package pb138.rss.configuration;


import java.io.IOException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import org.apache.log4j.Logger;

import org.xml.sax.SAXException;
import pb138.rss.gui.ReaderUI;

public class ConfigurationValidator {
    
    private Logger logger = Logger.getLogger(ConfigurationLoader.class);
    
    public static void main(String[] args){
        File configuration = new File("target/configuration.xml");
        ConfigurationValidator validator = new ConfigurationValidator();
        validator.validate(configuration);
    }

    public void validate(File configuration) {
        try {  
            
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

            Schema sch = schemaFactory.newSchema(new StreamSource("src/main/java/pb138/rss/configuration/ConfigurationSchema.xsd"));
            Validator validator = sch.newValidator();

            validator.validate(new StreamSource(configuration)); 

            logger.info("Configuration file validated!");

        } catch (SAXException | IOException e) {            
            System.out.println("The configuration file is invalid: "+e.getMessage());
        
        }
    }
}
