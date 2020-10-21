package ch.heig.amt.overflow.ui.web.vote;

import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.answer.NewAnswerCommand;
import ch.heig.amt.overflow.application.auth.UserDTO;
import ch.heig.amt.overflow.application.vote.NewVoteCommand;
import ch.heig.amt.overflow.domain.ContentId;
import ch.heig.amt.overflow.domain.message.FlashMessage;
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

@WebServlet(name = "VoteServlet", urlPatterns = "/submitVote.do")
public class VoteServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDTO currentUser = (UserDTO) request.getSession().getAttribute("currentUser");
        String voteType = request.getParameter("state");
        VoteStatus voteStatus = voteType.equals("up") ? new VoteUp() : new VoteDown();
        ContentId contentId = new ContentId(request.getParameter("content_id"));


        NewVoteCommand command = NewVoteCommand.builder()
                .userId(currentUser.getId())
                .ContentId(contentId)
                .status(voteStatus)
                .build();

        try{
            serviceRegistry.getVoteFacade().addNewVote(command);
            request.getSession().setAttribute("flash", FlashMessage.builder()
                    .message("Vote effectué avec succès")
                    .build());
        }
        catch(Exception e){
            request.getSession().setAttribute("flash", FlashMessage.builder()
                    .message("Une erreur s'est produite lors du vote: " + e.getMessage())
                    .type("danger")
                    .build());
        }

        response.sendRedirect(request.getContextPath() + "/question/" + contentId);
    }
}
