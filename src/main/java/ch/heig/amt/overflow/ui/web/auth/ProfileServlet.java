package ch.heig.amt.overflow.ui.web.auth;


import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.auth.AuthFacade;
import ch.heig.amt.overflow.application.auth.UserDTO;
import jdk.jshell.spi.ExecutionControl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {

    @Inject
    private ServiceRegistry serviceRegistry;
    private AuthFacade authFacade;
/*
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{

        UserDTO userDTO;

        try{
            questionDTO = authFacade.get;
        }
        catch(Exception e){

        }

        request.setAttribute("profile", userDTO);
        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
        request.getSession().removeAttribute("flash");
    }
    */

}
