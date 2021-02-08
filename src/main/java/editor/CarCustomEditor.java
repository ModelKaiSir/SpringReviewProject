package editor;

import domain.Car;
import org.springframework.util.Assert;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;

public class CarCustomEditor extends PropertyEditorSupport {

    /**
     * 使用一个字符串来描述Car信息:
     * CARNAME,BRAND,SPEED
     * 如 吉利博越2019,吉利,200
     *
     * @param text
     * @throws IllegalArgumentException
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        Assert.notNull(text);
        Assert.isTrue(!text.isEmpty());

        String[] carInfo = text.split(",");
        Car car = new Car();
        car.setName(carInfo[0]);
        car.setBrand(carInfo[1]);
        car.setMaxSpeed(Integer.valueOf(carInfo[2]));

        setValue(car);
    }
}
