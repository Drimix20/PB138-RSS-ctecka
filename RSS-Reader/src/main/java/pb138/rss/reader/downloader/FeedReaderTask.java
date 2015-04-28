package pb138.rss.reader.downloader;

import pb138.rss.feed.FeedContainer;
import pb138.rss.feed.RssFeed;
import org.apache.log4j.Logger;

/**
 *
 * @author Drimal
 */
public class FeedReaderTask implements Runnable {

    private Logger logger = Logger.getLogger(FeedReaderTask.class);
    private FeedContainer feedContainer;
    private FeedReader feedReader;
    private String label;
    private long initialDelay;
    private long scheduledDelay;

    public FeedReaderTask(String label, FeedReader reader, long initialDelay, long scheduledDelay) {
        feedContainer = FeedContainer.getInstance();
        this.label = label;
        this.feedReader = reader;
        this.initialDelay = initialDelay;
        this.scheduledDelay = scheduledDelay;
    }

    public String getLabel() {
        return label;
    }

    public long getInitialDelay() {
        return initialDelay;
    }

    public long getScheduledDelay() {
        return scheduledDelay;
    }

    @Override
    public void run() {
        try {
            RssFeed feed = feedReader.readFeed();
            feedContainer.putIntoFeedContainer(feedReader.getUrl(), feed);
            logger.info(label + "- messages.size: " + feed.getItems().size());
        } catch (Exception ex) {
            logger.error("Error while task", ex);
        }
    }

}
