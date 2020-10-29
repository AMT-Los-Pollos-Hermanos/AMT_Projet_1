/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.ui.web.auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangePasswordServlet", urlPatterns = "/changePassword")
public class ChangePasswordServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/changePassword.jsp").forward(request, response);
        request.getSession().removeAttribute("flash");
    }

}
