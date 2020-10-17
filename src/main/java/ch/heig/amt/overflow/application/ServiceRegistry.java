package ch.heig.amt.overflow.application;

import ch.heig.amt.overflow.application.answer.AnswerFacade;
import ch.heig.amt.overflow.application.auth.AuthFacade;
import ch.heig.amt.overflow.application.question.QuestionFacade;
import ch.heig.amt.overflow.domain.answer.IAnswerRepository;
import ch.heig.amt.overflow.domain.question.IQuestionRepository;
import ch.heig.amt.overflow.domain.user.IUserRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class ServiceRegistry {

    @Inject @Named("JdbcQuestionRepository")
    private IQuestionRepository questionRepository;

    @Inject @Named("JdbcUserRepository")
    private IUserRepository userRepository;

    @Inject @Named("JdbcAnswerRepository")
    private IAnswerRepository answerRepository;

    private QuestionFacade questionFacade;
    private AuthFacade authFacade;
    private AnswerFacade answerFacade;

    @PostConstruct
    private void init() {
        authFacade = new AuthFacade(userRepository);
        questionFacade = new QuestionFacade(questionRepository);
        answerFacade = new AnswerFacade(answerRepository);
    }

    public QuestionFacade getQuestionFacade() {
        return questionFacade;
    }
    public AnswerFacade getAnswerFacade() {
        return answerFacade;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

}
