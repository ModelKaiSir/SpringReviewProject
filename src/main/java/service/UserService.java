package service;

import dao.LoginLogDao;
import dao.UserDao;
import domain.LoginLog;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import sun.rmi.runtime.Log;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginLogDao loginLogDao;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    public boolean hasMatchUser(String userName, String password) {

        return userDao.getMatchCount(userName, password) > 0;
    }

    public Optional<User> findUserByName(String userName) {

        return userDao.findUserByName(userName);
    }

    @Transactional(rollbackFor = Exception.class)
    public void loginSuccess(User user) {

        user.setCredits(user.getCredits() + 5);
        LoginLog log = new LoginLog();
        log.setUserId(user.getUserId());
        log.setIp(user.getLastIp());
        log.setLoginDate(user.getLastVisit());

        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(log);
    }
}
