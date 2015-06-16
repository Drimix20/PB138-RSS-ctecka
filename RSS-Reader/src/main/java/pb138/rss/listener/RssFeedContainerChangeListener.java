package pb138.rss.listener;

import java.util.logging.Level;
import javax.xml.transform.TransformerException;
import org.apache.log4j.Logger;
import pb138.rss.converter.ContainerToHtmlConverter;
import pb138.rss.feed.Container;
import pb138.rss.file.RssFileWriter;

/**
 *
 * @author Drimal
 */
public class RssFeedContainerChangeListener implements ContainerChangeListener {

    private static Logger logger = Logger.getLogger(RssFeedContainerChangeListener.class);
    private javax.swing.JTextPane jTextPane;
    private ContainerToHtmlConverter converter;
    private RssFileWriter writer;
    private boolean changeJTextPane = true;

    public RssFeedContainerChangeListener(javax.swing.JTextPane jTextPane, ContainerToHtmlConverter converter, RssFileWriter writer) {
        this.jTextPane = jTextPane;
        this.converter = converter;
        this.writer = writer;
    }
    
    @Override
    public void containerChanged(Container container) {
        logger.info("Container changed, I can write to file");
        Thread thread = new Thread(this.writer);
        thread.start();
        if (changeJTextPane) {
            String html;
            try {
                html = converter.getString();
            } catch (TransformerException ex) {
                throw new RuntimeException(ex);
            }
            this.jTextPane.setText(html);
        }
    }
    
    public void setChangeJTextPane(boolean value) {
        changeJTextPane = value;
    }

}
