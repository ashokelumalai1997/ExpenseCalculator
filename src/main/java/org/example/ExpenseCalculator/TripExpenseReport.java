package org.example.ExpenseCalculator;

import java.util.HashMap;
import java.util.Map;

public class TripExpenseReport implements ExpenseReport {
    private Double totalExpense;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;
    private Map<String, Double> expenseCurrencySplit = new HashMap<>();
    public Double getTotalExpense() {
        return totalExpense;
    }
    public void setTotalExpense(Double amount) {
        this.totalExpense=amount;
    }

    public String getReport() {
        return totalExpense.toString();
    }



    public Map<String, Double> getExpenseCurrencySplit() {
        return expenseCurrencySplit;
    }

    public void updateExpenseCurrencySplit(String date, String currency, Double amount) {
        String key = date.toString() +"-"+ currency;
        this.expenseCurrencySplit.put(key,this.expenseCurrencySplit.getOrDefault(key,Double.valueOf(0))+amount);
    }

}
