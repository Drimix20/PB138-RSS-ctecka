/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pb138.rss.templates;

import java.io.File;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Marek
 */
public class XSLTProcesor {
    
    Transformer xsltProc;
    
    public XSLTProcesor(String xslPath) {
        
        TransformerFactory tf = TransformerFactory.newInstance();
        
        try {
            xsltProc = tf.newTransformer(new StreamSource(new File(xslPath)));
        } catch (TransformerConfigurationException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void transform(String inputPath, String outputPath) throws TransformerException {
        
        xsltProc.transform(
                new StreamSource(new File(inputPath)), 
                new StreamResult(new File(outputPath)));
    }
    
    public String transform(String inputPath) throws TransformerException {
        StringWriter outWriter = new StringWriter();
        StreamResult result = new StreamResult( outWriter );
        xsltProc.transform( 
                new StreamSource(new File(inputPath)), 
                result );  
        StringBuffer sb = outWriter.getBuffer(); 
        return sb.toString();
    }
}
