package org.example.ExpenseCalculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args){

        Properties properties = new Properties();
        try (InputStream resourceInput = Main.class.getResourceAsStream("/config.properties")) {
            properties.load(resourceInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExpenseCalculator expenseCalculator = new ExpenseCalculator();
        String filePath = properties.getProperty("xmlInputFile");
        String targetCurrency = properties.getProperty("outputCurrencyFormat");
        expenseCalculator.calculateExpense(filePath,targetCurrency);
        expenseCalculator.sendReport();
        logger.info("Expense calculation Completed!");
    }
}
