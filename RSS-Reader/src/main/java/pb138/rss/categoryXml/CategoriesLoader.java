/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pb138.rss.categoryXml;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pb138.rss.category.Category;

/**
 *
 * @author Michaela
 */
public class CategoriesLoader {
    
    private Logger logger = Logger.getLogger(CategoriesLoader.class);

    private File inputFile;

    public CategoriesLoader(File inputFile) {
        this.inputFile = inputFile;
    }
    
    public Set<Category> loadCategories() {
        logger.info("Loading categories from file: " + inputFile.getPath());

        Set<Category> categories = new HashSet<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("category");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Category category = new Category(element.getTextContent());
                    categories.add(category);
                }
            }
        } catch (SAXException | ParserConfigurationException | IOException ex) {
            logger.error("Error while reading from file " + inputFile, ex);
            return Collections.EMPTY_SET;
        }

        return categories;
    }
}
