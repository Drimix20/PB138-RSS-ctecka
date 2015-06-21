package pb138.rss.reader.downloader;

import java.util.Objects;
import org.apache.log4j.Logger;
import pb138.rss.feed.Container;
import pb138.rss.feed.RssFeed;

/**
 *
 * @author Martin Drimal
 * @UCO 373769
 */
public class RssFeedReaderTask implements Runnable {

    private Logger logger = Logger.getLogger(RssFeedReaderTask.class);
    private Container feedContainer;
    private RssFeedReader feedReader;
    private String label;
    private long initialDelay;
    private long scheduledDelay;

    public RssFeedReaderTask(String label, RssFeedReader reader, long initialDelay, long scheduledDelay, Container container) {
        this.feedContainer = container;
        this.label = label;
        this.feedReader = reader;
        this.initialDelay = initialDelay;
        this.scheduledDelay = scheduledDelay;
    }

    public void setFeedContainer(Container feedContainer) {
        this.feedContainer = feedContainer;
    }

    public String getAssociatedUrl() {
        return feedReader.getUrl();
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
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.feedReader.getUrl());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RssFeedReaderTask other = (RssFeedReaderTask) obj;
        if (!Objects.equals(this.feedReader.getUrl(), other.feedReader.getUrl())) {
            return false;
        }
        return true;
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

    @Override
    public String toString() {
        return label;
    }
}
