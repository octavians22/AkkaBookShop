package com.example.orderservice.behaviors;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.http.exceptions.HttpException;
import com.paypal.orders.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderCreateBehavior extends AbstractBehavior<OrderCreateBehavior.Command> {


    private final String RETURN_URL = "http://localhost:8900/payment/success";

    private final String CANCEL_URL = "http://localhost:8900/payment/cancel";

    public static Behavior<Command> create() {
        return Behaviors.setup(OrderCreateBehavior::new);
    }

    private OrderCreateBehavior(ActorContext<Command> context) {
        super(context);
    }

    public PayPalHttpClient createPayPalClient() {
        String clientId = "AZYwM6d4OXtUvc1-RQHn6hd7gg4rHMDWtTp1tdggVz41JJxbQa1Be362JXK9oWixiRbZDQFAEF7fmn6k";
        String clientSecret = "EIzo-Ia54Tbwb-gdlzDANxihaTrU6JUNPPqk2huN_EeqH_RffeGtRSzIfMW6UzO8v_NekziDeVFzeMd-";
        var payPalEnvironment = new PayPalEnvironment.Sandbox(clientId, clientSecret);
        return new PayPalHttpClient(payPalEnvironment);
    }

    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessage(CreateOrderCommand.class, msg -> {
                    OrderRequest orderRequest = new OrderRequest();
                    orderRequest.checkoutPaymentIntent(msg.getIntent());
                    List<PurchaseUnitRequest> purchaseUnits = new ArrayList<>();
                    purchaseUnits
                            .add(new PurchaseUnitRequest().amountWithBreakdown(new AmountWithBreakdown().currencyCode(msg.getCurrencyCode()).value(String.valueOf(msg.getValue()))));
                    orderRequest.purchaseUnits(purchaseUnits);
                    ApplicationContext applicationContext = new ApplicationContext();
                    applicationContext.returnUrl(RETURN_URL);
                    applicationContext.cancelUrl(CANCEL_URL);
                    applicationContext.userAction("PAY_NOW");
                    orderRequest.applicationContext(applicationContext);
                    OrdersCreateRequest request = new OrdersCreateRequest().requestBody(orderRequest);
                    try {
                        PayPalHttpClient payPalHttpClient = createPayPalClient();
                        HttpResponse<Order> response = payPalHttpClient.execute(request);
                        Order order = response.result();
                        System.out.println("Order ID: " + order.id());
                        order.links().forEach(link -> System.out.println(link.rel() + " => " + link.method() + ":" + link.href()));
                        String redirectUrl = order.links().stream()
                                .filter(link -> link.rel().equals("approve"))
                                .findFirst()
                                .orElseThrow(NoSuchElementException::new)
                                .href();
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + redirectUrl);

                    } catch (Exception e) {
                        if (e instanceof HttpException) {
                            HttpException he = (HttpException) e;
                            System.out.println(he.getMessage());
                            he.headers().forEach(x -> System.out.println(x + " :" + he.headers().header(x)));
                        }
                    }

                    return Behaviors.same();
                })
                .build();
    }

    public interface Command {
    }

    public static class CreateOrderCommand implements Command {
        private final String messaje; // start
        private final String intent;  // o sa fie CAPTURE
        private final String currencyCode;
        private final Double value;

        public CreateOrderCommand(String messaje, String intent, String currencyCode, Double value) {
            this.messaje = messaje;
            this.intent = intent;
            this.currencyCode = currencyCode;
            this.value = value;
        }

        public String getMessaje() {
            return messaje;
        }

        public String getIntent() {
            return intent;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public Double getValue() {
            return value;
        }
    }
}
