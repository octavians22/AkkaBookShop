package com.example.orderservice.config;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayPalClientConfig {

    @Value("${client.id}")
    private String clientId;
    @Value("${client.secret}")
    private String clientSecret;

    @Bean
    public PayPalEnvironment payPalEnvironment() {
        return new PayPalEnvironment.Sandbox(clientId, clientSecret);
    }

    @Bean
    public PayPalHttpClient payPalHttpClient() {
        return new PayPalHttpClient(payPalEnvironment());
    }
}
