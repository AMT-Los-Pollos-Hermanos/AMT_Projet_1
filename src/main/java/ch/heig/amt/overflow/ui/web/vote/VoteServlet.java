/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.ui.web.vote;

import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.auth.UserDTO;
import ch.heig.amt.overflow.application.vote.NewVoteCommand;
import ch.heig.amt.overflow.domain.ContentId;
import ch.heig.amt.overflow.domain.message.FlashMessage;
import ch.heig.amt.overflow.domain.vote.VoteStatus;

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

    // set the user vote to the facade
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDTO currentUser = (UserDTO) request.getSession().getAttribute("currentUser");
        String voteType = request.getParameter("state");
        VoteStatus voteStatus = voteType.equals("up") ? VoteStatus.UP : VoteStatus.DOWN;
        ContentId contentId = new ContentId(request.getParameter("content_id"));
        String questionId = request.getParameter("question_id");


        NewVoteCommand command = NewVoteCommand.builder()
                .userId(currentUser.getId())
                .contentId(contentId)
                .status(voteStatus)
                .build();

        try{
            serviceRegistry.getVoteFacade().addNewVote(command);
        }
        catch(Exception e){
            request.getSession().setAttribute("flash", FlashMessage.builder()
                    .message("Une erreur s'est produite lors du vote: " + e.getMessage())
                    .type("danger")
                    .build());
        }

        response.sendRedirect(request.getContextPath() + "/question/" + questionId);
    }
}
