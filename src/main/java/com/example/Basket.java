package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Basket containing a list of {@link BasketEntry}.
 * Can calculate total amount of items and total price including VAT.
 */
public class Basket {

    private double vatFactor;
    private List<BasketEntry> entries = new ArrayList<BasketEntry>();
    private List<Payment> payments = new ArrayList<Payment>();

    public List<Payment> getPayments() {
        return payments;
    }

    public double getVatFactor() {
        return vatFactor;
    }

    public void setVatFactor(double vatFactor) {
        this.vatFactor = vatFactor;
    }

    public List<BasketEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<BasketEntry> entries) {
        this.entries = entries;
    }

    public Basket(double vatFactor) {
        if (vatFactor < 1) {
            throw new RuntimeException("Value added tax factor must be greater than 1");
        }
        this.vatFactor = vatFactor;
    }

    /**
     * Calculate total price including VAT
     * @return the total basket price for end-consumer
     */
    public double calculateTotalPrice() {
        double total = .0;
        for (BasketEntry entry : entries) {
            total += entry.calculatePrice();
        }
        double totalWithVat = total * vatFactor;
        return totalWithVat;
    }

    public int calculateTotalAmount() {
        int totalAmount = 0;
        for (BasketEntry entry : entries) {
            totalAmount += entry.getAmount();
        }
        return totalAmount;
    }

    public double calculatePaidPrice() {
        double result = .0;
        for (Payment payment : payments) {
            result += payment.getAmount();
        }
        return result;
    }

    public double calculateOutstandingPrice() {
        return calculateTotalPrice() - calculatePaidPrice();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
                "Basket - total amount of items: %d - total price (incl. VAT): %.2f\n",
                calculateTotalAmount(),
                calculateTotalPrice()
        ));
        sb.append("-----------------------------------------------------------------\n");
        for (BasketEntry entry : entries) {
            sb.append(entry.toString());
            sb.append('\n');
        }
        sb.append("-----------------------------------------------------------------\n");
        sb.append("Payments:\n");
        for (Payment payment : payments) {
            sb.append(payment.toString()).append('\n');
        }
        sb.append(String.format(
                "--- Amount paid: %.2f / Outstanding price: %.2f",
                calculatePaidPrice(),
                calculateOutstandingPrice()
        ));

        return sb.toString();
    }

    public void addBasketEntry(Article article, int amount) {
        if (article == null) {
            throw new RuntimeException("Article is missing!");
        }
        if (amount <= 0) {
            throw new RuntimeException("Amount must be positive");
        }
        BasketEntry entry = new BasketEntry(article, amount);
        entries.add(entry);
    }

    public void addPayment(Payment payment) {
        if (payment.getAmount() > calculateOutstandingPrice()) {
            throw new RuntimeException("Payment cannot be bigger than outstanding price.");
        }
        payments.add(payment);
    }

    /**
     * Bundles an article with an amount
     */
    static class BasketEntry {

        private Article article;
        private int amount;

        public Article getArticle() {
            return article;
        }

        public int getAmount() {
            return amount;
        }

        public BasketEntry(Article article, int amount) {
            this.article = article;
            this.amount = amount;
        }

        public double calculatePrice() {
            return article.getPrice() * amount;
        }

        @Override
        public String toString() {
            return String.format("BasketEntry(amount:%d,article:%s)", amount, article.toString());
        }
    }
}
