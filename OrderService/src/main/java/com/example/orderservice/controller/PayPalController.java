package com.example.orderservice.controller;

import akka.actor.typed.ActorSystem;
import com.example.orderservice.behaviors.OrderCreateBehavior;
import com.example.orderservice.service.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/paypal")
public class PayPalController {

    private final PayPalService payPalService;

    @Autowired
    public PayPalController(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    /*@PostMapping
    public void createOrder() {
        payPalService.createOrder();
    }*/
    @PostMapping()
    public void sendCreateOrderMessage(@RequestBody OrderCreateBehavior.CreateOrderCommand orderCommand) {
        ActorSystem<OrderCreateBehavior.Command> actorSystem = ActorSystem.create(OrderCreateBehavior.create(), "Actor_System");
        actorSystem.tell(orderCommand);
    }
}
