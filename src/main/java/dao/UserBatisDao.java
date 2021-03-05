package dao;

import domain.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

public interface UserBatisDao {

    public User loadUser(int userId);
    public User loadUser2(UserBatisQueryParam param);
    public User loadUser3(Map<String,Object> param);
}
