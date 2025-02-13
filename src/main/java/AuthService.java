package main.java;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

// JAAS
public class AuthService {
    public static void authenticate(String username, String password) throws LoginException {
        LoginContext context = new LoginContext("TaskAuth", new CallbackHandler() {
            public void handle(Callback[] callbacks) {
                ((NameCallback) callbacks[0]).setName(username);
                ((PasswordCallback) callbacks[1]).setPassword(password.toCharArray());
            }
        });
        context.login(); // Throws exception if auth fails
    }
}
