package main.Services;

import main.Services.LoginInterface;

import javax.enterprise.inject.Alternative;

@Alternative
public class TwitterLoginProvider implements LoginInterface {
    @Override
    public Boolean isAdmin(String login, String password) {
        return login.equals("admin2") && password.equals("admin2");
    }

    @Override
    public Boolean isUser(String login, String password) {
        return login.equals("user2") && password.equals("user2");
    }
}
