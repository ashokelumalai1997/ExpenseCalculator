package org.example.ExpenseCalculator;

public interface CurrencyValueProvider {
    Double getCurrencyConversion(String date, String fromCurrency, String toCurrency);
}
