package pb138.rss.feed;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author Drimal
 */
public class FeedContainer {

    private static FeedContainer instance = null;
    private ConcurrentMap<String, RssFeed> feedContainer = new ConcurrentHashMap<>();

    private void FeedContainer() {
    }

    public static FeedContainer getInstance() {
        if (instance == null) {
            synchronized (FeedContainer.class) {
                if (instance == null) {
                    instance = new FeedContainer();
                }
            }
        }
        return instance;
    }

    public void putIntoFeedContainer(String key, RssFeed feed) {
        feedContainer.put(key, feed);
    }

    public Set<String> getKeys() {
        return feedContainer.keySet();
    }

    public RssFeed getFromFeedContainer(String key) {
        return feedContainer.getOrDefault(key, new RssFeed());
    }
}
