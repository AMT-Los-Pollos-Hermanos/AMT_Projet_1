package ch.heig.amt.overflow.ui.web.vote;

import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.answer.NewAnswerCommand;
import ch.heig.amt.overflow.application.auth.UserDTO;
import ch.heig.amt.overflow.application.question.NewQuestionCommand;
import ch.heig.amt.overflow.application.question.QuestionsDTO;
import ch.heig.amt.overflow.application.vote.NewVoteCommand;
import ch.heig.amt.overflow.domain.ContentId;
import ch.heig.amt.overflow.domain.message.FlashMessage;
import ch.heig.amt.overflow.domain.question.QuestionId;
import ch.heig.amt.overflow.domain.vote.status.VoteDown;
import ch.heig.amt.overflow.domain.vote.status.VoteStatus;
import ch.heig.amt.overflow.domain.vote.status.VoteUp;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewVoteServlet", urlPatterns = "/submitVote.do")
public class NewVoteServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String referer = request.getHeader("referer");

        UserDTO currentUser = (UserDTO) request.getSession().getAttribute("currentUser");
        ContentId contentId = new ContentId(request.getParameter("content_id"));
        String vote = request.getParameter("status");

        // Set vote's status based on the parameter "status"
        VoteStatus voteStatus = vote.equals(new VoteUp().getVote()) ? new VoteUp() : new VoteDown();

        NewVoteCommand command = NewVoteCommand.builder()
                .userId(currentUser.getId())
                .ContentId(contentId)
                .status(voteStatus)
                .build();

        try {
            serviceRegistry.getVoteFacade().addNewVote(command);
            request.getSession().setAttribute("flash", FlashMessage.builder()
                    .message("Vote publiée avec succès")
                    .build());
        } catch (Exception e) {
            request.getSession().setAttribute("flash", FlashMessage.builder()
                    .message("Une erreur s'est produite lors de l'ajout de votre vote: " + e.getMessage())
                    .type("danger")
                    .build());
        }
        response.sendRedirect(request.getContextPath() + (referer != null ? referer : ""));
    }
}
