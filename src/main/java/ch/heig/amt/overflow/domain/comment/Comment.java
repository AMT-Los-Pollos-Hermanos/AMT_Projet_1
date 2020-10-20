package ch.heig.amt.overflow.domain.comment;

import ch.heig.amt.overflow.domain.IEntity;
import ch.heig.amt.overflow.domain.MainContentId;
import ch.heig.amt.overflow.domain.user.User;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder(toBuilder = true)
public class Comment implements IEntity<Comment, CommentId> {

    @Builder.Default
    private CommentId id = new CommentId();

    private String content;

    private User author;

    private MainContentId mainContentId;

    private Date createdAt;

    private Date updatedAt;

    @Builder.Default
    private int nbVotes = 0;

    @Override
    public Comment deepClone() {
        return this.toBuilder().id(new CommentId(id.toString())).build();
    }
}
