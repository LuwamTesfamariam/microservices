package com.example.weightservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "Wight-Service", url = "localhost:8081")
public interface WeightConversionProxy {
    @GetMapping("/weight/from/{from}/to/{to}")
    Weight getWeightExchangeValue(@PathVariable String from, @PathVariable String to);
}
