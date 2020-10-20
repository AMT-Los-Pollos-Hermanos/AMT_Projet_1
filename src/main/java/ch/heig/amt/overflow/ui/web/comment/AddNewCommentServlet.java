package ch.heig.amt.overflow.ui.web.comment;

import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.auth.UserDTO;
import ch.heig.amt.overflow.application.comment.NewCommentCommand;
import ch.heig.amt.overflow.domain.MainContentId;
import ch.heig.amt.overflow.domain.message.FlashMessage;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddNewCommentServlet", urlPatterns = "/comment.do")
public class AddNewCommentServlet extends HttpServlet {

    @Inject
    ServiceRegistry registry;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO currentUser = (UserDTO) request.getSession().getAttribute("currentUser");
        String id = request.getParameter("content_id");
        NewCommentCommand cmd = NewCommentCommand.builder()
                .authorId(currentUser.getId())
                .content(request.getParameter("comment"))
                .mainContentId(new MainContentId(id))
                .build();
        try {
            registry.getCommentFacade().addNewComment(cmd);
        } catch (Exception e) {
            request.getSession().setAttribute("flash", FlashMessage.builder()
                    .message("Une erreur s'est produite lors de l'ajout de votre commentaire: " + e.getMessage())
                    .type("danger")
                    .build());
        }
        response.sendRedirect(request.getContextPath() + "/question/" + id);
    }

}
