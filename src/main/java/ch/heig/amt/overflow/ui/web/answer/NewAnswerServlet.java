package ch.heig.amt.overflow.ui.web.answer;

import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.answer.NewAnswerCommand;
import ch.heig.amt.overflow.application.auth.UserDTO;
import ch.heig.amt.overflow.application.question.NewQuestionCommand;
import ch.heig.amt.overflow.application.question.QuestionsDTO;
import ch.heig.amt.overflow.domain.message.FlashMessage;
import ch.heig.amt.overflow.domain.question.QuestionId;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewAnswerServlet", urlPatterns = "/submitAnswer.do")
public class NewAnswerServlet extends HttpServlet {

    @Inject
    ServiceRegistry serviceRegistry;

    // add new answer to the answer facade or throw exception 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDTO currentUser = (UserDTO) request.getSession().getAttribute("currentUser");
        String url = request.getParameter("question_id");
        QuestionId questionId = new QuestionId(url);

        NewAnswerCommand command = NewAnswerCommand.builder()
                .authorId(currentUser.getId())
                .content(request.getParameter("content"))
                .questionId(questionId)
                .build();

        try {
            serviceRegistry.getAnswerFacade().addNewAnswer(command);
            request.getSession().setAttribute("flash", FlashMessage.builder()
                    .message("Réponse publiée avec succès")
                    .build());
        } catch (Exception e) {
            request.getSession().setAttribute("flash", FlashMessage.builder()
                    .message("Une erreur s'est produite lors de l'ajout de votre réponse: " + e.getMessage())
                    .type("danger")
                    .build());
        }
        response.sendRedirect(request.getContextPath() + "/question/" + questionId);
    }
}
