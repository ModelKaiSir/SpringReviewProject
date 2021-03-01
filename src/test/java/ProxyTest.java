import advice.ForumService;
import advice.Waiter;
import domain.Boss;
import domain.User;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class ProxyTest {

    @Test
    public void cglib() {

        CglibProxy proxy = new CglibProxy();
        Work work = proxy.getProxy(Work.class);
        work.work();
        work.down();
    }

    public void proxyFactory() {

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(new Work());
        pf.addAdvice(new BeforeWork());

        Work w = (Work) pf.getProxy();
        w.work();
        w.down();
    }

    @Test
    public void beforeAdvice() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        //bean自身生命周期
        Waiter waiter = (Waiter) context.getBean("waiter");
        waiter.greetTo("邱凯");
        waiter.serveTo("邱凯");
        waiter.opps();
    }

    @Test
    public void throwsAdvice() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:beans.xml");
        try {
            //bean自身生命周期
            ForumService service = (ForumService) context.getBean("forum");
            service.updateForum(2);
            service.removeForum(1);
        } catch (Throwable e){

        }
    }

    public static class Work {

        public void down() {
            System.out.println("Im down");
        }

        public void work() {
            System.out.println("Im working");
        }
    }

    public static class BeforeWork implements MethodBeforeAdvice {

        @Override
        public void before(Method method, Object[] objects, Object o) throws Throwable {

            System.out.println("Im working !!!");
        }
    }

    public static class CglibProxy implements MethodInterceptor {

        private Enhancer enhancer = new Enhancer();

        public <T> T getProxy(Class<T> clazz) {

            enhancer.setSuperclass(clazz);
            enhancer.setCallback(this);
            return (T) enhancer.create();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

            System.out.println("proxy start");
            Object r = methodProxy.invokeSuper(o, objects);
            System.out.println("proxy end");
            return r;
        }
    }
}
