package advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class WaiterAfterAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {

        if (objects.length != 0) {

            System.out.println("join your dinner " + objects[0]);
        }
    }
}
