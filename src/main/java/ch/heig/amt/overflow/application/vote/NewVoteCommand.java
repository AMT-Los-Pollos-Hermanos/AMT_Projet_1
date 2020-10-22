package ch.heig.amt.overflow.application.vote;

import ch.heig.amt.overflow.domain.ContentId;
import ch.heig.amt.overflow.domain.user.UserId;
import ch.heig.amt.overflow.domain.vote.status.VoteStatus;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class NewVoteCommand {
    UserId userId;

    // TODO: Rename
    ContentId ContentId;

    VoteStatus status;
}
