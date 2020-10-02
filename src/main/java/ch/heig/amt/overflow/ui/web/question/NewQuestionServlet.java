package ch.heig.amt.overflow.ui.web.question;

import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.question.NewQuestionCommand;
import ch.heig.amt.overflow.application.question.QuestionFacade;
import ch.heig.amt.overflow.domain.message.FlashMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewQuestionServlet", urlPatterns = "/submitQuestion.do")
public class NewQuestionServlet extends HttpServlet {

    QuestionFacade questionFacade = ServiceRegistry.getServiceRegistry().getQuestionFacade();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        NewQuestionCommand command = NewQuestionCommand.builder()
                .author("Anonymous")
                .title(request.getParameter("title"))
                .content(request.getParameter("content"))
                .build();

        try {
            questionFacade.addNewQuestion(command);
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
