package steps.continuumsecurity.v6.model;

import javax.xml.bind.annotation.XmlElement;

public class Login {
    private String token;
    private String error;

    @XmlElement
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @XmlElement
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}