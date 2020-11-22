package main.Servlets;


import main.Beans.LoginBean;
import main.Model.Car;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/start")
public class StartServlet extends HttpServlet {

    @Inject
    private LoginBean loginBean;
    private static final String PERSISTENCE_UNIT_NAME = "postgres";
    private static EntityManagerFactory factory;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(loginBean.getIsLogged()) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/navigation/navigationpanel.xhtml");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login/loginpanel.xhtml");
        }
    }
}
