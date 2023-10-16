package org.example.ExpenseCalculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CurrencyValueProviderClient implements CurrencyValueProvider {
    private static final Logger logger = LogManager.getLogger(CurrencyValueProviderClient.class);
    @Override
    public Double getCurrencyConversion(String date, String fromCurrency, String toCurrency) {

        if(fromCurrency.equals(toCurrency)) return (double) 1;
        return 100.0;
    }
}
