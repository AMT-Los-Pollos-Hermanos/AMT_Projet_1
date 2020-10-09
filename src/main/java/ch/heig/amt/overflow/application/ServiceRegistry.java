package ch.heig.amt.overflow.application;

import ch.heig.amt.overflow.application.auth.AuthFacade;
import ch.heig.amt.overflow.application.question.QuestionFacade;
import ch.heig.amt.overflow.domain.IPasswordEncoder;
import ch.heig.amt.overflow.domain.question.IQuestionRepository;
import ch.heig.amt.overflow.domain.user.IUserRepository;
import ch.heig.amt.overflow.infrastructure.persistence.memory.InMemoryQuestionRepository;
import ch.heig.amt.overflow.infrastructure.security.BCryptPasswordEncoder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class ServiceRegistry {

    IQuestionRepository questionRepository;

    @Inject @Named("JdbcUserRepository")
    IUserRepository userRepository;
    QuestionFacade questionFacade;
    AuthFacade authFacade;
    IPasswordEncoder passwordEncoder;

    private ServiceRegistry() {
        questionRepository = new InMemoryQuestionRepository();
        questionFacade = new QuestionFacade(questionRepository);
        passwordEncoder = new BCryptPasswordEncoder();
        authFacade = new AuthFacade(userRepository);
    }

    public QuestionFacade getQuestionFacade() {
        return questionFacade;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    public IPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}
