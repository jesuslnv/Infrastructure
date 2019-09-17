package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import java.io.Serializable;

public class MainRunner implements Serializable {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[1];
            args[0] = "-t @DEV -t @Smoke -t 'not @Chrome'";
            args[0] = args[0] + ";-p json:target/smoke/Infrastructure/Infrastructure.json";
            args[0] = args[0] + " -p html:target/smoke/Infrastructure/html-report";
        }
        mainRun(args);
    }

    private static void mainRun(String[] args) {
        String tags = "", plugin = "";
        for (int i = 0; i < args[0].split(";").length; i++) {
            String tmpData = args[0].split(";")[i];
            // The parameter contains the TAGS
            if (tmpData.contains("-t")) {
                tags = tmpData;
            }
            // The parameter contains the PLUGIN
            if (tmpData.contains("-p")) {
                plugin = tmpData;
            }
        }
        logger.info("TAGS: " + tags);
        logger.info("PLUGIN: " + plugin);
        // --------------------------------------------------------------------------
        // Set corresponding properties AND Set all features in project by default
        // --------------------------------------------------------------------------
        System.setProperty("cucumber.options", tags + " datafile/features/ " + plugin);
        JUnitCore junit = new JUnitCore();
        junit.run(CucumberBase.class);
    }

    //<editor-fold desc="BASE CLASS TO RUN CUCUMBER">
    @RunWith(Cucumber.class)
    @CucumberOptions(glue = "steps", tags = {"@DEV"})
    public static class CucumberBase {
    }
    //</editor-fold>
}