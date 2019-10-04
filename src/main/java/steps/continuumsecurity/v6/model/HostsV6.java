package steps.continuumsecurity.v6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HostsV6 {
    List<HostV6> hosts;

    public List<HostV6> getHosts() {
        return hosts;
    }

    public void setHosts(List<HostV6> hosts) {
        this.hosts = hosts;
    }
}