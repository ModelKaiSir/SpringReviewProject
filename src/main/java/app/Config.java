package app;

import domain.Boss;
import domain.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sql.DataSource;
import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableAsync
@EnableScheduling
public class Config {


    @Bean
    public List<Car> cars() {

        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < 100; i++) {

            Car c = new Car();
            c.setName("CAR" + i);
            c.setMaxSpeed(i);
            cars.add(c);
        }
        return cars;
    }

    @Scheduled(fixedDelay = 5000)
    public void tips(){

        System.out.println("tips!");
    }
}
