package cache;

import org.springframework.cache.annotation.Cacheable;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Cacheable(cacheNames = "users", keyGenerator = "myKeyGenerator")
public @interface UserCache {

}
