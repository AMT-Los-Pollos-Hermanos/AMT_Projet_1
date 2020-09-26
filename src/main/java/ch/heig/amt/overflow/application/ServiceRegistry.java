package ch.heig.amt.overflow.application;

import ch.heig.amt.overflow.application.question.QuestionFacade;
import ch.heig.amt.overflow.domain.question.IQuestionRepository;
import ch.heig.amt.overflow.infrastructure.persistence.memory.InMemoryQuestionRepository;

public class ServiceRegistry {

    private static ServiceRegistry singleton;

    private static IQuestionRepository questionRepository;
    private static QuestionFacade questionFacade;

    public static ServiceRegistry getServiceRegistry() {
        if(singleton == null) {
            singleton = new ServiceRegistry();
        }
        return singleton;
    }

    private ServiceRegistry() {
        questionRepository = new InMemoryQuestionRepository();
        questionFacade = new QuestionFacade(questionRepository);
    }

    public QuestionFacade getQuestionFacade() {
        return questionFacade;
    }

}
