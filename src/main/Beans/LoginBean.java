package main.Beans;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name="loginbean")
@SessionScoped
public class LoginBean {
    private String login = "";
    private String password = "";

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
        return login.equals("admin") && password.equals("admin");
    }

    private Boolean getIsUser() {
        return login.equals("user") && password.equals("user");
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
