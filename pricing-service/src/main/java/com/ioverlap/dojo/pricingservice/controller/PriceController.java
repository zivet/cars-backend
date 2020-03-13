package com.ioverlap.dojo.pricingservice.controller;

import com.ioverlap.dojo.pricingservice.domain.Price;
import com.ioverlap.dojo.pricingservice.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = PriceController.PRICE)
public class PriceController {

    public static final String PRICE = "/price";

    @Autowired
    private PriceService priceService;

    @GetMapping
        public ResponseEntity<Price> get(@RequestParam Long carId) {
        try {
            return ResponseEntity.ok(priceService.getPrice(carId));
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
