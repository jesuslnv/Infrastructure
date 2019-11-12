package steps;

class Parameters {

    //Private constructor to hide the implicit public one
    private Parameters() {
    }

    //File MIME Types to be directly accepted and downloaded
    private static final String FILE_MIME_TYPES = "application/msword," + "text/plain," + "text/html," + "image/jpeg," + "image/png," + "application/csv," + "text/csv," + "application/octet-stream," + "application/csv," + "pdf,"
            + "application/pdf," + "application/x-pdf," + "application/acrobat," + "application/vnd.pdf," + "text/pdf," + "text/x-pdf," + "application/vnd.adobe.xfdf," + "application/vnd.fdf,"
            + "application/vnd.adobe.xdp+xml," + "application/xml," + "text/xml," + "application/excel," + "application/vnd.ms-excel," + "application/msexcel," + "application/x-msexcel," + "application/x-ms-excel,"
            + "application/x-excel," + "application/x-dos_ms_excel," + "application/xls," + "application/x-xls," + "text/xls," + "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    //GLOBAL VARS TO USE
    static final String[] SELENIUM_BROWSER_ARGUMENTS = {"--browser.download.manager.showWhenStarting=false",
            "--browser.download.folderList=2", "--browser.download.dir=C:\\Temp\\", "--incognito",
            "--browser.helperApps.neverAsk.saveToDisk=" + FILE_MIME_TYPES,
            "--browser.helperApps.neverAsk.openFile=" + FILE_MIME_TYPES};
    static final String OWASP_ZAP_HTTP_IP = "127.0.0.1";
    static final int OWASP_ZAP_HTTP_PORT = 9090;
}