package steps.continuumsecurity.proxy.model;

import steps.continuumsecurity.proxy.model.ScanInfo;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseList;
import org.zaproxy.clientapi.core.ApiResponseSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScanResponse {
    List<steps.continuumsecurity.proxy.model.ScanInfo> scans = new ArrayList();

    public ScanResponse(ApiResponseList responseList) {
        for (ApiResponse rawResponse : responseList.getItems()) {
            scans.add(new steps.continuumsecurity.proxy.model.ScanInfo((ApiResponseSet) rawResponse));
        }
        Collections.sort(scans);
    }

    public List<steps.continuumsecurity.proxy.model.ScanInfo> getScans() {
        return scans;
    }

    public steps.continuumsecurity.proxy.model.ScanInfo getScanById(int scanId) {
        for (steps.continuumsecurity.proxy.model.ScanInfo scan : scans) {
            if (scan.getId() == scanId) return scan;
        }
        return null;
    }

    public ScanInfo getLastScan() {
        if (scans.size() == 0) throw new RuntimeException("No scans found");
        return scans.get(scans.size() - 1);
    }
}