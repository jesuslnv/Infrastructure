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
    public final static String OWASP_ZAP_HTTP_PROXY = "localhost:9090";
}
