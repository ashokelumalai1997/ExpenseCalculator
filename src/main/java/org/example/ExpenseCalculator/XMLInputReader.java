package org.example.ExpenseCalculator;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

public class XMLInputReader implements InputReader {
    private XMLInputFactory inputFactory;
    private XMLStreamReader reader;

    public XMLInputFactory getInputFactory() {
        return inputFactory;
    }

    public void setInputFactory(XMLInputFactory inputFactory) {
        this.inputFactory = inputFactory;
    }

    public XMLStreamReader getReader() {
        return reader;
    }

    public void setReader(XMLStreamReader reader) {
        this.reader = reader;
    }

    public XMLInputReader(XMLInputFactory inputFactory, String input) {
        this.inputFactory = inputFactory;
        // Initialize the reader and load the input here
    }


}
