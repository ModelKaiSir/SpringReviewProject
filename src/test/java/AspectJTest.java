import aspectj.*;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class AspectJTest {

    @Test
    public void aspectJProxyFactory() {

        AspectJProxyFactory apf = new AspectJProxyFactory();

        apf.setTarget(new AspectJTargetBean());
        apf.addAspect(ExecutionAspectJ.class);
        AspectJTarget b = (AspectJTarget) apf.getProxy();
        b.start();
    }

    @Test
    public void aspectJ() {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-aop.xml");
        AspectJTargetBeanChild child = context.getBean("targetBeanChild", AspectJTargetBeanChild.class);

        child.beginTo();
        child.child();
        child.endTo(1, "张三");
        if (child instanceof AspectJExt) {

            ((AspectJExt) child).ext();
        }
        try {
            child.test(-9);
        } catch (Throwable e) {

            e.printStackTrace();
        }
    }
}
