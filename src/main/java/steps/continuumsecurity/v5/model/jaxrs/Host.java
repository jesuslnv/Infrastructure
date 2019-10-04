package steps.continuumsecurity.v5.model.jaxrs;

public class Host {
    private String hostname;
    private int severity;

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}