package pb138.rss.feed;

import java.util.Set;
import pb138.rss.reader.downloader.RssFeedReaderTask;

/**
 *
 * @author Martin Drimal
 * @UCO 373769
 */
public interface Container {

    RssFeed getFromFeedContainer(String key);

    Set<String> getKeys();

    void putIntoFeedContainer(String key, RssFeed feed);

    boolean removeOldData(RssFeedReaderTask task);
}
