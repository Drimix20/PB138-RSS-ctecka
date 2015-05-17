package pb138.rss.file;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import org.apache.log4j.Logger;
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
        String fileContent = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        fileContent += "<sources>";
        for (String key : feedContainer.getKeys()) {
            RssFeed feed = feedContainer.getFromFeedContainer(key);
            try {
                fileContent += "<source url=\"" + URLEncoder.encode(key, "UTF-8") + "\">";
            } catch (UnsupportedEncodingException ex) {
                logger.error("Incorrect encoding", ex);
                return;
            }
            fileContent += "<title><![CDATA[ " + feed.getTitle() + " ]]></title>";
            fileContent += "<link><![CDATA[" + feed.getLink() + "]]></link>";
            fileContent += "<description><![CDATA[" + feed.getDescription() + "]]></description>";
            fileContent += "<language><![CDATA[" + feed.getLanguage() + "]]></language>";
            fileContent += "<copyright><![CDATA[" + feed.getCopyright() + "]]></copyright>";
            fileContent += "<pubDate><![CDATA[" + feed.getPubDate() + "]]></pubDate>";
            Set<RssFeedItem> items = feed.getItems();
            for (Iterator<RssFeedItem> it = items.iterator(); it.hasNext();) {
                RssFeedItem item = it.next();
                fileContent += "<item>";
                fileContent += "<title><![CDATA[ " + item.getTitle() + " ]]></title>";
                fileContent += "<description><![CDATA[" + item.getDescription() + "]]></description>";
                fileContent += "<link><![CDATA[" + item.getLink() + "]]></link>";
                fileContent += "<date><![CDATA[" + item.getDate() + "]]></date>";
                fileContent += "<author><![CDATA[" + item.getAuthor() + "]]></author>";
                fileContent += "<guid><![CDATA[" + item.getGuid() + "]]></guid>";
                fileContent += "</item>";
            }
            fileContent += "</source>";
        }
        fileContent += "</sources>";
        
        try (PrintWriter writer = new PrintWriter(this.filePath, "UTF-8")) {
            writer.println(fileContent);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            logger.error("Error while writing to file: " + this.filePath , ex);
        }
    }
    
}
