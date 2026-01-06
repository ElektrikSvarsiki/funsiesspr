package org.svarsik.funsiesspr.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.svarsik.funsiesspr.dto.CarResponse;
import org.svarsik.funsiesspr.dto.CreateCarRequest;
import org.svarsik.funsiesspr.dto.UpdateCarRequest;
import org.svarsik.funsiesspr.exceptions.CarNotFoundException;
import org.svarsik.funsiesspr.model.Car;
import org.svarsik.funsiesspr.repository.CarRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    CarRepository carRepository;

    @InjectMocks
    private CarService carService;



    @Test
    void addCar_shouldReturnCarResponse_whenValidRequest() {
        // Arrange
        CreateCarRequest request = new CreateCarRequest("BMW", "X5", 2020);

        Car savedCar = new Car();
        savedCar.setId(1L); // simulate DB-generated ID
        savedCar.setBrand("BMW");
        savedCar.setModel("X5");
        savedCar.setYear(2020);

        when(carRepository.save(any(Car.class))).thenReturn(savedCar);

        // Act
        CarResponse response = carService.addCar(request);

        // Assert
        assertEquals(1L, response.id());
        assertEquals("BMW", response.brand());
        assertEquals("X5", response.model());
        assertEquals(2020, response.year());

        // Verify that save() was called once
        verify(carRepository).save(any(Car.class));
    }



    @Test
    void addCar_shouldThrow_whenBrandIsBlank() {
        // Arrange: brand is blank
        CreateCarRequest request = new CreateCarRequest("", "X5", 2020);

        // Act & Assert: exception thrown
        assertThrows(IllegalArgumentException.class, () -> carService.addCar(request));


        // Verify: save() was never called
        verify(carRepository, never()).save(any());
    }



    @Test
    void addCar_shouldThrow_whenModelIsBlank() {
        // Arrange: model is blank
        CreateCarRequest request = new CreateCarRequest("BMW", "", 2020);

        // Act & Assert: exception thrown
        assertThrows(IllegalArgumentException.class, () -> {
            carService.addCar(request);
        });

        // Verify: save() was never called
        verify(carRepository, never()).save(any());
    }


    @Test
    void deleteCarTest(){

        when(carRepository.findById(1L)).thenReturn(Optional.of(new Car()));

        carService.deleteCar(1L);

        verify(carRepository).delete(any(Car.class));


        verify(carRepository).findById(1L);

    }

    @Test
    void deleteCarNotFoundTest(){

        when(carRepository.findById(1L)).thenReturn(Optional.empty());


        assertThrows(CarNotFoundException.class, () -> carService.deleteCar(1L));

        verify(carRepository, never()).delete(any(Car.class));
    }


    @Test
    void updateCarTest(){
        Car existing = new Car();
        existing.setId(1L);
        when(carRepository.findById(1L)).thenReturn(Optional.of(existing));

        when(carRepository.save(any(Car.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UpdateCarRequest req = new UpdateCarRequest(
                "bmw",
                "x5",
                2020
        );

       CarResponse resp = carService.updateCar(req, 1L);

       assertEquals(1, resp.id());
       assertEquals("bmw", resp.brand());
       assertEquals("x5", resp.model());
       assertEquals(2020, resp.year());

        verify(carRepository).save(any(Car.class));


        verify(carRepository).findById(1L);


    }

    @Test
    void updateCarNotFoundTest(){
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        UpdateCarRequest req = new UpdateCarRequest("bmw", "x5", 2020);

        assertThrows(CarNotFoundException.class, () -> carService.updateCar(req, 1L));

        verify(carRepository, never()).save(any(Car.class));
    }


    @Test
    void getCarByIdTest(){
        Car car = new Car();
        car.setId(1L);
        car.setBrand("BMW");
        car.setModel("X5");
        car.setYear(2020);
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        Car testCar = carService.getCarById(1L);
        assertEquals(car.getId(), testCar.getId());
        assertEquals(car.getBrand(), testCar.getBrand());
        assertEquals(car.getModel(), testCar.getModel());
        assertEquals(car.getYear(), testCar.getYear());
        verify(carRepository).findById(1L);

    }


    @Test
    void getCarByIdNotFoundTest() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CarNotFoundException.class,
                () -> carService.getCarById(1L));

        verify(carRepository).findById(1L);
    }

























}
