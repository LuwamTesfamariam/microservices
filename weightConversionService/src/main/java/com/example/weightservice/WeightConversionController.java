package com.example.weightservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableDiscoveryClient
@RequestMapping("/weightConversion")
public class WeightConversionController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WeightConversionProxy weightConversionProxy;

    @GetMapping("/from/{from}/to/{to}/amount/{amount}")
    public String conversionResult(@PathVariable String from,@PathVariable String to, @PathVariable int amount){
        Weight weight = restTemplate.getForObject("http://wight-service/weight/from/"+from+"/to/"+to, Weight.class);
        return "The amount of "+amount+from+" to "+to+" is: "+weight.getRate()*amount+" RestTemplateVersion";
    }

    @GetMapping("/from/{from}/to/{to}")
    public String getRate(@PathVariable String from,@PathVariable String to){
        Weight weight = restTemplate.getForObject("http://localhost:8081/weight/from/"+from+"/to/"+to, Weight.class);
        return "The rate from "+from+" to "+to+" is: "+weight.getRate()+" RestTemplateVersion";
    }

    @GetMapping("/from/{from}/to/{to}/amount/{amount}/feign")
    public String conversionResultFeign(@PathVariable String from,@PathVariable String to, @PathVariable int amount){
        Weight weight = weightConversionProxy.getWeightExchangeValue(from, to);
        return "The amount of "+amount+from+" to "+to+" is: "+weight.getRate()*amount+" FeignVersion";
    }
    @GetMapping("/from/{from}/to/{to}/feign")
    public String getRateFeign(@PathVariable String from,@PathVariable String to){
        Weight weight = weightConversionProxy.getWeightExchangeValue(from, to);
        return "The rate from "+from+" to "+to+" is: "+weight.getRate()+" FeignVersion";
    }
}
