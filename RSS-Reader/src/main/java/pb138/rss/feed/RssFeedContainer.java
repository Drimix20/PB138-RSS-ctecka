package pb138.rss.feed;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author Drimal
 */
public class RssFeedContainer implements Container {

    private ConcurrentMap<String, RssFeed> feedContainer = new ConcurrentHashMap<>();

    private void FeedContainer() {
        feedContainer = new ConcurrentHashMap<>();
    }

    @Override
    public void putIntoFeedContainer(String key, RssFeed feed) {
        feedContainer.put(key, feed);
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
