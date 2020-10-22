package ch.heig.amt.overflow.application.vote;

import ch.heig.amt.overflow.domain.ContentId;
import ch.heig.amt.overflow.domain.MainContentId;
import ch.heig.amt.overflow.domain.user.UserId;
import ch.heig.amt.overflow.domain.vote.IVoteRepository;
import ch.heig.amt.overflow.domain.vote.Vote;
import ch.heig.amt.overflow.domain.vote.VoteId;
import ch.heig.amt.overflow.domain.vote.status.VoteDown;
import ch.heig.amt.overflow.domain.vote.status.VoteUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoteFacadeTest {

    @Mock
    IVoteRepository repository;

    VoteFacade facade;

    @BeforeEach
    void setUp() {
        facade = new VoteFacade(repository);
    }

    @Test
    void testAddNewVote() {
        UserId id = new UserId();
        MainContentId contentId = new MainContentId();
        NewVoteCommand cmd = NewVoteCommand.builder()
                .userId(id)
                .ContentId(contentId)
                .status(new VoteDown())
                .build();
        when(repository.findByUserIdAndContentId(any(), any())).thenReturn(Optional.of(Vote.builder().build()));
        facade.addNewVote(cmd);
        ArgumentCaptor<Vote> captor = ArgumentCaptor.forClass(Vote.class);
        verify(repository).save(captor.capture());
        assertSame(id, captor.getValue().getUserId());
        assertSame(contentId, captor.getValue().getContentId());
        assertTrue(captor.getValue().getStatus() instanceof VoteDown);
    }

    @Test
    void testDeleteVote() {
        VoteId id = new VoteId();
        facade.deleteVote(id);
        ArgumentCaptor<VoteId> captor = ArgumentCaptor.forClass(VoteId.class);
        verify(repository).remove(captor.capture());
        assertSame(id, captor.getValue());
    }

    @Test
    void testDeleteVoteFromContent() {
        UserId userId = new UserId();
        ContentId contentId = new ContentId();
        when(repository.findByUserIdAndContentId(any(), any())).thenReturn(Optional.of(Vote.builder().build()));
        facade.deleteVote(userId, contentId);
        ArgumentCaptor<UserId> captor1 = ArgumentCaptor.forClass(UserId.class);
        ArgumentCaptor<ContentId> captor2 = ArgumentCaptor.forClass(ContentId.class);
        verify(repository).findByUserIdAndContentId(captor1.capture(), captor2.capture());
        assertSame(userId, captor1.getValue());
        assertSame(contentId, captor2.getValue());
    }

    @Test
    void testGetUserVotesInQuestionList() {
        UserId userId = new UserId();
        ContentId contentId = new ContentId();
        ArrayList<Vote> votes = new ArrayList<>();
        votes.add(Vote.builder()
                .userId(userId)
                .status(new VoteUp())
                .contentId(contentId)
                .build());
        when(repository.findByUserId(userId)).thenReturn(votes);
        VotesDTO result = facade.getUserVotesInQuestionList(userId);
        verify(repository).findByUserId(userId);
        List<VotesDTO.VoteDTO> voteDTO = result.getVotes();
        assertEquals(1, voteDTO.size());
        assertTrue(voteDTO.get(0).getStatus() instanceof VoteUp);
        assertEquals(contentId, voteDTO.get(0).getContentId());
    }

}