package org.example.ExpenseCalculator;

import java.util.HashMap;

public class TripExpenseReportBuilder extends ReportFactory{

    private ExpenseReport report;

    public TripExpenseReportBuilder(){
        this.report = new TripExpenseReport();
    }

    @Override
    public ExpenseReport createReport() {
        return report;
    }

    public TripExpenseReportBuilder setDescription(String description){
        this.report.setDescription(description);
        return this;
    }

    public TripExpenseReportBuilder setTotalExpense(Double amount){
        this.report.setTotalExpense(amount);
        return this;
    }
}
