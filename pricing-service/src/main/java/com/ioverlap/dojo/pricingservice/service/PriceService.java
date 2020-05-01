package com.ioverlap.dojo.pricingservice.service;

import com.ioverlap.dojo.pricingservice.domain.Price;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class PriceService {

    private static final Map<Long, Price> PRICES = LongStream
            .range(1, 50)
            .mapToObj(i -> new Price("USD", randomPrice(), i))
            .collect(Collectors.toMap(Price::getVehicleId, p -> p));

    public Price getPrice(Long carId) throws PriceException {
        if(!PRICES.containsKey(carId)) {
            throw new PriceException("price to car with id=" + carId + " not found");
        }
        return PRICES.get(carId);
    }

    private static BigDecimal randomPrice() {
        return new BigDecimal(ThreadLocalRandom.current().nextDouble(1, 5))
                .multiply(new BigDecimal(5000d)).setScale(2, RoundingMode.HALF_UP);
    }
}
