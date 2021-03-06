/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.ui.web.auth;


import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.auth.AuthenticateCommand;
import ch.heig.amt.overflow.application.auth.AuthenticationFailedException;
import ch.heig.amt.overflow.application.auth.UserDTO;
import ch.heig.amt.overflow.domain.message.FlashMessage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ApplicationScoped
@WebServlet(name = "LoginCommand", urlPatterns = "/login.do")
public class LoginCommandServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    // authenticate the user with cmd throw the authFacade
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String clearTextPassword = req.getParameter("password");

        AuthenticateCommand command = AuthenticateCommand.builder()
                .username(username)
                .clearTextPassword(clearTextPassword)
                .build();

        try {
            UserDTO userDTO = serviceRegistry.getAuthFacade().authenticate(command);
            req.getSession().setAttribute("currentUser", userDTO);
            req.getSession().setAttribute("flash", FlashMessage.builder()
                    .message("Vous êtes maintenant connecté.")
                    .build());
            resp.sendRedirect("questions");
        } catch (AuthenticationFailedException e) {
            req.getSession().setAttribute("flash", FlashMessage.builder()
                    .message("Nom d'utilisateur ou mot de passe incorrect.")
                    .type("danger")
                    .build());
            resp.sendRedirect("login");
        } catch (Exception e) {
            req.getSession().setAttribute("flash", FlashMessage.builder()
                    .message(e.getMessage())
                    .type("danger")
                    .build());
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }

}
