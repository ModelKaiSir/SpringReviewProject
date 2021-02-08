package domain;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class Boss {

    private String name;
    @Value("#{cars}")
    private List<Car> cars;
    private Car niceCar;

    public Boss(String name) {
        this.name = name;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars;
    }

    public String getName() {
        return name;
    }

    public void setNiceCar(Car niceCar) {
        this.niceCar = niceCar;
    }

    public Car getNiceCar() {
        return niceCar;
    }
}
