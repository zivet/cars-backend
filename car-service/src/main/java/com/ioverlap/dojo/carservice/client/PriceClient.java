package com.ioverlap.dojo.carservice.client;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PriceClient {

    @LoadBalanced
    @Bean(name = "restClient")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public String getPrice(Long carId) {
        Price price = restTemplate().getForEntity("http://localhost:8763/price?carId=" + carId, Price.class).getBody();
        return price.getCurrency() + price.getPrice();
    }
}
