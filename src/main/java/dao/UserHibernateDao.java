package dao;

import cache.UserCache;
import domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class UserHibernateDao extends BaseHibernateDao<User>{

    @UserCache
    public User load(){

        System.out.println("查找业务数据。");
        return get();
    }

    public User loadNoCache(){

        System.out.println("查找业务数据。");
        return get();
    }

    private User get(){

        System.out.println("从数据库查询。");
        return getTemplate().load(User.class, 1);
    }

    public void sessionCallBack(){

        getTemplate().execute((session) ->{

            return session.createQuery("select count(*) from User t where t.userId = ?").iterate().next();
        });
    }
}
