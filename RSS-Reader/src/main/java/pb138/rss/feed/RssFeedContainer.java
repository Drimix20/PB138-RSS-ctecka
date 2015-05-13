package pb138.rss.feed;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.log4j.Logger;

/**
 *
 * @author Martin Drimal
 * @UCO 373769
 */
public class RssFeedContainer implements Container {

    private Logger logger = Logger.getLogger(RssFeedContainer.class);
    private ConcurrentMap<String, RssFeed> feedContainer = new ConcurrentHashMap<>();

    private void FeedContainer() {
        feedContainer = new ConcurrentHashMap<>();
    }

    @Override
    public void putIntoFeedContainer(String key, RssFeed feed) {
        RssFeed rssFeed = feedContainer.get(key);
        if (rssFeed != null) {
            logger.info("Update feed items");
            rssFeed.getItems().addAll(feed.getItems());
        } else {
            logger.info("Insert new feed items");
            feedContainer.put(key, feed);
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
