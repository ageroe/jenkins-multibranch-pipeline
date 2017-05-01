package com.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BasketTest {

    private double vatFactor = 1.19;

    private Article articleTv;
    private Article articleRadio;
    private Article articleOven;

    private Basket basket;

    @Before
    public void setUp() throws Exception {
        articleTv = new Article(100, "SuperTv", 749.99);
        articleRadio = new Article(101, "SuperRadio", 74.99);
        articleOven = new Article(102, "SuperOven", 980);

        basket = new Basket(vatFactor);
        basket.addBasketEntry(articleTv, 1);
        basket.addBasketEntry(articleRadio, 2);
        basket.addBasketEntry(articleOven, 1);
    }

    @Test
    public void testCalculateTotalPrice() throws Exception {
        assertEquals(2237.1643, basket.calculateTotalPrice(), .001);
    }

    @Test
    public void testCalculateOutstandingPrice() throws Exception {
        assertEquals(2237.1643, basket.calculateOutstandingPrice(), .001);
    }

    @Test
    public void testCalculateOutstandingPriceOnePayment() throws Exception {
        basket.addPayment(new Payment(Payment.Method.CASH, 100));
        assertEquals(2137.1643, basket.calculateOutstandingPrice(), .001);
    }

    @Test
    public void testCalculatePaidPriceTwoPayments() throws Exception {
        basket.addPayment(new Payment(Payment.Method.CASH, 100));
        basket.addPayment(new Payment(Payment.Method.CREDIT, 99.99));
        assertEquals(199.99, basket.calculatePaidPrice(), .001);
    }

    @Test
    public void testCalculateTotalAmount() throws Exception {
        assertEquals(4, basket.calculateTotalAmount());
    }

    @Test(expected = RuntimeException.class)
    public void testAddBasketEntryInvalidAmount() throws Exception {
        basket.addBasketEntry(articleTv, 0);
    }

    @Test(expected = RuntimeException.class)
    public void testAddBasketEntryInvalidArticle() throws Exception {
        basket.addBasketEntry(null, 1);
    }

}