package pb138.rss.search;

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
     */
    @Override
    public RssFeedContainer runSearchForContainer(RssFeedContainer container, Set<SearchQuery> queries, boolean all) {
        
        RssFeedContainer filtered = new RssFeedContainer();
        boolean oneQuery;
        boolean allQueries;
        for (String key : container.getKeys()) {
            RssFeed result = container.getFromFeedContainer(key);
            allQueries = true;
            oneQuery = false;
            for (SearchQuery query : queries) {
                if (!query.matchFeed(result)) {
                    if(all)
                        allQueries = false;
                }
                else oneQuery = true;
            }
            if (all) {
                if (allQueries)
                    filtered.putIntoFeedContainer(key, result);
            }
            else if (oneQuery)
                filtered.putIntoFeedContainer(key, result);
            else if (runSearchForFeed(result, queries, all) != null)
                filtered.putIntoFeedContainer(key, result);
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
                    if(all) 
                        allQueries = false;
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
