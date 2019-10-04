package steps.continuumsecurity.proxy.model;

import org.zaproxy.clientapi.core.ApiResponseSet;

public class ScannerInfo {
    boolean enabled;
    int wascId;
    int cweId;

    public ScannerInfo(ApiResponseSet responseSet) {
        enabled = Boolean.parseBoolean(responseSet.getStringValue("enabled"));
        wascId = Integer.parseInt(responseSet.getStringValue("wascid"));
        cweId = Integer.parseInt(responseSet.getStringValue("cweid"));
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getWascId() {
        return wascId;
    }

    public int getCweId() {
        return cweId;
    }
}