package org.svarsik.funsiesspr.configuration;

import org.svarsik.funsiesspr.dto.CarResponse;
import org.svarsik.funsiesspr.model.Car;

import java.util.List;

public interface CarLogger {
    void log(List<CarResponse> cars);
}
