package com.example.orderservice;

import akka.actor.typed.ActorSystem;
import com.example.orderservice.behaviors.OrderCreateBehavior;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
        System.out.println("Inside the main method");


    }

}
