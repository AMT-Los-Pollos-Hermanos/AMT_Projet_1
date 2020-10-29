package ch.heig.amt.overflow.ui.web.auth;

import ch.heig.amt.overflow.domain.message.FlashMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout.do")
public class LogoutCommandServlet extends HttpServlet {

    @Override
    // remove user from session to logout
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("currentUser") != null) {
            req.getSession().removeAttribute("currentUser");
        }
        req.getSession().setAttribute("flash", FlashMessage.builder()
                .message("Vous êtes maintenant déconnecté.")
                .build());
        resp.sendRedirect(req.getContextPath() + "/login");
    }

}
