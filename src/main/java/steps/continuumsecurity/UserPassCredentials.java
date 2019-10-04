package steps.continuumsecurity;

public class UserPassCredentials extends Credentials {

    public UserPassCredentials() {
        super();
    }

    public UserPassCredentials(Credentials creds) {
        super("username", creds.get("username"), "password", creds.get("password"));
    }

    public UserPassCredentials(String username, String password) {
        super("username", username, "password", password);
    }

    public String getUsername() {
        return creds.get("username");
    }

    public void setUsername(String username) {
        this.creds.put("username", username);
    }

    public String getPassword() {
        return creds.get("password");
    }

    public void setPassword(String password) {
        this.creds.put("password", password);
    }
}