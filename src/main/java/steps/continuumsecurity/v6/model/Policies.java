package steps.continuumsecurity.v6.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class Policies {
    List<PolicyV6> policies;

    @XmlElement
    public List<PolicyV6> getPolicies() {
        return policies;
    }

    public void setPolicies(List<PolicyV6> policies) {
        this.policies = policies;
    }
}