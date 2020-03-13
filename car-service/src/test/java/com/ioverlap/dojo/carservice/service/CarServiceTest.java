package com.ioverlap.dojo.carservice.service;

import com.ioverlap.dojo.carservice.domain.Car;
import com.ioverlap.dojo.carservice.domain.CarRepository;
import com.ioverlap.dojo.carservice.domain.Condition;
import com.ioverlap.dojo.carservice.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CarServiceTest {

    public CarService carService;

    private Logger log = LoggerFactory.getLogger(CarServiceTest.class);

    @BeforeEach
    public void setUp() {
        Car car = new Car();
        car.setId(1L);
        car.setCondition(Condition.NEW);
        Optional<Car> optionalCar = Optional.of(car);

        CarRepository carRepositoryMock = Mockito.mock(CarRepository.class);
        Mockito.when(carRepositoryMock.findById(1L)).thenReturn(optionalCar);
        Mockito.when(carRepositoryMock.findById(2L)).thenReturn(Optional.empty());
        Mockito.when(carRepositoryMock.save(car)).thenReturn(car);

        carService = new CarServiceImpl(carRepositoryMock);
    }

    @Test
    public void testFindById() {
        assertNotNull(carService.findById(1L));
    }

    @Test
    public void testFindByIdErrorNotExistId() {
        NotFoundException ex = assertThrows(NotFoundException.class, () ->
            carService.findById(2L));
        log.info(ex.getMessage());
        assertEquals(NotFoundException.class, ex.getClass());
    }

    @Test
    public void testSave() {
        Car car = new Car();
        car.setId(1L);
        assertEquals(car.getId(), carService.save(car).getId());
    }

    @Test
    public void testUpdateCar() {
        Car newCar = new Car();
        newCar.setId(1L);
        newCar.setCondition(Condition.USED);
        assertEquals(Condition.USED, carService.update(newCar).getCondition());
    }

    @Test
    public void testUpdateErrorNotExistId() {
        Car newCar = new Car();
        newCar.setId(4L);
        newCar.setCondition(Condition.USED);

        NotFoundException ex = assertThrows(NotFoundException.class, () ->
                carService.update(newCar));
        log.info(ex.getMessage());
        assertEquals(NotFoundException.class, ex.getClass());
    }

    @Test
    public void testDelete() {
        assertDoesNotThrow(() -> carService.delete(1L));
    }

    @Test
    public void testDeleteErrorNotExistId() {
        NotFoundException ex = assertThrows(NotFoundException.class, () ->
                carService.delete(8L));
        assertEquals(NotFoundException.class, ex.getClass());
    }
}