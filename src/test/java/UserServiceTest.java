import dao.LoginLogDao;
import domain.LoginLog;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.UserService;

import java.util.Date;
import java.util.Optional;

@ContextConfiguration("classpath*:/spring-context.xml")
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginLogDao loginLogDao;

    @Test
    public void testHasMatchUser(){

        boolean a = userService.hasMatchUser("ADMIN", "ADMIN");
        boolean b = userService.hasMatchUser("ADMIN", "123456");
        Assert.assertTrue(!a);
        Assert.assertTrue(b);
    }

    @Test
    public void findUserByName(){

        Optional<User> user = userService.findUserByName("ADMIN");
        Assert.assertTrue(user.isPresent());
        Assert.assertEquals(user.get().getUserName(), "ADMIN");
    }

    @Test
    public void loginSuccess(){

        Optional<User> user = userService.findUserByName("ADMIN");
        Assert.assertTrue(user.isPresent());
        Assert.assertEquals(user.get().getUserName(), "ADMIN");
        user.get().setLastIp("localhost");
        userService.loginSuccess(user.get());
    }

    @Test
    @Rollback(false)
    public void addLoginLog(){

        LoginLog log = new LoginLog();
        log.setUserId(1);
        log.setIp("localhost");
        log.setLoginDate(new Date());
        loginLogDao.insertLoginLog(log);
    }
}
