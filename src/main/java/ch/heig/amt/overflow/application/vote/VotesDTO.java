/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.application.vote;

import ch.heig.amt.overflow.domain.ContentId;
import ch.heig.amt.overflow.domain.vote.VoteStatus;
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
