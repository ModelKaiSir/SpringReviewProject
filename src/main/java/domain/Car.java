package domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * Spring BeanFactory生命周期
 */
public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

    private String name;
    private int maxSpeed;
    private String brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("call set Field");
        this.name = name;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Car() {

        System.out.println("call Constructor");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

        System.out.println("call BeanFactoryAware");
    }

    @Override
    public void setBeanName(String s) {

        System.out.println("call setBeanName");
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public void destroy() throws Exception {

        System.out.println("call destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println("call afterPropertiesSet");
    }

    public void init(){

        System.out.println("call init");
    }

    public void myDestroy(){

        System.out.println("call my destroy");
    }
}
