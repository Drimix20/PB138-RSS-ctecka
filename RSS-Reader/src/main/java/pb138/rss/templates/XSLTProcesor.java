/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pb138.rss.templates;

import java.io.File;
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
    
    public XSLTProcesor(String xslPath) throws TransformerConfigurationException {
        
        TransformerFactory tf = TransformerFactory.newInstance();
        
        xsltProc = tf.newTransformer(new StreamSource(new File(xslPath)));
    }
    
    public void transform(String inputPath, String outputPath) throws TransformerException {
        
        xsltProc.transform(
                new StreamSource(new File(inputPath)), 
                new StreamResult(new File(outputPath)));
    }
}
