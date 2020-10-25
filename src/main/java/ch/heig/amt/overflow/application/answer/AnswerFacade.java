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
        if (!command.getContent().isEmpty()) {
            Answer submittedAnswer = Answer.builder()
                    .content(command.getContent())
                    .author(User.builder().id(command.getAuthorId()).build())
                    .questionId(command.getQuestionId())
                    .build();
            answerRepository.save(submittedAnswer);
        } else {
            throw new IllegalArgumentException("Le contenu est obligatoire");
        }
    }

    public AnswersDTO getAnswerFromQuestionId(QuestionId questionId) {
        Collection<Answer> answers = answerRepository.findByQuestionId(questionId);

        List<AnswersDTO.AnswerDTO> mapper = answers.stream().map(answer ->
                AnswersDTO.AnswerDTO.builder()
                        .answerId(answer.getId())
                        .content(answer.getContent())
                        .createdAt(answer.getCreatedAt())
                        .nbVotes(answer.getNbVotes())
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
