/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pb138.rss.categoryXml;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

/**
 *
 * @author Michaela
 */
public class CategoryValidator {
    
    private static final Logger logger = Logger.getLogger(CategoryValidator.class);
    
    public static void main(String[] args){
        CategoryValidator validator = new CategoryValidator();
        validator.validate();
    }

    public void validate() {
        try {  
            Source xmlFile = new StreamSource(new File("src/main/java/pb138/rss/categoryXml/categories.xml"));
            Source xsdFile = new StreamSource(new File("src/main/java/pb138/rss/categoryXml/CategorySchema.xsd"));
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

            Schema schema = schemaFactory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
        } catch (SAXException | IOException e) {    
            logger.error("The categories file is invalid: " + e.getMessage());     
        }
    }
}
