/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.application.vote;

import ch.heig.amt.overflow.domain.ContentId;
import ch.heig.amt.overflow.domain.user.UserId;
import ch.heig.amt.overflow.domain.vote.VoteStatus;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class NewVoteCommand {
    UserId userId;

    ContentId contentId;

    VoteStatus status;
}
