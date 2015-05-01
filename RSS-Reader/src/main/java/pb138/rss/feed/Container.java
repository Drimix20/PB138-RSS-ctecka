package pb138.rss.feed;

import java.util.Set;

/**
 *
 * @author Drimal
 */
public interface Container {

    RssFeed getFromFeedContainer(String key);

    Set<String> getKeys();

    void putIntoFeedContainer(String key, RssFeed feed);

}
