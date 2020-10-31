package main.Services;

import main.Services.Interceptors.LoginTracking;

import javax.enterprise.inject.Default;
import javax.interceptor.Interceptors;

@Default
@Interceptors(LoginTracking.class)
public class DefaultLoginProvider implements LoginInterface {
    @Override
    public Boolean isAdmin(String login, String password) {
        return login.equals("admin") && password.equals("admin");
    }

    @Override
    public Boolean isUser(String login, String password) {
        return login.equals("user") && password.equals("user");
    }
}
