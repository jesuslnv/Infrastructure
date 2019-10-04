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
import java.util.Collections;
import java.util.List;

public class OwaspZAPConfig {
    private static final Logger LOGGER = LogManager.getLogger();
    private static ClientApi clientApi = new ClientApi(Parameters.OWASP_ZAP_HTTP_IP, Parameters.OWASP_ZAP_HTTP_PORT);

    public static Proxy getOwaspZAPProxyConfig() {
        waitForSuccessfulConnectionToZap();
        Proxy proxy = new Proxy();
        String httpProxy = Parameters.OWASP_ZAP_HTTP_IP + ":" + Parameters.OWASP_ZAP_HTTP_PORT;
        proxy.setHttpProxy(httpProxy).setFtpProxy(httpProxy).setSslProxy(httpProxy);
        return proxy;
    }

    private static void runPassiveScan() {
        LOGGER.info("Starting Passive Scan");
        try {
            //Remove all Historical Scans
            clientApi.ascan.removeAllScans();
            //Enable all Scanners in Passive Mode
            clientApi.pscan.enableAllScanners();
            ApiResponse apiResponse = clientApi.pscan.recordsToScan();
            int scanTime = 0;
            while (!apiResponse.toString().equals("0")) {
                apiResponse = clientApi.pscan.recordsToScan();
                scanTime++;
                Thread.sleep(1000);
            }
            LOGGER.info("Passive Scan Completed in " + scanTime + " seconds");
        } catch (ClientApiException | InterruptedException ex) {
            LOGGER.error("Passive Scan Error: " + ex.getMessage());
        }
    }

    public static void runActiveScan(String urlToScan) {
        LOGGER.info("Starting Active Scan to URL: " + urlToScan);
        LOGGER.info("WEWE: " + clientApi.ascan);
        try {
            String scannerIds = Parameters.OWASP_ZAP_ATTACK_CODE__DIRECTORY_BROWSING;
            clientApi.ascan.enableScanners(scannerIds, null);
            for (String id : scannerIds.split(",")) {
                clientApi.ascan.setScannerAttackStrength(id, Parameters.OWASP_ZAP_CONFIG_STRENGTH, null);
                clientApi.ascan.setScannerAlertThreshold(id, Parameters.OWASP_ZAP_CONFIG_THRESHOLD, null);
            }
            ApiResponse apiResponse = clientApi.ascan.scan(urlToScan, "True", "False", null, null, null);
            int progress = 0;
            String scanId = ((ApiResponseElement) apiResponse).getValue();
            while (progress < 100) {
                Thread.sleep(3000);
                progress = Integer.parseInt(((ApiResponseElement) clientApi.ascan.status(scanId)).getValue());
                LOGGER.info("Active Scan progress: " + progress + "%");
            }
            LOGGER.info("Active Scan Completed");
        } catch (ClientApiException | InterruptedException ex) {
            LOGGER.error("Active Scan Error: " + ex.getMessage());
        }
    }

    public static void runScanner(String urlToScan, String riskLevel) {
        //Run Passive Scan First (Because is the most basic and simple Scan)
        runPassiveScan();
        //Run Active Scan with specific Penetration Test defined
        runActiveScan(urlToScan);
        LOGGER.info("Scanning: " + urlToScan);
        try {
            clientApi.ascan.scan(urlToScan, "true", "false", null, null, null);
            LOGGER.info("wewewewewewewe 2");
            int complete = 0;
            ApiResponseList response = (ApiResponseList) clientApi.ascan.scans();
            int scanId = 0;
            for (ApiResponse rawResponse : response.getItems()) {
                scanId = Integer.parseInt(((ApiResponseSet) rawResponse).getStringValue("id"));
            }
            LOGGER.info("wewewewewewewe 3");
            while (complete < 100) {
                response = (ApiResponseList) clientApi.ascan.scans();
                List<ScanInfo> lstScanInfos = getOrderedScanInfoList(response);
                complete = getScanInfoById(lstScanInfos, scanId).getProgress();
                LOGGER.debug("Scan is " + complete + "% complete.");
                Thread.sleep(1000);
            }
            LOGGER.info("wewewewewewewe 4");
            //----------------------------------------------------------------
            //Configure SPIDER in ClientAPI
            clientApi.spider.setOptionMaxDepth(5);
            clientApi.spider.setOptionThreadCount(10);
            clientApi.spider.scan(urlToScan, null, "true", "Default Context", null);
            waitForSpiderToComplete();
            //----------------------------------------------------------------
            printSecurityAlerts(riskLevel);
        } catch (InterruptedException | ClientApiException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private static void waitForSuccessfulConnectionToZap() {
        int timeoutInMs = 15000;
        int connectionTimeoutInMs = timeoutInMs;
        boolean connectionSuccessful = false;
        long startTime = System.currentTimeMillis();
        Socket socket = null;
        while (!connectionSuccessful) {
            try {
                LOGGER.info("Attempting to connect to ZAP API on: " + Parameters.OWASP_ZAP_HTTP_IP + " port: " + Parameters.OWASP_ZAP_HTTP_PORT);
                socket = new Socket();
                socket.connect(new InetSocketAddress(Parameters.OWASP_ZAP_HTTP_IP, Parameters.OWASP_ZAP_HTTP_PORT), connectionTimeoutInMs);
                connectionSuccessful = true;
                LOGGER.info("Connected to ZAP");
            } catch (SocketTimeoutException ignore) {
                throw new RuntimeException("Unable to connect to ZAP's proxy after " + timeoutInMs + " milliseconds.");
            } catch (IOException ignore) {
                // and keep trying but wait some time first...
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException("The task was interrupted while sleeping between connection polling.", e);
                }
                long ellapsedTime = System.currentTimeMillis() - startTime;
                if (ellapsedTime >= timeoutInMs) {
                    throw new RuntimeException("Unable to connect to ZAP's proxy after " + timeoutInMs + " milliseconds.");
                }
                connectionTimeoutInMs = (int) (timeoutInMs - ellapsedTime);
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void waitForSpiderToComplete() {
        int status = 0;
        int counter99 = 0; //hack to detect a ZAP spider that gets stuck on 99%
        ApiResponseList response = null;
        try {
            response = (ApiResponseList) clientApi.spider.scans();
            List<ScanInfo> lstScanInfos = getOrderedScanInfoList(response);
            ScanInfo lastScanInfo = lstScanInfos.get(lstScanInfos.size() - 1);
            int scanId = lastScanInfo.getId();
            while (status < 100) {
                response = (ApiResponseList) clientApi.spider.scans();
                lstScanInfos = getOrderedScanInfoList(response);
                status = getScanInfoById(lstScanInfos, scanId).getProgress();
                if (status == 99) {
                    counter99++;
                }
                if (counter99 > 10) {
                    break;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        } catch (ClientApiException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private static List<ScanInfo> getOrderedScanInfoList(ApiResponseList apiResponseList) {
        List<ScanInfo> lstScanInfos = new ArrayList<>();
        for (ApiResponse apiResponse : apiResponseList.getItems()) {
            lstScanInfos.add(new ScanInfo((ApiResponseSet) apiResponse));
        }
        Collections.sort(lstScanInfos);
        return lstScanInfos;
    }

    private static ScanInfo getScanInfoById(List<ScanInfo> lstScanInfos, int scanId) {
        for (ScanInfo scanInfo : lstScanInfos) {
            if (scanInfo.getId() == scanId) {
                return scanInfo;
            }
        }
        return null;
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
        }
    }
}