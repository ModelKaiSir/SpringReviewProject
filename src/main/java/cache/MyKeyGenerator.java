package cache;

import org.aspectj.weaver.tools.cache.CacheKeyResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MyKeyGenerator implements KeyGenerator{

    @Override
    public Object generate(Object o, Method method, Object... objects) {
        System.out.println(o);
        System.out.println(method.getName());
        System.out.println(Arrays.deepToString(objects));
        return "TEST_KEY";
    }
}
