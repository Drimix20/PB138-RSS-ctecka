package pb138.rss.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pb138.rss.feed.RssFeed;
import pb138.rss.feed.RssFeedContainer;
import pb138.rss.feed.RssFeedItem;
import pb138.rss.reader.downloader.RssFeedReaderTask;

/**
 *
 * @author Marek Turis
 * @uco 422805
 */
public class RssFileWriter implements Runnable {

    private Logger logger = Logger.getLogger(RssFileWriter.class);
    private RssFeedContainer feedContainer;
    private String filePath;
    
    public RssFileWriter(RssFeedContainer feedContainer, String filePath) {
        this.feedContainer = feedContainer;
        this.filePath = filePath;
    }
    
    
    /**
     * Zapíše obsah do súboru
     */
    @Override
    public void run() {
        logger.info("Writing to file: " + filePath);
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            java.util.logging.Logger.getLogger(RssFileWriter.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        Document doc = docBuilder.newDocument();
        Element sources = doc.createElement("sources");
        doc.appendChild(sources);
        
        for (String key : feedContainer.getKeys()) {
            RssFeed feed = feedContainer.getFromFeedContainer(key);
            Element source = doc.createElement("source");
            try {
                source.setAttribute("url", URLEncoder.encode(key, "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                logger.error("Incorrect encoding", ex);
                return;
            }
            String category = feed.getCategory() != null ? feed.getCategory().getName() : "none";
            source.setAttribute("category", category);
            source.appendChild(this.createCdataElement("title", feed.getTitle(), doc));
            source.appendChild(this.createCdataElement("link", feed.getLink(), doc));
            source.appendChild(this.createCdataElement("description", feed.getDescription(), doc));
            source.appendChild(this.createCdataElement("language", feed.getLanguage(), doc));
            source.appendChild(this.createCdataElement("copyright", feed.getCopyright(), doc));
            source.appendChild(this.createCdataElement("pubDate", feed.getPubDate(), doc));
            Set<RssFeedItem> items = feed.getItems();
            for (Iterator<RssFeedItem> it = items.iterator(); it.hasNext();) {
                RssFeedItem item = it.next();
                Element itemElement = doc.createElement("item");
                itemElement.appendChild(this.createCdataElement("title", item.getTitle(), doc));
                itemElement.appendChild(this.createCdataElement("description", item.getDescription(), doc));
                itemElement.appendChild(this.createCdataElement("link", item.getLink(), doc));
                itemElement.appendChild(this.createCdataElement("date", item.getDate(), doc));
                itemElement.appendChild(this.createCdataElement("author", item.getAuthor(), doc));
                itemElement.appendChild(this.createCdataElement("guid", item.getGuid(), doc));
                source.appendChild(itemElement);
            }
            sources.appendChild(source);
        }
        
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException ex) {
            logger.error(ex);
            return;
        }
        Result output = new StreamResult(new File(this.filePath));
        Source input = new DOMSource(doc);

        try {
            transformer.transform(input, output);
        } catch (TransformerException ex) {
            logger.error(ex);
        }
        
    }
    
    private Element createCdataElement(String name, String data, Document doc) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createCDATASection(data));
        return element;
    }
    
}
