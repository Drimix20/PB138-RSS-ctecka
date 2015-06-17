package pb138.rss.search;

import java.util.Set;
import pb138.rss.feed.RssFeed;
import pb138.rss.feed.RssFeedContainer;
import pb138.rss.feed.RssFeedItem;

/**
 *
 * @author Michaela
 */
public interface SearchManager {
    
    public RssFeedContainer runSearchForContainer(RssFeedContainer container, Set<SearchQuery> queries, boolean all, boolean items, boolean feeds);
    public Set<RssFeedItem> runSearchForFeed(RssFeed feed, Set<SearchQuery> queries, boolean all);
}
