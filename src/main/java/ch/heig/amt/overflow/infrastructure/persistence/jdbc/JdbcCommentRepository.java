package ch.heig.amt.overflow.infrastructure.persistence.jdbc;

import ch.heig.amt.overflow.domain.MainContentId;
import ch.heig.amt.overflow.domain.comment.Comment;
import ch.heig.amt.overflow.domain.comment.CommentId;
import ch.heig.amt.overflow.domain.comment.ICommentRepository;
import ch.heig.amt.overflow.domain.user.User;
import ch.heig.amt.overflow.domain.user.UserId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@ApplicationScoped
@Named("JdbcCommentRepository")
public class JdbcCommentRepository implements ICommentRepository {

    @Resource(lookup = "jdbc/OverflowDS")
    private DataSource dataSource;

    @Override
    public void save(Comment entity) {
        // TODO implement

    }

    @Override
    public void remove(CommentId id) {
        // TODO implement

    }

    @Override
    public Collection<Comment> findByMainContentId(MainContentId mainContentId) {
        List<Comment> comments = new ArrayList<>();

        try {
            String sql = "SELECT * FROM comments " +
                    "INNER JOIN contents on comments.content_id = contents.id " +
                    "INNER JOIN users on contents.user_id = users.id " +
                    "WHERE comments.main_content_id = ?";

            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
            statement.setString(1, mainContentId.toString());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                comments.add(resultToComment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // TODO handle SQL exception
        }
        return comments;
    }

    @Override
    public Optional<Comment> findById(CommentId id) {
        Comment comment = null;

        try {
            String sql = "SELECT * FROM comments " +
                    "INNER JOIN contents on comments.content_id = contents.id " +
                    "INNER JOIN users on contents.user_id = users.id " +
                    "WHERE comments.content_id = ?";

            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
            statement.setString(1, id.toString());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                comment = resultToComment(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // TODO handle SQL exception
        }

        if (comment != null) {
            return Optional.of(comment);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Collection<Comment> findAll() {
        List<Comment> comments = new ArrayList<>();

        try {
            String sql = "SELECT * FROM comments " +
                    "INNER JOIN contents on comments.content_id = contents.id " +
                    "INNER JOIN users on contents.user_id = users.id";

            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                comments.add(resultToComment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // TODO handle SQL exception
        }
        return comments;
    }

    private Comment resultToComment(ResultSet rs) throws SQLException {
        Date updateAt = null;
        DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            if (rs.getString("updated_at") != null) {
                updateAt = utcFormat.parse(rs.getString("updated_at"));
            }
            return Comment.builder()
                    .id(new CommentId(rs.getString("comments.content_id")))
                    .author(User.builder()
                            .id(new UserId(rs.getString("users.id")))
                            .username(rs.getString("username"))
                            .email(rs.getString("email"))
                            .encryptedPassword(rs.getString("password"))
                            .lastName(rs.getString("last_name"))
                            .firstName(rs.getString("first_name"))
                            .build())
                    .content(rs.getString("content"))
                    .createdAt(utcFormat.parse(rs.getString("created_at")))
                    .updatedAt(updateAt)
                    .build();
        } catch (ParseException e) {
            e.printStackTrace(); // TODO handle SQL exception
        }
        return null;
    }
}
