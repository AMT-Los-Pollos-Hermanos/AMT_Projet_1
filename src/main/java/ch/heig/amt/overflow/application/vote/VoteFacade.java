package ch.heig.amt.overflow.application.vote;

import ch.heig.amt.overflow.domain.ContentId;
import ch.heig.amt.overflow.domain.question.QuestionId;
import ch.heig.amt.overflow.domain.user.UserId;
import ch.heig.amt.overflow.domain.vote.IVoteRepository;
import ch.heig.amt.overflow.domain.vote.Vote;
import ch.heig.amt.overflow.domain.vote.VoteId;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VoteFacade {

    private final IVoteRepository voteRepository;

    public VoteFacade(IVoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public void addNewVote(NewVoteCommand command) {
        if (hasVoted(command.getUserId(), command.getContentId())) {
            Vote submittedVote = Vote.builder()
                    .status(command.getStatus())
                    .userId(command.getUserId())
                    .contentId(command.getContentId())
                    .build();
            voteRepository.save(submittedVote);
        } else {
            throw new IllegalArgumentException("Status is mandatory");
        }
    }

    public void deleteVote(VoteId voteId) {
        voteRepository.remove(voteId);
    }

    public void deleteVote(UserId userId, ContentId contentId) {
        Optional<Vote> vote = voteRepository.findByUserIdAndContentId(userId, contentId);
        if (vote.isPresent()) {
            voteRepository.remove(vote.get().getId());
        } else {
            throw new RuntimeException("Vote unfound");
        }
    }

    public VotesDTO getUserVotesInQuestionList(UserId userId) {
        return mapVoteDTO(voteRepository.findByUserId(userId));
    }

    public VotesDTO getUserVotesInQuestionPage(UserId userId, QuestionId questionId) {
        return mapVoteDTO(voteRepository.findByUserIdAndQuestionId(userId, questionId));
    }

    public boolean hasVoted(UserId userId, ContentId contentId) {
        return voteRepository.findByUserIdAndContentId(userId, contentId).isPresent();
    }

    private VotesDTO mapVoteDTO(Collection<Vote> votes) {
        List<VotesDTO.VoteDTO> mapper = votes.stream().map(vote ->
                VotesDTO.VoteDTO.builder()
                        .contentId(vote.getContentId())
                        .status(vote.getStatus())
                        .build())
                .collect(Collectors.toList());

        return VotesDTO.builder()
                .votes(mapper)
                .build();
    }
}