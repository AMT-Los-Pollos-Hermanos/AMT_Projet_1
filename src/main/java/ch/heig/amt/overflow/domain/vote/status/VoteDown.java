package ch.heig.amt.overflow.domain.vote.status;

public class VoteDown implements VoteStatus {
    @Override
    public String getVote() {
        return "DOWN";
    }
}
