package ch.heig.amt.overflow.ui.web.auth;


import ch.heig.amt.overflow.application.auth.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        UserDTO currentUser = (UserDTO) request.getSession().getAttribute("currentUser");

        request.setAttribute("profile", currentUser);
        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
        request.getSession().removeAttribute("flash");
    }

}
