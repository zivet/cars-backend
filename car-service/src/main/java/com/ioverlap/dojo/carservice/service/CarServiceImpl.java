package com.ioverlap.dojo.carservice.service;

import com.ioverlap.dojo.carservice.domain.Car;
import com.ioverlap.dojo.carservice.domain.CarRepository;
import com.ioverlap.dojo.carservice.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> findAll() {
        return (List<Car>) carRepository.findAll();
    }

    public Car findById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        return optionalCar.orElseThrow(() -> new NotFoundException( "car with id=" + id + " does not exist."));
    }

    public Car save(Car car) {
        carRepository.save(car);
        return car;
    }

    public Car update(Car car) {
        Long id = car.getId();
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isPresent()) {
            Car carToUpdate = optionalCar.get();
            carToUpdate.setDetails(car.getDetails());
            carToUpdate.setLocation(car.getLocation());
            carToUpdate.setPrice(car.getPrice());
            carToUpdate.setCondition(car.getCondition());
            carToUpdate.setModifiedAt(LocalDateTime.now());
            return carRepository.save(carToUpdate);
        }
        throw new NotFoundException("Car with id=" + id + " does not exist.");
    }

    public void delete(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isPresent()) {
            carRepository.delete(optionalCar.get());
            return;
        }
        throw new NotFoundException("Car with id=" + id + " does not exist.");
    }
}