package steps.continuumsecurity.v6.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScansV6 {
    private List<ScanV6> scans;

    @XmlElement(name = "scans")
    public List<ScanV6> getScans() {
        return scans;
    }

    public void setScans(List<ScanV6> scans) {
        this.scans = scans;
    }
}