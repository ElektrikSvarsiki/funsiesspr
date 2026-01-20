package org.svarsik.funsiesspr.configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.svarsik.funsiesspr.dto.CarResponse;
import org.svarsik.funsiesspr.model.Car;

import java.util.List;

@Service
@Profile("prod")
public class MinimalCarLogger implements CarLogger {

    @Override
    public void log(List<CarResponse> cars) {
        System.out.println("PROD LOG: count=" + cars.size());
    }
}
