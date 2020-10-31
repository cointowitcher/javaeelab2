package main.Beans;
import main.Services.LoginInterface;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@ManagedBean(name="loginbean")
@SessionScoped
public class LoginBean {
    private String login = "";
    private String password = "";
    @Inject
    private LoginInterface loginProvider;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Boolean getIsAdmin() {
        return loginProvider.isAdmin(login, password);
    }

    private Boolean getIsUser() {
        return loginProvider.isUser(login, password);
    }

    public Boolean getIsLogged() {
        return getIsAdmin() || getIsUser();
    }

    public String go() {
        if(getIsLogged()) {
            return "authorized";
        } else {
            return "loginerror";
        }
    }

}
