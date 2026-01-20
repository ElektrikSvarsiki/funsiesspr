package org.svarsik.funsiesspr.configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.svarsik.funsiesspr.dto.CarResponse;
import org.svarsik.funsiesspr.model.Car;
import java.util.List;

@Profile("dev")
@Service
public class DetailedCarLogger implements CarLogger{
    public void log(List<CarResponse> cars) {
        System.out.println("DEV LOG: total=" + cars.size());
        cars.forEach(System.out::println);
    }
}
