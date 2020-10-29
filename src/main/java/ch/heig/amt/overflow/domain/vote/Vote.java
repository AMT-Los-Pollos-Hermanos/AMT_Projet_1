/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.domain.vote;

import ch.heig.amt.overflow.domain.ContentId;
import ch.heig.amt.overflow.domain.IEntity;
import ch.heig.amt.overflow.domain.user.UserId;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Vote implements IEntity<Vote, VoteId> {

    @Builder.Default
    private VoteId id = new VoteId();

    private VoteStatus status;

    private UserId userId;

    private ContentId contentId;

    @Override
    public Vote deepClone() {
        return this.toBuilder().id(new VoteId(id.toString())).build();
    }
}
