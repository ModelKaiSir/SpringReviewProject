package aspectj;

import org.aspectj.lang.annotation.*;

@Aspect
public class ExecutionAspectJ implements BaseAspectJ {

    // pointcuts
    /**
     * AspectJTarget接口及其实现类的方法名以to结尾的方法，一个或多个任意类型参数
     */
    @Pointcut("execution(* aspectj.AspectJTarget+.*(..))")
    public void targets(){

    }

    /**
     * AspectJTarget接口及其实现类的方法名以to结尾的方法，没有参数
     */
    @Pointcut("execution(* aspectj.AspectJTarget+.*To())")
    public void targetTo() {

    }

    /**
     * AspectJTarget接口及其实现类的方法名以to结尾的方法，一个String参数（如果为Object+则为Object子类的参数）
     */
    @Pointcut("execution(* aspectj.AspectJTarget+.*To(String))")
    public void targetToString() {

    }

    //advice

    @Before("targets()")
    @Override
    public void beforeAs() {

        System.out.println("ExecutionAspectJ before.");
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
