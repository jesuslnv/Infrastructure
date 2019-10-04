package steps.continuumsecurity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class User {
    private Credentials credentials;
    private List<String> roles;
    private Map<String, String> recoverPasswordMap;

    public User(Credentials credentials, String... roles) {
        this.roles = (List<String>) Arrays.asList(roles);
        this.credentials = credentials;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String getDefaultRole() {
        if (roles != null && roles.size() > 0) {
            return roles.get(0);
        }
        return null;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getRolesAsCSV() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < roles.size(); i++) {
            res.append(roles.get(i));
            if (i < roles.size() - 1) res.append(",");
        }
        return res.toString();
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean hasRole(String theRole) {
        for (String role : roles) {
            if (role.equalsIgnoreCase(theRole)) return true;
        }
        return false;
    }

    public boolean hasRole(List<String> theRoles) {
        for (String role : roles) {
            if (theRoles.contains(role)) return true;
        }
        return false;
    }

    public Map<String, String> getRecoverPasswordMap() {
        return recoverPasswordMap;
    }

    public void setRecoverPasswordMap(Map<String, String> recoverPassword) {
        this.recoverPasswordMap = recoverPassword;
    }
}