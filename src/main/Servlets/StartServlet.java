package main.Servlets;


import main.Beans.LoginBean;
import main.DatabaseWrapper;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/start")
public class StartServlet extends HttpServlet {

    @Inject
    private LoginBean loginBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseWrapper.createTables();
        if(loginBean.getIsLogged()) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/navigation/navigationpanel.xhtml");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login/loginpanel.xhtml");
        }
    }
}
