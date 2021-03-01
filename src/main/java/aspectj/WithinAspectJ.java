package aspectj;

import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.Ordered;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Aspect
public class WithinAspectJ implements BaseAspectJ, Ordered {

    //pointcuts

    @Autowired
    PlatformTransactionManager manager;

    /**
     * 匹配aspectj包下，以AspectJTarget开头的类 within不会匹配子类，如果标注接口则永远没有匹配的类
     */
    @Pointcut("within(aspectj.AspectJTarget*)")
    public void withinTarget() {

    }

    /**
     * 匹配标注了AspectJEnable注解的所有类及其子类 注解需要可继承
     */
    @Pointcut("@within(aspectj.AspectJEnable)")
    public void withinAspectJEnable() {

    }

    @Pointcut("target(aspectj.AspectJExt)")
    public void aspectJExt() {
    }

    @Pointcut("this(ext) && args(int, String, ..)")
    public void aspectJExts(AspectJExt ext) {
        //int id, String name
    }

    //advice

    @Override
    public void beforeAs() {

        System.out.println("before as withinTarget method");
    }

    @Override
    public void afterReturning() {

        System.out.println("this test");
    }

    @AfterReturning(value = "target(aspectj.AspectJTargetBeanChild)",returning = "num")
    public void afterReturningAs(int num) {

        System.out.printf("return num %s %n", num);
    }

    @Override
    public void aRoundAs() {

    }

    @Around("execution(* service..*(..)) && @annotation(org.springframework.transaction.annotation.Transactional)")
    public void aRoundAs(ProceedingJoinPoint p) throws Throwable {

        System.out.println("within start Around");
        // TransactionInterceptor interceptor = new TransactionInterceptor(manager, new AnnotationTransactionAttributeSource());
        System.out.println("end Around ");
    }

    @Override
    public void afterThrowingAs() {

    }

    @AfterThrowing(value = "target(aspectj.AspectJTargetBeanChild)", throwing = "e")
    public void afterThrowingAs(IllegalArgumentException e) {

        System.out.println("------begin exception------");
        System.out.println(e.getMessage());
        System.out.println("------end exception------");
    }

    @Override
    public void afterAs() {

    }

    @Override
    public int getOrder() {
        return 1;
    }
}
