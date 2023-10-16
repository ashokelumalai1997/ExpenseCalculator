package org.example.ExpenseCalculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReportHandlerConsole implements ReportHandler{
    private static final Logger logger = LogManager.getLogger(ReportHandler.class);
    @Override
    public void sendReport(ExpenseReport expenseReport) {
        logger.info("Sending report on total expenses to Console...");
        System.out.println(expenseReport.getReport());
    }
}
