package ch.heig.amt.overflow.domain.vote;

import ch.heig.amt.overflow.domain.ContentId;
import ch.heig.amt.overflow.domain.IRepository;
import ch.heig.amt.overflow.domain.question.QuestionId;
import ch.heig.amt.overflow.domain.user.UserId;

import java.util.Collection;
import java.util.Optional;

public interface IVoteRepository extends IRepository<Vote, VoteId> {
    Collection<Vote> findByUserId(UserId userId);
    Collection<Vote> findByUserIdAndQuestionId(UserId userId, QuestionId questionId);
    Optional<Vote> findByUserIdAndContentId(UserId userId, ContentId contentId);
}
