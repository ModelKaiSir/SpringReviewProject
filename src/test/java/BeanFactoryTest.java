import domain.Boss;
import domain.Car;
import domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;
import processor.CustomBeanPostProcessor;
import processor.CustomInstantiationAwareBeanPostProcessor;

import java.beans.PropertyDescriptor;
import java.util.List;

public class BeanFactoryTest {

    @Test
    public void beanFactory() {

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:beans.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);

        factory.addBeanPostProcessor(new CustomInstantiationAwareBeanPostProcessor());
        factory.addBeanPostProcessor(new CustomBeanPostProcessor());
        //bean自身生命周期
        Car car = factory.getBean(Car.class);
        System.out.println(car.getName());
        System.out.println(car.getMaxSpeed());

        //销毁管理的单例实例
        factory.destroySingletons();
    }

    @Test
    public void applicationContext() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        //bean自身生命周期
        Boss boss = (Boss) context.getBean("myBoss");
        System.out.println(boss.getNiceCar().getName());
        System.out.println(boss.getNiceCar().getBrand());
        System.out.println(boss.getNiceCar().getMaxSpeed());
        System.out.println(boss.getCars().size());
        //销毁管理的单例实例
        context.destroy();
    }

    @Test
    public void resource() throws Exception {

        //从classPath下面找文件，不支持表达式
        ClassPathResource resource = new ClassPathResource("spring-context.xml");
        System.out.println(resource.getDescription());
        System.out.println(resource.getFile().getAbsolutePath());

        //支持资源地址表达式和ant表达式
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        Resource[] resources = resolver.getResources("classpath:spring*.xml");

        for (Resource r : resources) {

            System.out.println(r.getDescription());
            System.out.println(r.getFile().getAbsolutePath());
        }
    }
}
