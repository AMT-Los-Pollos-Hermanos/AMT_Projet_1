package ch.heig.amt.overflow.ui.web;

import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.answer.AnswerFacade;
import ch.heig.amt.overflow.application.question.QuestionFacade;
import ch.heig.amt.overflow.application.question.QuestionQuery;
import ch.heig.amt.overflow.application.question.QuestionsDTO;
import ch.heig.amt.overflow.domain.answer.Answer;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AnswerTmpServlet", urlPatterns = "/tmp")
public class AnswerTmpServlet extends HttpServlet {

    @Inject
    private ServiceRegistry serviceRegistry;
    private QuestionFacade questionFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        questionFacade = serviceRegistry.getQuestionFacade();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.write("dwa\n");

        QuestionsDTO questionsDTO = questionFacade.getQuestions(QuestionQuery.builder().build());

    }
}
