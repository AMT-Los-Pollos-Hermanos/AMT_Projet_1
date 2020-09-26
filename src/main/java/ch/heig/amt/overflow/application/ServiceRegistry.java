package ch.heig.amt.overflow.application;

import ch.heig.amt.overflow.application.auth.AuthFacade;
import ch.heig.amt.overflow.application.question.QuestionFacade;
import ch.heig.amt.overflow.domain.IPasswordEncoder;
import ch.heig.amt.overflow.domain.question.IQuestionRepository;
import ch.heig.amt.overflow.domain.user.IUserRepository;
import ch.heig.amt.overflow.infrastructure.persistence.memory.InMemoryQuestionRepository;
import ch.heig.amt.overflow.infrastructure.persistence.memory.InMemoryUserRepository;
import ch.heig.amt.overflow.infrastructure.security.BCryptPasswordEncoder;

public class ServiceRegistry {

    private static ServiceRegistry singleton;

    private final IQuestionRepository questionRepository;
    private final IUserRepository userRepository;
    private final QuestionFacade questionFacade;
    private final AuthFacade authFacade;
    private final IPasswordEncoder passwordEncoder;

    public static ServiceRegistry getServiceRegistry() {
        if(singleton == null) {
            singleton = new ServiceRegistry();
        }
        return singleton;
    }

    private ServiceRegistry() {
        questionRepository = new InMemoryQuestionRepository();
        questionFacade = new QuestionFacade(questionRepository);
        passwordEncoder = new BCryptPasswordEncoder();
        userRepository = new InMemoryUserRepository(passwordEncoder);
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
