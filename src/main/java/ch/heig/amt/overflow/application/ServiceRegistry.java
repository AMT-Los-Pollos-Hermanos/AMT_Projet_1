package ch.heig.amt.overflow.application;

import ch.heig.amt.overflow.application.auth.AuthFacade;
import ch.heig.amt.overflow.application.question.QuestionFacade;
import ch.heig.amt.overflow.domain.question.IQuestionRepository;
import ch.heig.amt.overflow.domain.user.IUserRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class ServiceRegistry {

    @Inject @Named("InMemoryQuestionRepository")
    private IQuestionRepository questionRepository;

    @Inject @Named("InMemoryUserRepository")
    private IUserRepository userRepository;

    private QuestionFacade questionFacade;
    private AuthFacade authFacade;

    @PostConstruct
    private void init() {
        authFacade = new AuthFacade(userRepository);
        questionFacade = new QuestionFacade(questionRepository);
    }

    public QuestionFacade getQuestionFacade() {
        return questionFacade;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

}
