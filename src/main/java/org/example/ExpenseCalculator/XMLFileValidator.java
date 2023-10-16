package org.example.ExpenseCalculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;


public class XMLFileValidator {
    private static final Logger logger = LogManager.getLogger(XMLFileValidator.class);

    static SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    static Schema schema;

    static {
        try {
            schema = schemaFactory.newSchema(new StreamSource(XMLFileValidator.class.getResourceAsStream("/xmlschema.xsd")));
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    static Validator validator = schema.newValidator();
    public XMLFileValidator() throws SAXException {
    }
    public static boolean validateFile(String file){


        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            validator.validate(new StreamSource(inputStream));
            logger.info("XML document is valid.");
            return true;
        } catch (SAXException e) {
            logger.error("Validation error: " + e.getMessage());
        } catch (IOException e) {
            logger.error("IO error: " + e.getMessage());
        }

        return false;
    }
}
