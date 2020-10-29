/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.ui.web.comment;

import ch.heig.amt.overflow.application.content.MainContentFacade;
import ch.heig.amt.overflow.domain.MainContentId;
import ch.heig.amt.overflow.domain.answer.Answer;
import ch.heig.amt.overflow.domain.message.FlashMessage;
import ch.heig.amt.overflow.domain.question.Question;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewCommentServlet", urlPatterns = "/comment/*")
public class NewCommentServlet extends HttpServlet {

    @Inject
    MainContentFacade facade;

    // set question or answer to the request
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MainContentId id = new MainContentId(request.getPathInfo().split("/")[1]);
        try {
            Object o = facade.getContentFromId(id);
            Question q = null;
            Answer a = null;
            if (o instanceof Question) {
                q = (Question) o;
            } else if (o instanceof Answer) {
                a = (Answer) o;
            }
            request.setAttribute("question", q);
            request.setAttribute("answer", a);
            request.setAttribute("contentId", id);
            request.setAttribute("questionId", q != null ? q.getId().toString() : a.getQuestionId().toString());
            request.getRequestDispatcher("/WEB-INF/views/comment.jsp").forward(request, response);

        } catch (Exception e) {
            request.getSession().setAttribute("flash", FlashMessage.builder()
                    .message(e.getMessage())
                    .type("danger")
                    .build());
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

}
