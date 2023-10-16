package org.example.ExpenseCalculator;

import java.util.Map;

public interface ExpenseReport  {
    public String toString();

    void setTotalExpense(Double amount);
    void setDescription(String string);
    void updateExpenseCurrencySplit(String date, String currency, Double amount);
    String getReport();

    Map<String,Double> getExpenseCurrencySplit();

}
