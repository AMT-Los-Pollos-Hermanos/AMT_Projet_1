package ch.heig.amt.overflow.application.vote;

import ch.heig.amt.overflow.domain.ContentId;
import ch.heig.amt.overflow.domain.vote.status.VoteStatus;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class VotesDTO {

    @Builder
    @Value
    public static class VoteDTO {
        ContentId contentId;
        VoteStatus status;
    }



    @Singular
    List<VoteDTO> votes;

}
