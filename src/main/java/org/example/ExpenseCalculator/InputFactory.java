package org.example.ExpenseCalculator;

import javax.xml.stream.XMLInputFactory;

public class InputFactory {
    public XMLInputFactory getInstance(String inputType){
        switch (inputType){
            case "XML": return XMLInputFactory.newInstance();
        }
        return null;
    }
}
