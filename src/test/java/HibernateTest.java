import dao.UserDao;
import dao.UserHibernateDao;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration("classpath:spring-orm-hibernate.xml")
public class HibernateTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UserHibernateDao userHibernateDao;

    @Test
    @Rollback(false)
    public void add(){

        System.out.println("第一次查询");
        User t = userHibernateDao.load();
        System.out.println(t);

        System.out.println("第二次查询");
        t = userHibernateDao.load();
        System.out.println(t);
    }
}
