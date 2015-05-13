package pb138.rss.feed;

import java.util.Set;

/**
 *
 * @author Martin Drimal
 * @UCO 373769
 */
public interface Container {

    RssFeed getFromFeedContainer(String key);

    Set<String> getKeys();

    void putIntoFeedContainer(String key, RssFeed feed);

}
