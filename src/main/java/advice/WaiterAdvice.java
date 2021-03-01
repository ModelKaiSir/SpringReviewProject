package advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class WaiterAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {

        if (objects.length != 0) {

            System.out.println("How are you? " + objects[0]);
        }
    }
}
