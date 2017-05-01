package com.example;

public class BigMoneySalesApp {

    public static void main(String[] args){
        Article a1 = new Article(1, "ACME TV 1", 599);
        Article a2 = new Article(2, "ACME RADIO 1", 69);
        Article a3 = new Article(3, "ACME TOASTER", 29);

        Basket basket = new Basket(1.19);
        basket.addBasketEntry(a1, 1);
        basket.addBasketEntry(a2, 4);
        basket.addBasketEntry(a3, 5);

        Payment p1 = new Payment(Payment.Method.PAYPAL, 300);
        Payment p2 = new Payment(Payment.Method.CASH, 200);
        Payment p3 = new Payment(Payment.Method.CREDIT, 100);
        basket.addPayment(p1);
        basket.addPayment(p2);
        basket.addPayment(p3);

        String basketString = basket.toString();

        System.out.println(basketString);
    };

}
