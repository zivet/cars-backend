package com.ioverlap.dojo.carservice.controller;

import com.ioverlap.dojo.carservice.domain.Car;
import com.ioverlap.dojo.carservice.exception.NotFoundException;
import com.ioverlap.dojo.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = CarController.CAR)
public class CarController {

    public static final String CAR = "/cars";

    public static final String ID = "/{id}";

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> all() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping(value = ID)
    public ResponseEntity<Car> get(@PathVariable Long id) {
        return ResponseEntity.ok(carService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Car> post(@Valid @RequestBody Car car) {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost").port(8080).build().encode().toUri();
        return ResponseEntity.created(uri).body(carService.save(car));
    }

    @PutMapping(value = ID)
    public ResponseEntity<Car> put(@PathVariable Long id, @RequestBody Car car) {
        car.setId(id);
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost").port(8080).build().encode().toUri();
        return ResponseEntity.status(HttpStatus.OK).body(carService.update(car));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<Car> delete(@PathVariable Long id) throws NotFoundException {
        carService.delete(id);
        return ResponseEntity.ok().build();
    }
}
