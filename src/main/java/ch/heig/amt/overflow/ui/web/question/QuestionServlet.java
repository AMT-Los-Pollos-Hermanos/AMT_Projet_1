package ch.heig.amt.overflow.ui.web.question;

import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.question.QuestionFacade;
import ch.heig.amt.overflow.application.question.QuestionNotFoundException;
import ch.heig.amt.overflow.application.question.QuestionQuery;
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

@WebServlet(name = "QuestionServlet", urlPatterns = "/question/*")
public class QuestionServlet extends HttpServlet {

    @Inject
    private ServiceRegistry serviceRegistry;
    private QuestionFacade questionFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        questionFacade = serviceRegistry.getQuestionFacade();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId = request.getPathInfo().split("/")[1];
        QuestionsDTO.QuestionDTO questionDTO;

        try {
            questionDTO = questionFacade.getQuestion(new QuestionId(questionId));
        } catch (QuestionNotFoundException e) {
            e.printStackTrace();
            request.getSession().setAttribute("flash", FlashMessage.builder()
                    .message(e.getMessage())
                    .type("danger")
                    .build());
            response.sendRedirect("questions");
            return;
        }

        request.setAttribute("question", questionDTO);
        request.getRequestDispatcher("/WEB-INF/views/question.jsp").forward(request, response);
        request.getSession().removeAttribute("flash");

    }
}
