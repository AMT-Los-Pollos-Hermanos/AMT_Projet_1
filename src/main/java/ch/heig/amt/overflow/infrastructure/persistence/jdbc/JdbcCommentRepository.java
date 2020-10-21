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
import java.sql.Connection;
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
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement preparedStatement;

            preparedStatement = con.prepareStatement("SELECT COUNT(*) FROM comments WHERE content_id = ?");
            preparedStatement.setString(1, entity.getId().toString());
            ResultSet rs = preparedStatement.executeQuery();

            int size = 0;
            if (rs.next()) {
                size = rs.getInt(1);
            }

            con.setAutoCommit(false);
            if (size == 0) {
                // Create comment
                preparedStatement = con.prepareStatement("INSERT INTO contents (id, user_id, content) VALUES (?, ?, ?);");
                preparedStatement.setString(1, entity.getId().toString());
                preparedStatement.setString(2, entity.getAuthor().getId().toString());
                preparedStatement.setString(3, entity.getContent());
                preparedStatement.executeUpdate();

                preparedStatement = con.prepareStatement("INSERT INTO comments (content_id, main_content_id) VALUES (?, ?);");
                preparedStatement.setString(1, entity.getId().toString());
                preparedStatement.setString(2, entity.getMainContentId().toString());
                preparedStatement.executeUpdate();
            } else {
                // Update comment
                preparedStatement = con.prepareStatement("UPDATE contents SET content = ?, user_id = ? WHERE contents.id = ?;");
                preparedStatement.setString(1, entity.getContent());
                preparedStatement.setString(2, entity.getAuthor().getId().toString());
                preparedStatement.setString(3, entity.getId().toString());
                preparedStatement.executeUpdate();

                preparedStatement = con.prepareStatement("UPDATE comments SET main_content_id = ? WHERE comments.content_id = ?;");
                preparedStatement.setString(1, entity.getMainContentId().toString());
                preparedStatement.setString(2, entity.getId().toString());
                preparedStatement.executeUpdate();
            }
            con.commit();

        } catch (SQLException e) {
            throw new RuntimeException("Error while adding/updating comment to the database");
        }
    }

    @Override
    public void remove(CommentId id) {
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("DELETE FROM contents WHERE id = ?");
            preparedStatement.setString(1, id.toString());
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("No comments deleted, answer with id '" + id.toString() + "' not found in database");
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL error");
        }
    }

    @Override
    public Collection<Comment> findByMainContentId(MainContentId mainContentId) {
        List<Comment> comments = new ArrayList<>();

        try {
            String sql = getQuery("WHERE comments.main_content_id = ?");

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
            String sql = getQuery("WHERE comments.content_id = ?");

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
            String sql = getQuery("");

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
                    .nbVotes(rs.getInt("nb_votes"))
                    .build();
        } catch (ParseException e) {
            e.printStackTrace(); // TODO handle SQL exception
        }
        return null;
    }

    private String getQuery(String condition) {
        return "SELECT comments.content_id, content, created_at, updated_at, " +
                "users.id, username, email, password, last_name, first_name, " +
                "(COUNT(IF(state = 'UP', 1, NULL)) - COUNT(IF(state = 'DOWN', 1, NULL))) AS nb_votes " +
                "FROM comments " +
                "INNER JOIN contents on comments.content_id = contents.id " +
                "INNER JOIN users on contents.user_id = users.id " +
                "LEFT JOIN votes ON contents.id = votes.content_id " +
                condition + " " +
                "GROUP BY comments.content_id";
    }
}
