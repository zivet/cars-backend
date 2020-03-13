package com.ioverlap.dojo.carservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioverlap.dojo.carservice.domain.Car;
import com.ioverlap.dojo.carservice.domain.CarRepresentationModel;
import com.ioverlap.dojo.carservice.domain.Condition;
import com.ioverlap.dojo.carservice.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@WebMvcTest
@Import(CarRepresentationModel.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @BeforeEach
    public void setUp() {
        Car car = new Car();
        car.setId(1L);
        car.setCondition(Condition.NEW);
        Mockito.when(carService.findAll()).thenReturn(Arrays.asList(car));
        Mockito.when(carService.findById(1L)).thenReturn(car);
        Mockito.when(carService.save(car)).thenReturn(car);
        Mockito.when(carService.update(car)).thenReturn(car);
    }

    @Test
    public void testAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CarController.CAR))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPost() throws Exception {
        Car car = new Car();
        car.setId(1L);
        car.setCondition(Condition.NEW);
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post(CarController.CAR)
                .content(objectMapper.writeValueAsString(car))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testPut() throws Exception {
        Car car = new Car();
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.put(CarController.CAR + CarController.ID, 1)
                .content(objectMapper.writeValueAsString(car))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGet() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.get(CarController.CAR + CarController.ID, 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.delete(CarController.CAR + CarController.ID, 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
