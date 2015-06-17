package pb138.rss.search;

import java.util.HashSet;
import java.util.Set;
import pb138.rss.feed.RssFeed;
import pb138.rss.feed.RssFeedContainer;
import pb138.rss.feed.RssFeedItem;

/**
 *
 * @author Michaela
 */
public class SearchManagerImpl implements SearchManager {
    
    SearchQueryManagerImpl man;
    
    /**
     * 
     * @param container
     * @param queries
     * @param all if true then all queries else any query from set queries
     * @param feeds
     * @param items
     * @return filtered container
     */
    @Override
    public RssFeedContainer runSearchForContainer(RssFeedContainer container, Set<SearchQuery> queries, boolean all, boolean feeds, boolean items) {
        
        RssFeedContainer filtered = new RssFeedContainer();
        RssFeed filteredItems = new RssFeed();
        Set<RssFeedItem> foundItems = new HashSet<>();
        boolean oneQuery;
        boolean allQueries;
        for (String key : container.getKeys()) {
            RssFeed result = container.getFromFeedContainer(key);
            
            if (feeds) {
                allQueries = true;
                oneQuery = false;
                for (SearchQuery query : queries) {
                    if (!query.matchFeed(result)) {
                        if(all) {
                            allQueries = false;
                            break;
                        }
                    }
                    else oneQuery = true;
                }
                if (all) {
                    if (allQueries)
                        filtered.putIntoFeedContainer(key, result);
                }
                else if (oneQuery)
                    filtered.putIntoFeedContainer(key, result);
            }
            
            if(items) {
                foundItems = runSearchForFeed(result, queries, all);
                if (!foundItems.isEmpty())
                    filteredItems.addItems(foundItems);
            }
        }
        if (!filteredItems.getItems().isEmpty()) {
            filtered.putIntoFeedContainer("filtered", filteredItems);
        }
        return filtered;
    }
 
    @Override
    public Set<RssFeedItem> runSearchForFeed(RssFeed feed, Set<SearchQuery> queries, boolean all) {
    
        Set<RssFeedItem> filtered = new HashSet<>();
        boolean oneQuery;
        boolean allQueries;
        for (RssFeedItem result : feed.getItems()) {
            allQueries = true;
            oneQuery = false;
            for (SearchQuery query : queries) {
                if (!query.matchItem(result)) {
                    if(all) {
                        allQueries = false;
                        break;
                    }
                }
                else oneQuery = true;
            }
            if (all) {
                if (allQueries)
                    filtered.add(result);
            }
            else if (oneQuery)
                filtered.add(result);            
        }
        return filtered;
    }
    
}
