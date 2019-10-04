package steps.continuumsecurity;

import java.util.Map;

import steps.continuumsecurity.v5.model.Issue;
import steps.continuumsecurity.v5.model.Issue;

public interface ReportClient extends SessionClient {
    public Map<Integer, Issue> getAllIssuesSortedByPluginId(String uuid);
}