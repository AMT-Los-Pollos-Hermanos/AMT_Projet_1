package ch.heig.amt.overflow.application.answer;

import ch.heig.amt.overflow.domain.answer.Answer;
import ch.heig.amt.overflow.domain.answer.IAnswerRepository;
import ch.heig.amt.overflow.domain.question.QuestionId;
import ch.heig.amt.overflow.domain.user.User;
import ch.heig.amt.overflow.domain.user.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerFacadeTest {

    @Mock
    IAnswerRepository repository;

    AnswerFacade facade;

    @BeforeEach
    void setUp() {
        facade = new AnswerFacade(repository);
    }


    @Test
    void testAddNewAnswer() {
        UserId id = new UserId();
        QuestionId qid = new QuestionId();
        NewAnswerCommand cmd = NewAnswerCommand.builder()
                .authorId(id)
                .content("Ma super réponse")
                .questionId(qid)
                .build();

        facade.addNewAnswer(cmd);
        ArgumentCaptor<Answer> captor = ArgumentCaptor.forClass(Answer.class);
        verify(repository).save(captor.capture());
        assertSame(id, captor.getValue().getAuthor().getId());
        assertSame(qid, captor.getValue().getQuestionId());
        assertEquals("Ma super réponse", captor.getValue().getContent());
        assertEquals(0, captor.getValue().getNbVotes());
    }

    @Test
    void getAnswerFromId() {
        QuestionId id = new QuestionId();
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(Answer.builder()
                .author(User.builder().build())
                .content("Ma réponse")
                .nbVotes(5)
                .questionId(id)
                .build());
        when(repository.findByQuestionId(id)).thenReturn(answers);
        AnswersDTO result = facade.getAnswerFromQuestionId(id);
        List<AnswersDTO.AnswerDTO> answerDTO = result.getAnswers();
        assertEquals(1, answerDTO.size());
        assertEquals("Ma réponse", answerDTO.get(0).getContent());
        assertEquals(5, answerDTO.get(0).getNbVotes());
    }

}