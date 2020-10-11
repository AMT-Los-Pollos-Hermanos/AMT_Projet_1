package ch.heig.amt.overflow.application.question;

import ch.heig.amt.overflow.domain.question.IQuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class QuestionFacadeTest {

    QuestionFacade facade;

    @Mock
    IQuestionRepository repository;

    @BeforeEach
    public void setUp() {
        facade = new QuestionFacade(repository);
    }

    @Test
    public void testGetQuestion() {
        QuestionsDTO questions = facade.getQuestions(QuestionQuery.builder().build());
    }

}