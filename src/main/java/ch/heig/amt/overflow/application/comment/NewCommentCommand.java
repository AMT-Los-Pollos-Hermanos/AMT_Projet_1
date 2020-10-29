/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

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
