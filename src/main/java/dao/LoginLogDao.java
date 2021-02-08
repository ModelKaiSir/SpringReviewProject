package dao;

import domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;

@Repository
public class LoginLogDao {

    private JdbcTemplate jdbcTemplate;

    /**
     * 保存登陆日志SQL
     */
    private final static String INSERT_LOGIN_LOG_SQL = "INSERT INTO t_login_log(user_id,ip,login_datetime) VALUES(?,?,?)";

    @Autowired
    public LoginLogDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertLoginLog(LoginLog loginLog) {

        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL, loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate());
    }
}