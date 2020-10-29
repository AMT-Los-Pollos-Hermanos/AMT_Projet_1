package ch.heig.amt.overflow.ui.web.question;

import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.question.QuestionFacade;
import ch.heig.amt.overflow.application.question.QuestionQuery;
import ch.heig.amt.overflow.application.question.QuestionsDTO;
import ch.heig.amt.overflow.domain.message.FlashMessage;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuestionListServlet", urlPatterns = "/questions")
public class QuestionListServlet extends HttpServlet {

    @Inject
    private ServiceRegistry serviceRegistry;
    private QuestionFacade questionFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        questionFacade = serviceRegistry.getQuestionFacade();
    }

    // search for question
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("s");
        QuestionQuery query;
        if(search != null && !search.isEmpty()) {
            query = QuestionQuery.builder().search(search).build();
        } else {
            query = QuestionQuery.builder().build();
        }
        try {
            QuestionsDTO questionsDTO = questionFacade.getQuestions(query);
            request.setAttribute("questions", questionsDTO);
            request.setAttribute("search", search);
            request.getRequestDispatcher("/WEB-INF/views/questionList.jsp").forward(request, response);
            request.getSession().removeAttribute("flash");
        } catch (Exception e) {
            request.getSession().setAttribute("flash", FlashMessage.builder()
                    .message(e.getMessage())
                    .type("danger")
                    .build());
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

}
