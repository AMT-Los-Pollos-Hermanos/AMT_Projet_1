package ch.heig.amt.overflow.ui.web.auth;


import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.auth.AuthFacade;
import ch.heig.amt.overflow.application.auth.AuthenticateCommand;
import ch.heig.amt.overflow.application.auth.AuthenticationFailedException;
import ch.heig.amt.overflow.application.auth.CurrentUserDTO;
import ch.heig.amt.overflow.domain.message.FlashMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginCommand", urlPatterns = "/login.do")
public class LoginCommandServlet extends HttpServlet {

    private AuthFacade authFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        authFacade = ServiceRegistry.getServiceRegistry().getAuthFacade();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String clearTextPassword = req.getParameter("password");

        AuthenticateCommand command = AuthenticateCommand.builder()
                .username(username)
                .clearTextPassword(clearTextPassword)
                .build();

        try {
            CurrentUserDTO currentUserDTO = authFacade.authenticate(command);
            req.getSession().setAttribute("currentUser", currentUserDTO);
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
        }
    }

}
