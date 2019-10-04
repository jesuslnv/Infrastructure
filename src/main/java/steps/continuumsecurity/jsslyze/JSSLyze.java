package steps.continuumsecurity.jsslyze;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSSLyze {
    ProcessExecutor executor;
    SSLyzeParser parser;
    String outputFilename = "sslyze.output";
    String pathToSslyze;
    String output;

    public JSSLyze(String pathToSslyze) {
        this.pathToSslyze = pathToSslyze;
    }

    public JSSLyze(String pathToSslyze, String outputFilename) {
        this(pathToSslyze);
        this.outputFilename = outputFilename;
    }

    public void execute(String options, String host, int port) throws IOException {
        List<String> cmds = new ArrayList<>();
        cmds.add(pathToSslyze);
        cmds.addAll(Arrays.asList(options.split("\\s+")));
        if (port > -1) {
            host = host + ":" + port;
        }
        cmds.add(host);
        executor = new ProcessExecutor(cmds);
        executor.setFilename(outputFilename);
        executor.start();
        output = executor.getOutput();
        parser = new SSLyzeParser(output);
    }

    public SSLyzeParser getParser() {
        return parser;
    }

    public String getOutput() {
        return output;
    }
}