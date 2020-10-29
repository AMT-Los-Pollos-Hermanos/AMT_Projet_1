/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.ui.web.question;

import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.answer.AnswerFacade;
import ch.heig.amt.overflow.application.answer.AnswersDTO;
import ch.heig.amt.overflow.application.comment.CommentFacade;
import ch.heig.amt.overflow.application.question.QuestionFacade;
import ch.heig.amt.overflow.application.question.QuestionNotFoundException;
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
    private AnswerFacade answerFacade;
    private CommentFacade commentFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        questionFacade = serviceRegistry.getQuestionFacade();
        answerFacade = serviceRegistry.getAnswerFacade();
        commentFacade = serviceRegistry.getCommentFacade();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionId questionId = new QuestionId(request.getPathInfo().split("/")[1]);
        QuestionsDTO.QuestionDTO questionDTO;

        try {
            // Get question
            questionDTO = questionFacade.getQuestion(questionId);

            // Get question's comments
            questionDTO.setCommentsDTO(commentFacade.getCommentFromMainContentId(questionId));

            // Get question's answers
            questionDTO.setAnswersDTO(answerFacade.getAnswerFromQuestionId(questionId));

            // Get answers' comments
            for (AnswersDTO.AnswerDTO answer : questionDTO.getAnswersDTO().getAnswers()) {
                answer.setCommentsDTO(commentFacade.getCommentFromMainContentId(answer.getAnswerId()));
            }

        } catch (QuestionNotFoundException e) {
            e.printStackTrace();
            request.getSession().setAttribute("flash", FlashMessage.builder()
                    .message(e.getMessage())
                    .type("danger")
                    .build());
            response.sendRedirect(request.getContextPath() + "/questions");
            return;
        }

        request.setAttribute("question", questionDTO);
        request.getRequestDispatcher("/WEB-INF/views/question.jsp").forward(request, response);
        request.getSession().removeAttribute("flash");

    }

}
