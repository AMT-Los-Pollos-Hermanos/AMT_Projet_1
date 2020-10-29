/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.ui.web.question;

import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.auth.UserDTO;
import ch.heig.amt.overflow.application.question.NewQuestionCommand;
import ch.heig.amt.overflow.domain.message.FlashMessage;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewQuestionServlet", urlPatterns = "/submitQuestion.do")
public class NewQuestionServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;
     // add new question to the question facade
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO currentUser = (UserDTO) request.getSession().getAttribute("currentUser");
        NewQuestionCommand command = NewQuestionCommand.builder()
                .authorId(currentUser.getId())
                .title(request.getParameter("title"))
                .content(request.getParameter("content"))
                .build();

        try {
            serviceRegistry.getQuestionFacade().addNewQuestion(command);
            request.getSession().setAttribute("flash", FlashMessage.builder()
                    .message("Question publiée avec succès")
                    .build());
        } catch (Exception e) {
            request.getSession().setAttribute("flash", FlashMessage.builder()
                    .message("Une erreur s'est produite lors de l'ajout de votre question: " + e.getMessage())
                    .type("danger")
                    .build());
        }
        response.sendRedirect(request.getContextPath() + "/questions");
    }

}
