package steps.continuumsecurity.web;

import org.apache.logging.log4j.LogManager;
import steps.continuumsecurity.clients.AuthTokenManager;
import org.apache.logging.log4j.Logger;

public abstract class Application {
    public static Logger log = LogManager.getLogger();

    public void pause(long milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract void enableHttpLoggingClient();

    public abstract void enableDefaultClient();

    public abstract AuthTokenManager getAuthTokenManager();
}