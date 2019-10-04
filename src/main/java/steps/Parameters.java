package steps;

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
    public final static String OWASP_ZAP_HTTP_IP = "localhost";
    public final static int OWASP_ZAP_HTTP_PORT = 9090;
    public final static String OWASP_ZAP_CONFIG_STRENGTH = "High";
    public final static String OWASP_ZAP_CONFIG_THRESHOLD = "Low";
    public final static String OWASP_ZAP_ATTACK_CODE__DIRECTORY_BROWSING = "0";
    public final static String OWASP_ZAP_ATTACK_CODE__CROSS_SITE_SCRIPTING = "40012,40014,40016,40017";
    public final static String OWASP_ZAP_ATTACK_CODE__SQL_INJECTION = "40018";
    public final static String OWASP_ZAP_ATTACK_CODE__PATH_TRAVERSAL = "6";
    public final static String owasp_zap_attack_code__remote_file_inclusion = "7";
    public final static String OWASP_ZAP_ATTACK_CODE__SERVER_SIDE_INCLUDE = "40009";
    public final static String OWASP_ZAP_ATTACK_CODE__SCRIPT_ACTIVE_SCAN_RULES = "50000";
    public final static String OWASP_ZAP_ATTACK_CODE__SERVER_SIDE_CODE_INJECTION = "90019";
    public final static String OWASP_ZAP_ATTACK_CODE__REMOTE_OS_COMMAND_INJECTION = "90020";
    public final static String OWASP_ZAP_ATTACK_CODE__EXTERNAL_REDIRECT = "20019";
    public final static String OWASP_ZAP_ATTACK_CODE__CRLF_INJECTION = "40003";
    public final static String OWASP_ZAP_ATTACK_CODE__SOURCE_CODE_DISCLOSURE = "42,10045,20017";
    public final static String OWASP_ZAP_ATTACK_CODE__SHELL_SHOCK = "10048";
    public final static String OWASP_ZAP_ATTACK_CODE__REMOTE_CODE_EXECUTION = "20018";
    public final static String OWASP_ZAP_ATTACK_CODE__LDAP_INJECTION = "40015";
    public final static String OWASP_ZAP_ATTACK_CODE__XPATH_INJECTION = "90021";
    public final static String OWASP_ZAP_ATTACK_CODE__XML_EXTERNAL_ENTITY = "90023";
    public final static String OWASP_ZAP_ATTACK_CODE__PADDING_ORACLE = "90024";
    public final static String OWASP_ZAP_ATTACK_CODE__EL_INJECTION = "90025";
    public final static String OWASP_ZAP_ATTACK_CODE__INSECURE_HTTP_METHODS = "90028";
    public final static String OWASP_ZAP_ATTACK_CODE__PARAMETER_POLLUTION = "20014";
}
