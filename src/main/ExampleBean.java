package main;


import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@ManagedBean(name="exampleBean")
@SessionScoped
public class ExampleBean implements Serializable {
    private boolean logged = false;
    private String login = "";
    private String password = "";

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

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

    public String doLogin() {
        logged = true;
        return "success";
    }

    public String getStatus() {
        if(login.equals("admin") && password.equals("admin")) {
            return "admin";
        } else if(login.equals("user") && password.equals("user")) {
            return "user";
        } else {
            return "unknown";
        }
    }
}
