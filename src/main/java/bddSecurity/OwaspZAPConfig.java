package bddSecurity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.zaproxy.clientapi.core.*;
import steps.Parameters;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class OwaspZAPConfig {
    private static final Logger LOGGER = LogManager.getLogger();
    private static ClientApi clientApi = new ClientApi(Parameters.OWASP_ZAP_HTTP_IP, Parameters.OWASP_ZAP_HTTP_PORT);
    private static String previousUrlScanned = "";

    public static void runScanner(String urlToScan, String riskLevel) {
        //Verify if the "urlToScan" is equals to the "previousUrlScanned" to avoid multiple scans to the same URL.
        if (urlToScan.equals(previousUrlScanned)) {
            return;
        }
        //Run Passive Scan First (Because is the most basic and simple Scan)
        runPassiveScan(riskLevel);
        //Run Active Scan with each specified Penetration Test
        Parameters.OWASP_ZAP_ATTACK_CODES.forEach((attackType, attackTypeId) -> {
            runActiveScan(urlToScan, attackType, attackTypeId, riskLevel);
        });
        //Run Spider Scan at Last
        runSpiderScan(urlToScan, riskLevel);
        //Set the current URL scanned to the previous
        previousUrlScanned = urlToScan;
    }

    private static void runPassiveScan(String riskLevel) {
        LOGGER.info("--------------------------Starting Passive Scan--------------------------");
        try {
            //Remove all Historical Alerts generated Before
            clientApi.alert.deleteAllAlerts();
            //Enable all Scanners in Passive Mode
            clientApi.pscan.enableAllScanners();
            ApiResponse apiResponse = clientApi.pscan.recordsToScan();
            int scanTime = 0;
            while (!apiResponse.toString().equals("0")) {
                Thread.sleep(1000);
                scanTime++;
                apiResponse = clientApi.pscan.recordsToScan();
            }
            LOGGER.info("Passive Scan Completed in " + scanTime + " seconds");
            LOGGER.info("-------------------------------------------------------------------------");
            //Call the function to print the Alerts based on riskLevel settled
            printSecurityAlerts(riskLevel);
        } catch (ClientApiException | InterruptedException ex) {
            LOGGER.error("Passive Scan Error: " + ex.getMessage());
            LOGGER.info("-------------------------------------------------------------------------");
        }
    }

    private static void runActiveScan(String urlToScan, String scanTypeName, String scanTypeId, String riskLevel) {
        LOGGER.info("--------------------------Starting Active Scan---------------------------");
        LOGGER.info("Scanning URL: " + urlToScan);
        LOGGER.info("Scan Type: " + scanTypeName);
        LOGGER.info("Scan Id: " + scanTypeId);
        try {
            //Remove all Historical Alerts generated Before
            clientApi.alert.deleteAllAlerts();
            //Disable all other Scanners by Default
            clientApi.ascan.disableAllScanners(null);
            //Set Attack Mode in OwaspZap
            clientApi.core.setMode("attack");
            //Enable specific Active Scanner
            clientApi.ascan.enableScanners(scanTypeId, null);
            for (String id : scanTypeId.split(",")) {
                clientApi.ascan.setScannerAttackStrength(id, Parameters.OWASP_ZAP_CONFIG_STRENGTH, null);
                clientApi.ascan.setScannerAlertThreshold(id, Parameters.OWASP_ZAP_CONFIG_THRESHOLD, null);
            }
            ApiResponse apiResponse = clientApi.ascan.scan(urlToScan, "True", "False", null, null, null);
            String scanId = ((ApiResponseElement) apiResponse).getValue();
            int progress = 0;
            int scanTime = 0;
            while (progress < 100) {
                Thread.sleep(1000);
                scanTime++;
                progress = Integer.parseInt(((ApiResponseElement) clientApi.ascan.status(scanId)).getValue());
                //LOGGER.info("Active Scan progress: " + progress + "%");
            }
            LOGGER.info("Active Scan Completed in " + scanTime + " seconds");
            LOGGER.info("-------------------------------------------------------------------------");
            //Call the function to print the Alerts based on riskLevel settled
            printSecurityAlerts(riskLevel);
        } catch (ClientApiException | InterruptedException ex) {
            LOGGER.error("Active Scan Error: " + ex.getMessage());
            LOGGER.info("-------------------------------------------------------------------------");
        }
    }

    private static void runSpiderScan(String urlToScan, String riskLevel) {
        LOGGER.info("--------------------------Starting Spider Scan---------------------------");
        LOGGER.info("Scanning URL: " + urlToScan);
        try {
            //Remove all Historical Alerts generated Before
            clientApi.alert.deleteAllAlerts();
            //Enable specific Active Scanner
            ApiResponse apiResponse = clientApi.spider.scan(urlToScan, null, null, null, null);
            String scanId = ((ApiResponseElement) apiResponse).getValue();
            int progress = 0;
            int scanTime = 0;
            //Polling the status until it completes
            while (progress < 100) {
                Thread.sleep(1000);
                scanTime++;
                progress = Integer.parseInt(((ApiResponseElement) clientApi.spider.status(scanId)).getValue());
                //LOGGER.info("Spider progress : " + progress + "%");
            }
            LOGGER.info("Spider Scan Completed in " + scanTime + " seconds");
            LOGGER.info("-------------------------------------------------------------------------");
            //Call the function to print the Alerts based on riskLevel settled
            printSecurityAlerts(riskLevel);
        } catch (ClientApiException | InterruptedException ex) {
            LOGGER.error("Spider Scan Error: " + ex.getMessage());
            LOGGER.info("-------------------------------------------------------------------------");
        }
        LOGGER.info("-------------------------------------------------------------------------");
    }

    private static void printSecurityAlerts(String riskLevel) {
        //Get alert List to shown in the LOG
        List<Alert> lstAlerts = new ArrayList<>();
        //By default get "HIGH" level risks only
        Alert.Risk risk = Alert.Risk.High;
        if ("MEDIUM".equalsIgnoreCase(riskLevel)) {
            risk = Alert.Risk.Medium;
        } else if ("LOW".equalsIgnoreCase(riskLevel)) {
            risk = Alert.Risk.Low;
        }
        try {
            List<Alert> tmpLstAlerts = clientApi.getAlerts("", -1, -1);
            for (Alert alert : tmpLstAlerts) {
                if (alert.getRisk().ordinal() >= risk.ordinal()) {
                    lstAlerts.add(alert);
                }
            }
        } catch (ClientApiException ex) {
            LOGGER.error(ex.getMessage());
        }
        String details = "";
        for (Alert alert : lstAlerts) {
            details = details + alert.getName() + "\n"
                    + "URL: " + alert.getUrl() + "\n"
                    + "Parameter: " + alert.getParam() + "\n"
                    + "CWE-ID: " + alert.getCweId() + "\n"
                    + "WASC-ID: " + alert.getWascId() + "\n";
        }
        //If some vulnerabilities found they will be shown in LOG
        if (lstAlerts.size() > 0) {
            LOGGER.warn(lstAlerts.size() + " " + risk + " vulnerabilities found.\nDetails:\n" + details);
        } else {
            LOGGER.info("No " + riskLevel + " or Higher alerts found");
        }
        LOGGER.info("-------------------------------------------------------------------------");
    }
}