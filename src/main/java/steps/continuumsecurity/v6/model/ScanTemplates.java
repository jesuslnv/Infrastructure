package steps.continuumsecurity.v6.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScanTemplates {
    private List<ScanTemplate> templates;

    public List<ScanTemplate> getTemplates() {
        return templates;
    }

    public void setTemplates(List<ScanTemplate> templates) {
        this.templates = templates;
    }
}