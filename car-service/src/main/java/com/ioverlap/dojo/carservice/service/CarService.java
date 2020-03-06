package com.ioverlap.dojo.carservice.service;

import com.ioverlap.dojo.carservice.domain.Car;

import java.util.List;

public interface CarService {

    List<Car> findAll();

    Car findById(Long id);

    Car save(Car car);

    Car update(Car car);

    void delete(Long id);
}
