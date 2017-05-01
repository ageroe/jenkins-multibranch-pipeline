package com.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentTest {

    @Test(expected = RuntimeException.class)
    public void constructorNullMethod() {
        new Payment(null, 100);
    }

    @Test(expected = RuntimeException.class)
    public void constructorInvalidAmount() {
        new Payment(Payment.Method.CASH, 0);
    }

    @Test(expected = RuntimeException.class)
    public void constructorNegativeAmount() {
        new Payment(Payment.Method.CASH, -1);
    }

}