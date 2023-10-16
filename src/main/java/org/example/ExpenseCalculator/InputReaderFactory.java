package org.example.ExpenseCalculator;

import javax.xml.stream.XMLInputFactory;

public class InputReaderFactory {
    public static InputReader createInputReader(XMLInputFactory inputFactory, String input) {
        return new XMLInputReader(inputFactory, input);
    }
}
