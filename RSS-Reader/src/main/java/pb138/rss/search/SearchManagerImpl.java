package pb138.rss.search;

import java.util.HashSet;
import java.util.Set;
import pb138.rss.feed.RssFeed;
import pb138.rss.feed.RssFeedContainer;
import pb138.rss.feed.RssFeedItem;
import org.apache.log4j.Logger;

/**
 *
 * @author Michaela
 */
public class SearchManagerImpl implements SearchManager {
    private final Logger logger = Logger.getLogger(SearchManagerImpl.class);
    
    /**
     * Spustí vyhľadávanie pre celý kontejner.
     * @param container
     * @param queries
     * @param all logický súčin medzi dotazmi
     * @param feeds bude sa vyhľadávať vo feedoch
     * @param items bude sa vyhľadávať v itemoch
     * @return filtrovaný kontejner
     */
    @Override
    public RssFeedContainer runSearchForContainer(RssFeedContainer container, Set<SearchQuery> queries, boolean all, boolean feeds, boolean items) {
        logger.info("Running search.");
        
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
 
    /** 
     * Spustí vyhľadávanie pre jeden feed.
     * @param feed
     * @param queries
     * @param all logický súčin medzi dotazmi
     * @return množina itemov feedu, ktoré vyhovujú dotazu
     */
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
