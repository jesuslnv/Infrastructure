package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;

import java.io.Serializable;

public class MainRunner implements Serializable {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[1];
            args[0] = "-t @DEV and @Smoke";
            args[0] = args[0] + ";-p json:target/Infrastructure/Infrastructure.json";
            args[0] = args[0] + ", html:target/Infrastructure/html-report.html";
        }
        mainRun(args);
    }

    private static void mainRun(String[] args) {
        String tags = "";
        String plugin = "";
        for (int i = 0; i < args[0].split(";").length; i++) {
            String tmpData = args[0].split(";")[i];
            // The parameter contains the TAGS
            if (tmpData.contains("-t")) {
                tags = tmpData.replace("-t", "");
            }
            // The parameter contains the PLUGIN
            if (tmpData.contains("-p")) {
                plugin = tmpData.replace("-p ", "");
            }
        }
        LOGGER.info("-------------------------------------------------------------------------");
        LOGGER.info("TAGS: {}", tags);
        LOGGER.info("PLUGIN: {}", plugin);
        // --------------------------------------------------------------------------
        // Set corresponding properties AND Set all features in project by default
        // --------------------------------------------------------------------------
        System.setProperty("cucumber.glue", "steps");
        System.setProperty("cucumber.filter.tags", tags);
        System.setProperty("cucumber.plugin", plugin);
        System.setProperty("cucumber.features", "datafile/features/");
        JUnitCore junit = new JUnitCore();
        junit.run(CucumberBase.class);
    }

    //<editor-fold desc="BASE CLASS TO RUN CUCUMBER">
    @RunWith(Cucumber.class)
    @CucumberOptions(glue = "steps", tags = "@DEV")
    public static class CucumberBase {
    }
    //</editor-fold>
}