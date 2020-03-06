package com.ioverlap.dojo.carservice.controller;

import com.ioverlap.dojo.carservice.domain.Car;
import com.ioverlap.dojo.carservice.domain.Condition;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class CarControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAll() {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost").port(port).path(CarController.CAR)
                .build().encode().toUri();
        ResponseEntity<Car[]> responseEntity = restTemplate.getForEntity(uri, Car[].class);
        assertEquals(3, responseEntity.getBody().length);
    }

    @Test
    public void testGet() {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost").port(port)
                .path(CarController.CAR)
                .path(CarController.ID).buildAndExpand(22L)
                .encode().toUri();
        ResponseEntity<Car> responseEntity = restTemplate.getForEntity(uri, Car.class);
        assertEquals(22L, responseEntity.getBody().getId());
    }

    @Test
    public void testPost() {
        Car car = new Car();
        car.setId(1L);
        car.setCondition(Condition.NEW);
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost").port(port)
                .path(CarController.CAR)
                .build()
                .encode().toUri();
        ResponseEntity<Car> responseEntity = restTemplate.postForEntity(uri, car, Car.class);
        assertEquals(1L, responseEntity.getBody().getId());
    }

    @Test
    public void testPostErrorArgumentNotValid() {
        Car car = new Car();
        car.setId(1L);
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost").port(port)
                .path(CarController.CAR)
                .build()
                .encode().toUri();
        ResponseEntity<Car> responseEntity = restTemplate.postForEntity(uri, car, Car.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testDelete() {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost").port(port)
                .path(CarController.CAR)
                .path(CarController.ID)
                .buildAndExpand(20L)
                .encode().toUri();
        RequestEntity<Object> requestEntity = new RequestEntity<>(HttpMethod.DELETE, uri);
        ResponseEntity<Object> responseEntity = restTemplate.exchange(requestEntity, Object.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteErrorNotExistId() {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost").port(port)
                .path(CarController.CAR)
                .path(CarController.ID)
                .buildAndExpand(2L)
                .encode().toUri();
        RequestEntity<Object> requestEntity = new RequestEntity<>(HttpMethod.DELETE, uri);
        ResponseEntity<Object> responseEntity = restTemplate.exchange(requestEntity, Object.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }


}
