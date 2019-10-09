package steps;

import java.util.HashMap;

public class Parameters {
    //File MIME Types to be directly accepted and downloaded
    private final static String FILE_MIME_TYPES = "application/msword," + "text/plain," + "text/html," + "image/jpeg," + "image/png," + "application/csv," + "text/csv," + "application/octet-stream," + "application/csv," + "pdf,"
            + "application/pdf," + "application/x-pdf," + "application/acrobat," + "application/vnd.pdf," + "text/pdf," + "text/x-pdf," + "application/vnd.adobe.xfdf," + "application/vnd.fdf,"
            + "application/vnd.adobe.xdp+xml," + "application/xml," + "text/xml," + "application/excel," + "application/vnd.ms-excel," + "application/msexcel," + "application/x-msexcel," + "application/x-ms-excel,"
            + "application/x-excel," + "application/x-dos_ms_excel," + "application/xls," + "application/x-xls," + "text/xls," + "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    //GLOBAL VARS TO USE
    public final static String[] SELENIUM_BROWSER_ARGUMENTS = {"--browser.download.manager.showWhenStarting=false",
            "--browser.download.folderList=2", "--browser.download.dir=C:\\Temp\\", "--incognito",
            "--browser.helperApps.neverAsk.saveToDisk=" + FILE_MIME_TYPES,
            "--browser.helperApps.neverAsk.openFile=" + FILE_MIME_TYPES};
    public final static String OWASP_ZAP_HTTP_IP = "127.0.0.1";
    public final static int OWASP_ZAP_HTTP_PORT = 9090;
    public final static String OWASP_ZAP_CONFIG_STRENGTH = "High";
    public final static String OWASP_ZAP_CONFIG_THRESHOLD = "Low";
    public final static HashMap<String, String> OWASP_ZAP_ATTACK_CODES = new HashMap<>(){{
        put("DIRECTORY_BROWSING","0");
        put("PATH_TRAVERSAL","6");
        put("REMOTE_FILE_INCLUSION","7");
        put("SOURCE_CODE_DISCLOSURE","10045");
        put("REMOTE_CODE_EXECUTION","20018");
        put("EXTERNAL_REDIRECT","20019");
        put("BUFFER_OVERFLOW","30001");
        put("FORMAT_STRING_ERROR","30002");
        put("CRLF_INJECTION","40003");
        put("PARAMETER_TAMPERING","40008");
        put("SERVER_SIDE_INCLUDE","40009");
        put("CROSS_SITE_SCRIPTING","40012,40014,40016,40017");
        put("SQL_INJECTION","40018");
        put("SCRIPT_ACTIVE_SCAN_RULES","50000");
        put("SERVER_SIDE_CODE_INJECTION","90019");
        put("REMOTE_OS_COMMAND_INJECTION","90020");
        //put("REMOTE_CODE_EXECUTION","20018");
        //put("LDAP_INJECTION","40015");
        //put("INSECURE_HTTP_METHODS","90028");
        //put("XPATH_INJECTION","90021");
        //put("PADDING_ORACLE","90024");
        //put("SHELL_SHOCK","10048");
        //put("XML_EXTERNAL_ENTITY","90023");
        //put("PARAMETER_POLLUTION","20014");
        //put("EL_INJECTION","90025");
        //put("PADDING_ORACLE","90024");
    }};
}
