package steps.continuumsecurity;

import java.util.ArrayList;
import java.util.List;

public class Hosts {
    List<Host> hosts = new ArrayList<>();

    public void addHost(Host host) {
        hosts.add(host);
    }

    public List<Host> getHosts() {
        return hosts;
    }
}