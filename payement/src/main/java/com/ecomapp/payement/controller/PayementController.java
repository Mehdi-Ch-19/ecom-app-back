package com.ecomapp.payement.controller;

import com.ecomapp.payement.dto.CreatePaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayementController {

    public CreatePaymentResponse createPayementIntent() throws StripeException {
        Stripe.apiKey = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";

            PaymentIntentCreateParams params =
                    PaymentIntentCreateParams.builder()
                            .setAmount(15*100L)
                            .setCurrency("usd")
                            .setAutomaticPaymentMethods(
                                    PaymentIntentCreateParams.AutomaticPaymentMethods
                                            .builder()
                                            .setEnabled(true)
                                            .build()
                            )
                            .build();

            // Create a PaymentIntent with the order amount and currency
            PaymentIntent paymentIntent = PaymentIntent.create(params);

             return new CreatePaymentResponse(paymentIntent.getClientSecret());
        }


}
