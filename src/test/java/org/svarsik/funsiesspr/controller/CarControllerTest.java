package org.svarsik.funsiesspr.controller;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.svarsik.funsiesspr.dto.CarResponse;
import org.svarsik.funsiesspr.dto.CreateCarRequest;
import org.svarsik.funsiesspr.exceptions.CarNotFoundException;
import org.svarsik.funsiesspr.model.Car;
import org.svarsik.funsiesspr.service.CarService;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarControllerTest {
    @MockitoBean
    private CarService carService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getCarTest() throws Exception {

        Car car = new Car();
        car.setId(1L);
        car.setBrand("BMW");
        car.setModel("X5");
        car.setYear(2020);

        when(carService.getCarById(1L)).thenReturn(car);

      var mockReq =  MockMvcRequestBuilders.get("/1").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockReq).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.brand").value("BMW"))
                .andExpect(jsonPath("$.model").value("X5"))
                .andExpect(jsonPath("$.year").value(2020));

        verify(carService).getCarById(1L);

    }

    @Test
    void getCarNotFoundTest() throws Exception {
        when(carService.getCarById(1L)).thenThrow(new CarNotFoundException(1l));

        var mockReq =  MockMvcRequestBuilders.get("/1").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockReq).andExpect(status().isNotFound());

        verify(carService).getCarById(1L);
    }


    @Test
    void addCarTest() throws Exception {
        CreateCarRequest car = new CreateCarRequest("BMW", "X5", 2020);


        when(carService.addCar(car)).thenReturn(new CarResponse(1L, "BMW", "X5", 2020));

        var req = MockMvcRequestBuilders.post("/").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(car));


        mockMvc.perform(req).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.brand").value("BMW"))
                .andExpect(jsonPath("$.model").value("X5"))
                .andExpect(jsonPath("$.year").value(2020))
        ;

        verify(carService).addCar(car);

    }

}
