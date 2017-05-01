package com.example;

public class Payment {

    public enum Method {
        CASH, PAYPAL, CREDIT
    };

    private Method method;
    private double amount;

    public Method getMethod() {
        return method;
    }

    public double getAmount() {
        return amount;
    }

    public Payment(Method method, double amount) {
        if (method == null) {
            throw new RuntimeException("Method must not be null.");
        }
        if (amount <= 0) {
            throw new RuntimeException("Amount must be positive");
        }
        this.method = method;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Paid %.2f by %s", amount, method);
    }
}
