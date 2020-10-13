package ch.heig.amt.overflow.application.answer;

import ch.heig.amt.overflow.domain.answer.Answer;
import ch.heig.amt.overflow.domain.answer.IAnswerRepository;
import ch.heig.amt.overflow.domain.question.Question;
import ch.heig.amt.overflow.domain.user.User;

public class AnswerFacade {

    private final IAnswerRepository answerRepository;

    public AnswerFacade(IAnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public void addNewAnswer(NewAnswerCommand command) {
        if (!command.getTitle().isEmpty() && !command.getContent().isEmpty()) {
            Answer submittedAnswer = Answer.builder()
                    .title(command.getTitle())
                    .content(command.getContent())
                    .author(User.builder().id(command.getAuthorId()).build())
                    .question(Question.builder().id(command.getQuestionId()).build())
                    .build();
            answerRepository.save(submittedAnswer);
        } else {
            throw new IllegalArgumentException("Title and content are mandatory");
        }
    }
}
