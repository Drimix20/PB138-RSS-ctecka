package pb138.rss.configuration;


import java.io.IOException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ConfigurationValidator {
    
    public static void main(String[] args){
        ConfigurationValidator validator = new ConfigurationValidator();
        validator.validate();
    }

    public void validate() {
        try {  
            
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

            Schema sch = schemaFactory.newSchema(new StreamSource("src/main/java/pb138/rss/configuration/ConfigurationSchema.xsd"));
            Validator validator = sch.newValidator();

            validator.validate(new StreamSource("src/main/java/pb138/rss/configuration/testconfig.xml")); //nazov suboru nie je finalny :)

           //System.out.println("TEST FILE VALIDATED");

        } catch (SAXException | IOException e) {            
            System.out.println("The configuration file is invalid: "+e.getMessage());
        
        }
    }
}
