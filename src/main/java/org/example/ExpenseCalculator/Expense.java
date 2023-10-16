package org.example.ExpenseCalculator;

import java.text.ParseException;

public class Expense {
    private String description;
    private String currency;
    private double amount;
    private String date;

    // Getters and setters for the fields
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) throws ParseException {

        this.date = date;
    }

    @Override
    public String toString() {
        return "org.example.ExpenseCalculator.ExpenseCalculator.Expense{" +
                "description='" + description + '\'' +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}
