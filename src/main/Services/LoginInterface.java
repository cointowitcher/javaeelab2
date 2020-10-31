package main.Services;

public interface LoginInterface {
    Boolean isAdmin(String login, String password);
    Boolean isUser(String login, String password);
}
