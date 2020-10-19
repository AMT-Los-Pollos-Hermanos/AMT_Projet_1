package ch.heig.amt.overflow.application.comment;

import ch.heig.amt.overflow.domain.MainContentId;
import ch.heig.amt.overflow.domain.user.UserId;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class NewCommentCommand {
    UserId authorId;

    MainContentId mainContentId;

    @Builder.Default
    String content = "No content";
}
