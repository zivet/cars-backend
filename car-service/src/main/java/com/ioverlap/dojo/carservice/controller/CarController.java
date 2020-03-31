package com.ioverlap.dojo.carservice.controller;

import com.ioverlap.dojo.carservice.domain.Car;
import com.ioverlap.dojo.carservice.domain.CarRepresentationModel;
import com.ioverlap.dojo.carservice.exception.NotFoundException;
import com.ioverlap.dojo.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = CarController.CAR)
public class CarController {

    public static final String CAR = "/cars";

    public static final String ID = "/{id}";

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepresentationModel carRepresentationModel;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel>> all() {
        List<EntityModel> entityModelList = carService.findAll().stream().map(carRepresentationModel::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel> collectionModel = new CollectionModel<>(entityModelList, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CarController.class).all()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping(value = ID)
    public ResponseEntity<EntityModel> get(@PathVariable Long id) {
        return ResponseEntity.ok(carRepresentationModel.toModel(carService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<EntityModel> post(@Valid @RequestBody Car car) throws URISyntaxException {
        EntityModel<Car> entityModel = carRepresentationModel.toModel(carService.save(car));
        return ResponseEntity.created(new URI(entityModel.getRequiredLink(IanaLinkRelations.SELF).expand().getHref()))
                .body(entityModel);
    }

    @PutMapping(value = ID)
    public ResponseEntity<EntityModel> put(@PathVariable Long id, @RequestBody Car car) {
        car.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(carRepresentationModel.toModel(carService.update(car)));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<Object> delete(@PathVariable Long id) throws NotFoundException {
        carService.delete(id);
        return ResponseEntity.ok().build();
    }
}
