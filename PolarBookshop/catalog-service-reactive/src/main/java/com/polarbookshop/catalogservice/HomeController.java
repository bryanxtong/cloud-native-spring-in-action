package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.config.PolarProperties;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HomeController
{
    private PolarProperties properties;

    public HomeController(PolarProperties properties) {
        this.properties = properties;
    }

    public Mono<String> getGreeting(){
        return Mono.just(properties.getGreeting());
    }
}
