/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pb138.rss.schema;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 *
 * @author Marek
 */
public class SchemaValidator {
    
    private final String schemaPath;
    
    public SchemaValidator(String schemaPath){
        this.schemaPath = schemaPath;
    }
    
    public boolean valid(String xmlFilename) {
        Source xmlFile = new StreamSource(new File(xmlFilename));
        Source xsdFile = new StreamSource(new File(schemaPath));
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        
        try {
            Schema schema = schemaFactory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            return true;
        } catch (SAXException|IOException e) {
            return false;
        }
    }
}
