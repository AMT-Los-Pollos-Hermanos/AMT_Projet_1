package ch.heig.amt.overflow.ui.web.auth;

import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.auth.AuthFacade;
import ch.heig.amt.overflow.application.auth.RegisterCommand;
import ch.heig.amt.overflow.application.auth.RegistrationFailedException;
import ch.heig.amt.overflow.domain.message.FlashMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterCommandServlet", urlPatterns = "/register.do")
public class RegisterCommandServlet extends HttpServlet {

    AuthFacade authFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        authFacade =  ServiceRegistry.getServiceRegistry().getAuthFacade();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RegisterCommand command = RegisterCommand.builder()
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .email(req.getParameter("email"))
                .username(req.getParameter("username"))
                .clearTextPassword(req.getParameter("password"))
                .build();

        try {
            authFacade.register(command);
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
        }
    }

}