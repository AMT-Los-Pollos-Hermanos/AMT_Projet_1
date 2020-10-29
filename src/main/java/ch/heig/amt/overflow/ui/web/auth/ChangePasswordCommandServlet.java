/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.ui.web.auth;

import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.auth.ChangePasswordCommand;
import ch.heig.amt.overflow.application.auth.ChangePasswordException;
import ch.heig.amt.overflow.application.auth.UserDTO;
import ch.heig.amt.overflow.domain.message.FlashMessage;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangePasswordCommandServlet", urlPatterns = "/changePassword.do")
public class ChangePasswordCommandServlet extends HttpServlet {
    @Inject
    ServiceRegistry serviceRegistry;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ChangePasswordCommand command = ChangePasswordCommand.builder()
                .userId(((UserDTO) request.getSession().getAttribute("currentUser")).getId())
                .oldPassword(request.getParameter("oldPassword"))
                .newPassword(request.getParameter("newPassword"))
                .newPasswordAgain(request.getParameter("newPasswordAgain"))
                .build();

        try {
            serviceRegistry.getAuthFacade().changePassword(command);
            request.getSession().setAttribute("flash", FlashMessage.builder()
                    .message("Mot de passe modifié avec succès")
                    .build());
            response.sendRedirect(request.getContextPath() + "/profile");
        } catch (ChangePasswordException e) {
            request.getSession().setAttribute("flash", FlashMessage.builder()
                    .type("danger")
                    .message(e.getMessage())
                    .build());
            response.sendRedirect(request.getContextPath() + "/changePassword");
        } catch (Exception e) {
            request.getSession().setAttribute("flash", FlashMessage.builder()
                    .type("danger")
                    .message("Erreur lors de la modification du mot de passe : " + e.getMessage())
                    .build());
            response.sendRedirect(request.getContextPath() + "/profile");
        }
    }
}
