package ch.heig.amt.overflow.domain.vote.status;

public class VoteUp implements VoteStatus {

    @Override
    public String getVote() {
        return "UP";
    }
}
