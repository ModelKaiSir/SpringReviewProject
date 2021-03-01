package advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.sql.SQLException;

public class TransManager implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, RuntimeException e) {

        System.out.println("-----------");
        System.out.println("method:" + method.getName());
        System.out.println("抛出异常:" + e.getMessage());
        System.out.println("成功回滚事务。");
    }

    public void afterThrowing(Method method, Object[] args, Object target, SQLException e) {

        System.out.println("-----------");
        System.out.println("method:" + method.getName());
        System.out.println("抛出异常:" + e.getMessage());
        System.out.println("成功回滚事务。");
    }
}
