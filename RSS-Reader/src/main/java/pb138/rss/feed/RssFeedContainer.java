package pb138.rss.feed;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.log4j.Logger;
import pb138.rss.listener.ContainerChangeListener;

/**
 *
 * @author Martin Drimal
 * @UCO 373769
 */
public class RssFeedContainer implements Container {

    private Logger logger = Logger.getLogger(RssFeedContainer.class);
    private ConcurrentMap<String, RssFeed> feedContainer = new ConcurrentHashMap<>();
    private ContainerChangeListener listener;

    private void FeedContainer() {
        feedContainer = new ConcurrentHashMap<>();
    }

    public void setListener(ContainerChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void putIntoFeedContainer(String key, RssFeed feed) {
        RssFeed rssFeed = feedContainer.get(key);
        boolean added = false;
        if (rssFeed != null) {
            logger.info("Update feed items");
            added = rssFeed.getItems().addAll(feed.getItems());
        } else {
            logger.info("Insert new feed items");
            feedContainer.put(key, feed);
        }
        if (listener != null) {
            listener.containerChanged(this);
        }
    }

    @Override
    public Set<String> getKeys() {
        return feedContainer.keySet();
    }

    @Override
    public RssFeed getFromFeedContainer(String key) {
        return feedContainer.getOrDefault(key, new RssFeed());
    }
}
