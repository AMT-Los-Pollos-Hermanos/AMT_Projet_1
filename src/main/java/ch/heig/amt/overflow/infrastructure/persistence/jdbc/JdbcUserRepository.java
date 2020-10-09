package ch.heig.amt.overflow.infrastructure.persistence.jdbc;

import ch.heig.amt.overflow.domain.user.IUserRepository;
import ch.heig.amt.overflow.domain.user.User;
import ch.heig.amt.overflow.domain.user.UserId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcUserRepository")
public class JdbcUserRepository implements IUserRepository {

    @Resource(lookup = "jdbc/OverflowDS")
    DataSource dataSource;

    public JdbcUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user = null;
        try {
            String sqlQuery = "SELECT * FROM users WHERE username = '?';";
            PreparedStatement statement = dataSource.getConnection().prepareStatement(sqlQuery);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                user = User.builder()
                        .firstName(rs.getString("firstname"))
                        .lastName(rs.getString("lastname"))
                        .email(rs.getString("email"))
                        .username(rs.getString("username"))
                        .encryptedPassword(rs.getString("password"))
                        .build();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (user != null) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void save(User entity) {
        String sqlQuery = "SELECT * FROM users WHERE username = '?';";
    }

    @Override
    public void remove(UserId id) {
        String sqlQuery = "SELECT * FROM users WHERE username = '?';";
    }

    @Override
    public Optional<User> findById(UserId id) {
        String sqlQuery = "SELECT * FROM users WHERE username = '?';";
        return Optional.empty();
    }

    @Override
    public Collection<User> findAll() {
        String sqlQuery = "SELECT * FROM users";
        return null;
    }
}
