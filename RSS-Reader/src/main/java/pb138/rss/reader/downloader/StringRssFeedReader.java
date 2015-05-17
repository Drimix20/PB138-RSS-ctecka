package pb138.rss.reader.downloader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import pb138.rss.reader.downloader.RssFeedReader;

/**
 *
 * @author Marek Turis
 * @uco 422805
 */
public class StringRssFeedReader extends RssFeedReader {
    
    private String xmlStr;
    
    public StringRssFeedReader(String address, String xmlStr) {
        super(address);
        this.xmlStr = "<rss xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:atom=\"http://www.w3.org/2005/Atom\" version=\"2.0\"><channel>" + 
           xmlStr + "</channel></rss>";
    }
    
    @Override
    public InputStream read() {
        InputStream stream = new ByteArrayInputStream(xmlStr.getBytes(StandardCharsets.UTF_8));
        return stream;
    }
}
