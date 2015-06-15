/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pb138.rss.categoryXml;

import java.io.File;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pb138.rss.category.Category;

/**
 *
 * @author Michaela
 */
public class CategoriesSaver {
    
    private Logger logger = Logger.getLogger(CategoriesSaver.class);

    private File outputFile;

    public CategoriesSaver(File outputFile) {
        this.outputFile = outputFile;
    }
    
    public void saveCategories(Set<Category> categories) {
        logger.info("Writing categories to file: " + outputFile.getPath());

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("categories");
            doc.appendChild(rootElement);

            for (Category category : categories) {
                Element categoryElement = doc.createElement("category");
                categoryElement.appendChild(doc.createTextNode(category.getName()));
                rootElement.appendChild(categoryElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(outputFile);
            transformer.transform(source, result);
            logger.info("Categories saved");
        } catch (Exception ex) {
            logger.error("Error while saving categories", ex);
        }
    }
}
