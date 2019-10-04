package steps.continuumsecurity.v5.model.jaxrs;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Scans {
    private List<Scan> scan;

    @XmlElementWrapper(name = "scanList")
    @XmlElement(name = "scan")
    public List<Scan> getScan() {
        return scan;
    }

    public void setScan(List<Scan> scan) {
        this.scan = scan;
    }
}