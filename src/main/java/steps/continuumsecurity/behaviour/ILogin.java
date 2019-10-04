package steps.continuumsecurity.behaviour;

import steps.continuumsecurity.Credentials;

public interface ILogin {
    /*
        Assume that the browser is open on the login page, and perform the login.
        A UserPassCredentials class exists for simple single auth authentication.
     */
    void login(Credentials credentials);

    /*
        Describe how to navigate from the base URL to the login page.
     */
    void openLoginPage();

    /*
        Determine whether the user is currently logged in or not.
        This should involve first making a request for a resource and then determining whether the
        user is logged in based on the response.
        To improve robustness, the call to the resource should be possible from any location
        in the application.
     */
    boolean isLoggedIn();
}