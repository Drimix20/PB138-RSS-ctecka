package pb138.rss.configuration;

import java.io.File;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pb138.rss.feed.Container;
import pb138.rss.reader.downloader.RssFeedReader;
import pb138.rss.reader.downloader.RssFeedReaderTask;

/**
 *
 * @author Drimal
 */
public class ConfigurationLoader {

    private Logger logger = Logger.getLogger(ConfigurationLoader.class);

    private File file;
    private Container container;

    public ConfigurationLoader(File file, Container container) {
        this.file = file;
        this.container = container;
    }

    public List<RssFeedReaderTask> loadConfiguration() {
        logger.info("Loading configuration from file: " + file.getPath());

        List<RssFeedReaderTask> configuration = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("source");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    RssFeedReader reader = new RssFeedReader(element.getElementsByTagName("url").item(0).getTextContent());
                    RssFeedReaderTask task = new RssFeedReaderTask(
                            element.getAttribute("name"),
                            reader,
                            parseValue(element.getElementsByTagName("init").item(0).getTextContent()),
                            parseValue(element.getElementsByTagName("refresh").item(0).getTextContent()),
                            container);
                    configuration.add(task);
                }
            }
        } catch (SAXException | ParserConfigurationException | IOException ex) {
            logger.error("Error while reading from file " + file, ex);
            return Collections.EMPTY_LIST;
        }

        //TODO load configuration
        return configuration;
    }

    private int parseValue(String val) {
        return Integer.parseInt(val);
    }
}
