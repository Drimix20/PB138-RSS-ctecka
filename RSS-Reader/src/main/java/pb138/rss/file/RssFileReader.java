package pb138.rss.file;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import pb138.rss.feed.RssFeed;
import pb138.rss.feed.RssFeedContainer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pb138.rss.reader.downloader.StringRssFeedReader;

/**
 *
 * @author Marek Turis
 * @uco 422805
 */
public class RssFileReader implements Runnable {

    private Logger logger = Logger.getLogger(RssFileWriter.class);
    private RssFeedContainer feedContainer;
    private String filePath;

    public RssFileReader(RssFeedContainer feedContainer, String filePath) {
        this.feedContainer = feedContainer;
        this.filePath = filePath;
    }

    /**
     * Načíta obsah súboru do RssFeedContainera
     */
    @Override
    public void run() {
        logger.info("Reading from file: " + filePath);
        Document doc;
        try {
            doc = newXmlDocument(filePath);
        } catch (SAXException | ParserConfigurationException | IOException ex) {
            logger.error("Error while reading from file " + filePath, ex);
            return;
        }

        NodeList sourceNodes = doc.getElementsByTagName("source");
        for (int i = 0; i < sourceNodes.getLength(); i++) {
            Element sourceElement = (Element) sourceNodes.item(i);
            String text = elementChildsToString(sourceElement);
            String url;
            try {
                url = URLDecoder.decode(sourceElement.getAttribute("url"), "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                logger.error("Incorrect encoding", ex);
                return;
            }

            StringRssFeedReader feedReader = new StringRssFeedReader(url, text);
            RssFeed feed = feedReader.readFeed();
            feedContainer.putIntoFeedContainer(url, feed);
        }
    }

    public static Document newXmlDocument(String fileName) throws SAXException,
            ParserConfigurationException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(fileName);
    }

    public static Document newXmlDocument(File file)
            throws SAXException, ParserConfigurationException, IOException {
        return newXmlDocument(file.toString());
    }

    private String nodeToString(Node node) {
        StringWriter sw = new StringWriter();
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            t.transform(new DOMSource(node), new StreamResult(sw));
        } catch (TransformerException te) {
            logger.error("nodeToString Transformer Exception", te);
            throw new RuntimeException(te);
        }
        return sw.toString();
    }

    private String elementChildsToString(Node node) {
        Element el = (Element) node;
        NodeList chList = el.getChildNodes();
        String str = "";

        for (int i = 0; i < chList.getLength(); i++) {
            str += nodeToString(chList.item(i));
        }

        return str;
    }
}
