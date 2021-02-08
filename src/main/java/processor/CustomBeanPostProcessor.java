package processor;

import domain.Car;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {

        if (o.getClass().isAssignableFrom(Car.class)) {

            System.out.println("修改Car的属性");
            Car c = (Car) o;
            if (c.getMaxSpeed() > 200) {

                c.setMaxSpeed(200);
            }
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {


        if (o.getClass().isAssignableFrom(Car.class)) {

            System.out.println("查漏补缺Car的属性");
            Car c = (Car) o;
            c.setBrand("Jili");
        }
        return o;
    }
}
