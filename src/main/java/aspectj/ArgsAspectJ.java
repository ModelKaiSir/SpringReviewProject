package aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ArgsAspectJ implements BaseAspectJ {

    @Before("args(Object)")
    @Override
    public void beforeAs() {
        System.out.println("args(Object) before.");
    }

    @Before("args(name)")
    public void beforeAsName(String name) {
        System.out.println("args(" + name + ") before.");
    }

    @Before("@args(aspectj.AspectJEnable)")
    public void beforeAsAnnotation() {
        System.out.println("args(AspectJEnable) before.");
    }

    @Override
    public void afterReturning() {

    }

    @Override
    public void aRoundAs() {

    }

    @Override
    public void afterThrowingAs() {

    }

    @Override
    public void afterAs() {

    }
}
