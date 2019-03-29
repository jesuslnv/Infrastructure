package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainRunner {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[1];
            args[0] = "-t @DEV -t @Smoke2";
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
        // --------------------------------------
        // Set all features in project by default
        String features = "";
        String featuresPath = "datafile/features/";
        List<File> featureFiles = listFeatureFiles(featuresPath, new ArrayList<>());
        File prjPath = new File("");
        for (File featureFile : featureFiles) {
            String tmpPath = featureFile.getAbsolutePath().replace(prjPath.getAbsolutePath() + "\\", "");
            features = features + " " + tmpPath;
            logger.info("ALL FEATURES: " + tmpPath);
        }
        // --------------------------------------
        // Set corresponding properties
        System.setProperty("cucumber.options", tags + " " + features + " " + plugin);
        JUnitCore junit = new JUnitCore();
        junit.run(CucumberBase.class);
    }

    //<editor-fold desc="EXTRA FUNCTIONS">
    //<editor-fold desc="GET LIST OF FEATURES">
    private static List<File> listFeatureFiles(String dir, ArrayList<File> files) {
        File featuresDir = new File(dir);
        File[] featuresList = featuresDir.listFiles();
        if (featuresList != null) {
            for (File file : featuresList) {
                if (file.isFile() && file.getAbsolutePath().contains(".feature")) {
                    files.add(file);
                } else {
                    if (file.isDirectory()) {
                        listFeatureFiles(file.getAbsolutePath(), files);
                    }
                }
            }
        }
        return files;
    }
    //</editor-fold>

    //<editor-fold desc="BASE CLASS TO RUN CUCUMBER">
    @RunWith(Cucumber.class)
    @CucumberOptions(glue = "steps", tags = {"@DEV"})
    public class CucumberBase {
    }
    //</editor-fold>
    //</editor-fold>
}