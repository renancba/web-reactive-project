package com.example.mercado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MercadoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MercadoApplication.class, args);
    }

}
