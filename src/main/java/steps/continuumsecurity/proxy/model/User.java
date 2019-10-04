package steps.continuumsecurity.proxy.model;

import org.zaproxy.clientapi.core.ApiResponseSet;

import java.io.IOException;

public class User {
    String id;
    boolean enabled;
    String contextId;
    String name;

    public User(ApiResponseSet apiResponseSet) throws IOException {
        id = apiResponseSet.getStringValue("id");
        enabled = Boolean.valueOf(apiResponseSet.getStringValue("enabled"));
        contextId = apiResponseSet.getStringValue("contextId");
        name = apiResponseSet.getStringValue("name");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
