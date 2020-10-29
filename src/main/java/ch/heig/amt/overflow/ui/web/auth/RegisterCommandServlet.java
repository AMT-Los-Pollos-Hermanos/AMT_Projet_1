package ch.heig.amt.overflow.ui.web.auth;

import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.auth.AuthFacade;
import ch.heig.amt.overflow.application.auth.RegisterCommand;
import ch.heig.amt.overflow.application.auth.RegistrationFailedException;
import ch.heig.amt.overflow.domain.message.FlashMessage;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterCommandServlet", urlPatterns = "/register.do")
public class RegisterCommandServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    // register user throw the facade from the req or throw exception
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RegisterCommand command = RegisterCommand.builder()
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .email(req.getParameter("email"))
                .username(req.getParameter("username"))
                .clearTextPassword(req.getParameter("password"))
                .build();

        try {
            serviceRegistry.getAuthFacade().register(command);
            req.getSession().setAttribute("flash", FlashMessage.builder()
                    .message("Compte créé avec succès. Vous pouvez maintenant vous connecter.")
                    .build());
            resp.sendRedirect("login");
        } catch (RegistrationFailedException e) {
            req.getSession().setAttribute("flash", FlashMessage.builder()
                    .message("Une erreur s'est produite durant l'enregistrement: " + e.getMessage())
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
