/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.application.question;

import ch.heig.amt.overflow.application.auth.UserDTO;
import ch.heig.amt.overflow.domain.question.IQuestionRepository;
import ch.heig.amt.overflow.domain.question.Question;
import ch.heig.amt.overflow.domain.question.QuestionId;
import ch.heig.amt.overflow.domain.user.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class QuestionFacade {

    private final IQuestionRepository questionRepository;

    public QuestionFacade(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    // add new question to the repository throw exception if incomplete
    public void addNewQuestion(NewQuestionCommand command) {
        if(!command.getTitle().isEmpty() && !command.getContent().isEmpty()) {
            Question submittedQuestion = Question.builder()
                    .title(command.getTitle())
                    .content(command.getContent())
                    .author(User.builder().id(command.getAuthorId()).build())
                    .build();
            questionRepository.save(submittedQuestion);
        } else {
            throw new IllegalArgumentException("Le titre et le contenu sont obligatoires");
        }
    }
    // return questionDTO corresponding to the query
    public QuestionsDTO getQuestions(QuestionQuery query) {
        Collection<Question> allQuestions = questionRepository.find(query);

        return QuestionsDTO.builder()
                .questions(mapQuestionDTO(allQuestions))
                .build();
    }
    // return questionDTO with the corresponding ID
    public QuestionsDTO.QuestionDTO getQuestion(QuestionId questionId) throws QuestionNotFoundException {
        Optional<Question> question = questionRepository.findById(questionId);

        if (question.isEmpty()) {
            throw new QuestionNotFoundException("Question non trouvée");
        }

        Collection<Question> allQuestions = new ArrayList<>();
        allQuestions.add(question.get());

        return mapQuestionDTO(allQuestions).get(0);
    }
    // return list of all questionsDTO
    private List<QuestionsDTO.QuestionDTO> mapQuestionDTO(Collection<Question> allQuestions) {
        return allQuestions.stream().map(question ->
                QuestionsDTO.QuestionDTO.builder()
                        .questionId(question.getId())
                        .title(question.getTitle())
                        .content(question.getContent())
                        .createdAt(question.getCreatedAt())
                        .nbVotes(question.getNbVotes())
                        .author(UserDTO.builder()
                                .id(question.getAuthor().getId())
                                .username(question.getAuthor().getUsername())
                                .firstName(question.getAuthor().getFirstName())
                                .lastName(question.getAuthor().getLastName())
                                .email(question.getAuthor().getEmail())
                                .build())
                        .build())
                .collect(Collectors.toList());
    }
}
