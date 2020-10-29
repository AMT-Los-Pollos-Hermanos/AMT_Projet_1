/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.application.answer;

import ch.heig.amt.overflow.application.auth.UserDTO;
import ch.heig.amt.overflow.application.comment.CommentsDTO;
import ch.heig.amt.overflow.domain.answer.AnswerId;
import lombok.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Builder
@Value
public class AnswersDTO {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class AnswerDTO {
        private final AnswerId answerId;
        private final String content;
        private final Date createdAt;
        private final UserDTO author;
        private final int nbVotes;

        @Setter
        private CommentsDTO commentsDTO;

        // initialise the Date createdAt with the current timestamp
        public String formattedCreatedAt() {
            DateFormat format = new SimpleDateFormat("'Le 'dd.MM.yyyy' à 'HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("Europe/Zurich"));
            return format.format(createdAt);
        }

    }



    @Singular
    List<AnswerDTO> answers;

}
