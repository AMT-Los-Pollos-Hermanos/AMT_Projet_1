package ch.heig.amt.overflow.application.question;

import ch.heig.amt.overflow.domain.question.IQuestionRepository;
import ch.heig.amt.overflow.domain.question.Question;
import ch.heig.amt.overflow.domain.user.User;
import ch.heig.amt.overflow.domain.user.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionFacadeTest {

    @Mock
    IQuestionRepository repository;

    QuestionFacade facade;

    @BeforeEach
    void setUp() {
        facade = new QuestionFacade(repository);
    }

    @Test
    void testAddNewEmptyQuestion() {
        NewQuestionCommand cmd = NewQuestionCommand.builder()
                .authorId(null)
                .title("")
                .content("")
                .build();
        assertThrows(IllegalArgumentException.class, () -> facade.addNewQuestion(cmd));
    }

    @Test
    void testAddNewQuestion() {
        UserId id = new UserId();
        NewQuestionCommand cmd = NewQuestionCommand.builder()
                .authorId(id)
                .title("Why the sky is blue ?")
                .content("Yes")
                .build();

        assertDoesNotThrow(() -> facade.addNewQuestion(cmd));

        ArgumentCaptor<Question> captor = ArgumentCaptor.forClass(Question.class);
        verify(repository).save(captor.capture());

        assertEquals("Why the sky is blue ?", captor.getValue().getTitle());
        assertEquals("Yes", captor.getValue().getContent());
        assertNotNull(captor.getValue().getAuthor());
        assertEquals(id, captor.getValue().getAuthor().getId());
    }

    @Test
    void testGetNoQuestion() {
        when(repository.find(any())).thenReturn(new ArrayList<>());
        QuestionsDTO dto = facade.getQuestions(QuestionQuery.builder().build());
        assertEquals(0, dto.getQuestions().size());
    }

    @Test
    void testGetOneQuestion() {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(Question.builder()
                .title("Hello World")
                .content("This is the content")
                .author(User.builder().build())
                .build());
        when(repository.find(any())).thenReturn(questions);
        QuestionsDTO dto = facade.getQuestions(QuestionQuery.builder().build());
        assertEquals("Hello World", dto.getQuestions().get(0).getTitle());
        assertEquals("This is the content", dto.getQuestions().get(0).getContent());
        assertEquals(1, dto.getQuestions().size());
    }

    @Test
    void testQueryQuestion() {
        QuestionQuery query = QuestionQuery.builder().build();
        facade.getQuestions(query);
        ArgumentCaptor<QuestionQuery> captor = ArgumentCaptor.forClass(QuestionQuery.class);
        verify(repository).find(captor.capture());
        assertSame(query, captor.getValue());
    }

}