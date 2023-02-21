package com.example.wieghtservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weight")
public class WeightController {
    static List<Weight> weights = List.of(
            new Weight("kg", "g", 1000),
            new Weight("kg", "pd", 2.5)
    );

    @GetMapping("/from/{from}/to/{to}")
    public Weight getWeightExchangeValue(@PathVariable String from, @PathVariable String to){
        return weights.stream()
                .filter(w -> w.getFrom().equalsIgnoreCase(from)
                        && w.getTo().equalsIgnoreCase(to))
                .findFirst().get();
    }
}
