import dao.UserBatisDao;
import dao.UserBatisQueryParam;
import dao.UserHibernateDao;
import domain.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@ContextConfiguration("classpath:spring-orm-mybatis.xml")
public class MyBatisTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private UserBatisDao dao;

    @Test
    public void selectOne() {

        User user = (User) sqlSessionTemplate.selectOne("dao.UserBatisDao.loadUser", 1);
        System.out.println(user.toString());
    }

    @Test
    public void selectOneMapper() {

        UserBatisDao dao = sqlSessionTemplate.getMapper(UserBatisDao.class);
        System.out.println(dao.loadUser(1).toString());
    }

    @Test
    public void autoSelectOneMapper() {

        UserBatisQueryParam p = new UserBatisQueryParam();
        p.setUserId(1);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 101);

        System.out.println(dao.loadUser(1).toString());
        System.out.println(dao.loadUser(1).toString());
    }
}
