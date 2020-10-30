/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.domain.vote;

import ch.heig.amt.overflow.domain.ContentId;
import ch.heig.amt.overflow.domain.IRepository;
import ch.heig.amt.overflow.domain.user.UserId;

import java.util.Collection;
import java.util.Optional;

public interface IVoteRepository extends IRepository<Vote, VoteId> {

    Collection<Vote> findByUserId(UserId userId);

    Optional<Vote> findByUserIdAndContentId(UserId userId, ContentId contentId);

}
