package dao;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserDao {

    private JdbcTemplate template;

    @Autowired
    public UserDao(JdbcTemplate template) {
        this.template = template;
    }

    public int getMatchCount(String userName, String password) {

        String query = "SELECT COUNT(*) FROM T_USER WHERE USER_NAME = ? AND PASSWORD = ?";
        return template.queryForObject(query, Integer.class, userName, password);
    }

    public Optional<User> findUserByName(String userName) {

        return template.query("SELECT user_id,user_name,credits FROM T_USER WHERE USER_NAME = ?", resultSet -> {

            resultSet.next();
            User user = new User();
            user.setUserId(resultSet.getInt("user_id"));
            user.setUserName(userName);
            user.setCredits(resultSet.getInt("credits"));
            return Optional.of(user);
        }, userName);
    }

    public void updateLoginInfo(User user) {
        template.update("UPDATE T_USER SET last_visit=?, last_ip=?, credits=? WHERE user_id =?",
                new Object[]{ user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId() });
    }
}
