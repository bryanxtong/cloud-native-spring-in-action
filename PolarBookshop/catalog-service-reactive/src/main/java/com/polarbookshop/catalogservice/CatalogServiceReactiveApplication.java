package com.polarbookshop.catalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CatalogServiceReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceReactiveApplication.class, args);
    }

}
