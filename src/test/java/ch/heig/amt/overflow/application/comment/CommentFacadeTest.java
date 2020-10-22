package ch.heig.amt.overflow.application.comment;

import ch.heig.amt.overflow.domain.MainContentId;
import ch.heig.amt.overflow.domain.comment.Comment;
import ch.heig.amt.overflow.domain.comment.ICommentRepository;
import ch.heig.amt.overflow.domain.user.User;
import ch.heig.amt.overflow.domain.user.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentFacadeTest {

    @Mock
    ICommentRepository repository;

    CommentFacade facade;

    @BeforeEach
    void setUp() {
        facade = new CommentFacade(repository);
    }

    @Test
    void testAddNewComment() {
        UserId id = new UserId();
        MainContentId contentId = new MainContentId();
        NewCommentCommand cmd = NewCommentCommand.builder()
                .authorId(id)
                .content("Mon super commentaire")
                .mainContentId(contentId)
                .build();

        facade.addNewComment(cmd);
        ArgumentCaptor<Comment> captor = ArgumentCaptor.forClass(Comment.class);
        verify(repository).save(captor.capture());
        assertSame(id, captor.getValue().getAuthor().getId());
        assertSame(contentId, captor.getValue().getMainContentId());
        assertEquals("Mon super commentaire", captor.getValue().getContent());
        assertEquals(0, captor.getValue().getNbVotes());
    }

    @Test
    void getCommentFromId() {
        MainContentId id = new MainContentId();
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(Comment.builder()
                .author(User.builder().build())
                .content("Mon commentaire")
                .nbVotes(5)
                .build());
        when(repository.findByMainContentId(id)).thenReturn(comments);
        CommentsDTO result = facade.getCommentFromMainContentId(id);
        List<CommentsDTO.CommentDTO> commentDTO = result.getComments();
        assertEquals(1, commentDTO.size());
        assertEquals("Mon commentaire", commentDTO.get(0).getContent());
        assertEquals(5, commentDTO.get(0).getNbVotes());
        assertNotNull(commentDTO.get(0).getAuthor().getId());
    }

    @Test
    void getCommentNoComment() {
        MainContentId id = new MainContentId();
        ArrayList<Comment> comments = new ArrayList<>();
        when(repository.findByMainContentId(id)).thenReturn(comments);
        CommentsDTO result = facade.getCommentFromMainContentId(id);
        List<CommentsDTO.CommentDTO> commentDTO = result.getComments();
        assertEquals(0, commentDTO.size());
    }

}