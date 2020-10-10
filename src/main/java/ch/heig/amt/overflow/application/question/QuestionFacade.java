package ch.heig.amt.overflow.application.question;

import ch.heig.amt.overflow.domain.question.IQuestionRepository;
import ch.heig.amt.overflow.domain.question.Question;
import ch.heig.amt.overflow.domain.user.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionFacade {

    private final IQuestionRepository questionRepository;

    public QuestionFacade(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void addNewQuestion(NewQuestionCommand command) {
        if(!command.getTitle().isEmpty() && !command.getContent().isEmpty()) {
            Question submittedQuestion = Question.builder()
                    .title(command.getTitle())
                    .content(command.getContent())
                    .author(User.builder().id(command.getAuthor()).build())
                    .build();
            questionRepository.save(submittedQuestion);
        } else {
            throw new IllegalArgumentException("Title and content are mandatory");
        }
    }

    public QuestionsDTO getQuestions(QuestionQuery query) {
        Collection<Question> allQuestions = questionRepository.findAll();

        List<QuestionsDTO.QuestionDTO> allQuestionsDTO = allQuestions.stream().map(question ->
                QuestionsDTO.QuestionDTO.builder()
                        .title(question.getTitle())
                        .content(question.getContent())
                        .build())
                .collect(Collectors.toList());

        return QuestionsDTO.builder()
                .questions(allQuestionsDTO)
                .build();
    }
}
