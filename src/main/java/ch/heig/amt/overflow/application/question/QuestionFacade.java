package ch.heig.amt.overflow.application.question;

import ch.heig.amt.overflow.domain.question.IQuestionRepository;
import ch.heig.amt.overflow.domain.question.Question;
import ch.heig.amt.overflow.domain.question.QuestionId;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionFacade {

    private final IQuestionRepository questionRepository;

    public QuestionFacade(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void addNewQuestion(NewQuestionCommand command) {
        Question submittedQuestion = Question.builder()
                .title(command.getTitle())
                .content(command.getContent())
                .author(command.getAuthor())
                .build();
        questionRepository.save(submittedQuestion);
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
