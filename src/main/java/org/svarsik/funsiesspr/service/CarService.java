package org.svarsik.funsiesspr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svarsik.funsiesspr.dto.CarResponse;
import org.svarsik.funsiesspr.dto.CreateCarRequest;
import org.svarsik.funsiesspr.dto.UpdateCarRequest;
import org.svarsik.funsiesspr.exceptions.CarNotFoundException;
import org.svarsik.funsiesspr.model.Car;
import org.svarsik.funsiesspr.repository.CarRepository;

import java.util.Optional;

@Service
@Transactional
public class CarService {

    private void apply(CreateCarRequest req, Car car) {
        // create = all fields required
        car.setBrand(req.brand());
        car.setModel(req.model());
        car.setYear(req.year());
    }

    private void apply(UpdateCarRequest req, Car car) {

         car.setBrand(req.brand());
         car.setModel(req.model());
        car.setYear(req.year());
    }

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car getCarById(long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }






    public CarResponse addCar(CreateCarRequest request) {

        if (request.brand().isBlank() || request.model().isBlank()) {
            throw new IllegalArgumentException("Brand and model are required");
        }

        Car car = new Car();
        car.setBrand(request.brand());
        car.setModel(request.model());
        car.setYear(request.year());

        Car saved = carRepository.save(car);


        return new CarResponse(
                saved.getId(),
                saved.getBrand(),
                saved.getModel(),
                saved.getYear()
        );
    }

    public void deleteCar(long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));


        carRepository.delete(car);
    }

    public CarResponse updateCar(UpdateCarRequest req, long id) {

        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));

        apply(req, car);


        Car saved =  carRepository.save(car);

        return new CarResponse(
                saved.getId(),
                saved.getBrand(),
                saved.getModel(),
                saved.getYear()
        );

    }
}
