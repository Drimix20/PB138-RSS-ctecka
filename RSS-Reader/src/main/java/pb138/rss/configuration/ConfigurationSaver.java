package pb138.rss.configuration;

import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pb138.rss.reader.downloader.RssFeedReaderTask;

/**
 *
 * @author Drimal
 */
public class ConfigurationSaver {

    private Logger logger = Logger.getLogger(ConfigurationLoader.class);

    private File outputFile;

    public ConfigurationSaver(File file) {
        this.outputFile = file;
    }

    public void saveConfiguration(List<RssFeedReaderTask> configuration) {
        logger.info("Writing configuration to file: " + outputFile.getPath());

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("sources");
            doc.appendChild(rootElement);

            for (RssFeedReaderTask task : configuration) {
                Element sourceElement = doc.createElement("source");
                rootElement.appendChild(sourceElement);

                Attr attr = doc.createAttribute("name");
                attr.setValue(task.getLabel());
                sourceElement.setAttributeNode(attr);

                Element urlElement = doc.createElement("url");
                urlElement.appendChild(doc.createTextNode(task.getAssociatedUrl()));
                sourceElement.appendChild(urlElement);

                Element init = doc.createElement("init");
                init.appendChild(doc.createTextNode("0"));
                sourceElement.appendChild(init);

                Element refreshElement = doc.createElement("refresh");
                refreshElement.appendChild(doc.createTextNode(task.getScheduledDelay() + ""));
                sourceElement.appendChild(refreshElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(outputFile);
            transformer.transform(source, result);
            logger.info("Configuration saved");
        } catch (Exception ex) {
            logger.error("Error while saving configration", ex);
        }
    }

}
