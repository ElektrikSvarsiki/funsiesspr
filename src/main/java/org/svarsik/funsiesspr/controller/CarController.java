package org.svarsik.funsiesspr.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.svarsik.funsiesspr.configuration.CarLogger;
import org.svarsik.funsiesspr.dto.CarResponse;
import org.svarsik.funsiesspr.dto.CreateCarRequest;
import org.svarsik.funsiesspr.dto.UpdateCarRequest;
import org.svarsik.funsiesspr.model.Car;
import org.svarsik.funsiesspr.service.CarService;

import java.util.List;

@RestController
public class CarController {

    private final CarService carService;
    private final CarLogger carLogger;
    public CarController(CarService carService, CarLogger carLogger) {
        this.carService = carService;
        this.carLogger = carLogger;
    }


    @GetMapping
    public ResponseEntity<List<CarResponse>> getAllCars() {
        List<CarResponse> cars = carService.getAllCars();
        carLogger.log(cars);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> get(@PathVariable long id) {
        Car car = carService.getCarById(id);

        CarResponse response = new CarResponse(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getproductionYear()
        );
        System.out.println(carService.hashCode());

        return ResponseEntity.ok(response);

    }



    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }


    @PostMapping
    public ResponseEntity<CarResponse> addCar(@Valid @RequestBody  CreateCarRequest createCarRequest) {


        CarResponse savedCar = carService.addCar(createCarRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedCar);


    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateCar(@RequestBody UpdateCarRequest req, @PathVariable Long id) {

        return ResponseEntity.ok(carService.updateCar(req, id));

    }

}
