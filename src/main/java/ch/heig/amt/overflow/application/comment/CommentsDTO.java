package ch.heig.amt.overflow.application.comment;

import ch.heig.amt.overflow.application.auth.UserDTO;
import ch.heig.amt.overflow.domain.MainContentId;
import ch.heig.amt.overflow.domain.comment.CommentId;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Builder
@Value
public class CommentsDTO {

    @Builder
    @Value
    public static class CommentDTO {
        CommentId commentId;
        MainContentId mainContentId;
        String content;
        Date createdAt;
        UserDTO author;
        int nbVotes;

        public String formattedCreatedAt() {
            DateFormat format = new SimpleDateFormat("'Le 'dd.MM.yyyy' à 'HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("Europe/Zurich"));
            return format.format(createdAt);
        }

    }

    @Singular
    List<CommentDTO> comments;

}
