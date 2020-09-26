package ch.heig.amt.overflow.ui.web.question;

import ch.heig.amt.overflow.models.LoginCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String n = request.getParameter("username");
        String p = request.getParameter("password");

        LoginCommand loginCommand = new LoginCommand();
        loginCommand.setEmail(n);
        loginCommand.setPassword(p);
        request.setAttribute("loginCommand", loginCommand);

        if (n.equals("admin") && p.equals("admin")) {
            request.getSession().setAttribute("currentUser", true);
            response.sendRedirect("/overflow/question");
        } else {
            response.sendRedirect("/overflow/login");
        }
    }

}
