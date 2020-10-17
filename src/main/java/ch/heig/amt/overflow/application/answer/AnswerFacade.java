package ch.heig.amt.overflow.application.answer;

import ch.heig.amt.overflow.application.auth.UserDTO;
import ch.heig.amt.overflow.domain.answer.Answer;
import ch.heig.amt.overflow.domain.answer.IAnswerRepository;
import ch.heig.amt.overflow.domain.question.QuestionId;
import ch.heig.amt.overflow.domain.user.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
                    .questionId(command.getQuestionId())
                    .build();
            answerRepository.save(submittedAnswer);
        } else {
            throw new IllegalArgumentException("Title and content are mandatory");
        }
    }

    public AnswersDTO getAnswerFromQuestionId(QuestionId questionId) {
        Collection<Answer> answers = answerRepository.findByQuestionId(questionId);

        List<AnswersDTO.AnswerDTO> mapper = answers.stream().map(answer ->
                AnswersDTO.AnswerDTO.builder()
                        .title(answer.getTitle())
                        .content(answer.getContent())
                        .createdAt(answer.getCreatedAt())
                        .author(UserDTO.builder()
                                .id(answer.getAuthor().getId())
                                .username(answer.getAuthor().getUsername())
                                .firstName(answer.getAuthor().getFirstName())
                                .lastName(answer.getAuthor().getLastName())
                                .email(answer.getAuthor().getEmail())
                                .build())
                        .build())
                .collect(Collectors.toList());

        return AnswersDTO.builder()
                .answers(mapper)
                .build();
    }
}
