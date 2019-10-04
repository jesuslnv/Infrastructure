package steps.continuumsecurity.clients;

import java.util.Map;

public interface AuthTokenManager {
    Map<String, String> getAuthTokens();

    void setAuthTokens(Map<String, String> tokens);

    void deleteAuthTokens();
}