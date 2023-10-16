package org.example.ExpenseCalculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.text.ParseException;
import java.util.Map;


public class ExpenseCalculator {
    private static final Logger logger = LogManager.getLogger(ExpenseCalculator.class);
    public  ReportHandler reportHandler = new ReportHandlerConsole();
    ExpenseReport report = new TripExpenseReportBuilder().setTotalExpense(0.0).setDescription("Client travel expenses").createReport();
    static Expense expense;

    public void calculateExpense(String filePath, String targetCurrency) {
        CurrencyValueProvider currencyValueProvider = new CurrencyValueProviderClient();
        Boolean isValidXML = XMLFileValidator.validateFile(filePath);
        if(!isValidXML){
            logger.error("Invalid XML file!");
            return;
        }

        try (
                FileInputStream fileInputStream = new FileInputStream(filePath);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        ) {

            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(bufferedInputStream);
            processXML(reader,report);
            calculateTotalExpense(report,currencyValueProvider,targetCurrency);
            logger.info("Total expenses : "+ report.getReport());

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

    }

    private static void calculateTotalExpense(ExpenseReport report, CurrencyValueProvider currencyValueProvider, String targetCurrency) throws ParseException {
        Double totalExpense = 0.0;
        Map<String, Double> expenseSplit = report.getExpenseCurrencySplit();
        for(Map.Entry<String,Double> expenseEntry : expenseSplit.entrySet()){
            String key = expenseEntry.getKey();
            String[] params = key.split("-");
            String date = params[0];
            String currency = params[1];
            Double conversionValue = currencyValueProvider.getCurrencyConversion(date, currency, targetCurrency);
            logger.info("On "+date+", 1 "+currency+" = "+conversionValue+" "+targetCurrency);
            Double value = conversionValue*expenseEntry.getValue();
            totalExpense+=value;
        }
        report.setTotalExpense(totalExpense);
    }

    private static void processXML(XMLStreamReader reader, ExpenseReport report) throws XMLStreamException, ParseException {
        expense = null;
        String currentElement = null;
        while (reader.hasNext()) {
            int event = reader.next();
            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if ("expense".equals(elementName)) {
                        expense = new Expense();
                    }
                    currentElement = elementName;
                    break;
                case XMLStreamConstants.CHARACTERS:
                    if (expense != null && currentElement != null) {
                        String text = reader.getText().trim();
                        if (!text.isEmpty()) {
                            //System.out.println(text);
                            // Populate the expense object based on the element being read
                            switch (currentElement) {
                                case "description":
                                    expense.setDescription(text);
                                    break;
                                case "currencyType":
                                    expense.setCurrency(text);
                                    break;
                                case "amount":
                                    expense.setAmount(Double.parseDouble(text));
                                    break;
                                case "date":
                                    expense.setDate(text);
                                    break;
                            }
                        }
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    String endElementName = reader.getLocalName();
                    if ("expense".equals(endElementName)) {

                        report.updateExpenseCurrencySplit(expense.getDate(), expense.getCurrency(),expense.getAmount());
                        expense = null;
                    }
                    break;
            }
        }
    }

    public void sendReport(){
        reportHandler.sendReport(report);
    }
}

