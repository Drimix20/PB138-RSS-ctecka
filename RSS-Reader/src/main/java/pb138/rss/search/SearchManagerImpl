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
    public void runSearchForContainer(RssFeedContainer container, Set<SearchQuery> queries, boolean all) {
        
        boolean printF = true;
        boolean printI = true;
        for (String key : container.getKeys()) {
            RssFeed result = container.getFromFeedContainer(key);
            for (SearchQuery query : queries) {
                if (!query.matchFeed(result)) {
                    if(all)
                        printF = false;
                }
                else System.out.println(result);
            }
            if (printF && all) {
                System.out.println(result);
                printF = true;
            }
            for (RssFeedItem result2 : result.getItems()) {
                for (SearchQuery query : queries) { 
                    if (!query.matchItem(result2)) {
                        if(all)
                            printI = false;
                    }
                    else System.out.println(result2);
                }
                if (printI && all) {
                    System.out.println(result2);
                    printI = true;
                }
            }
        }
    }
    
    @Override
    public void runSearchForFeed(RssFeed feed, Set<SearchQuery> queries, boolean all) {
    
        boolean printI = true;  
        for (RssFeedItem result : feed.getItems()) {
            for (SearchQuery query : queries) {
                if (!query.matchItem(result)) {
                    if(all) 
                        printI = false;
                }
                else System.out.println(result);
            }
            if (printI && all) {
                System.out.println(result);
                printI = true;
            }
        }
    }
    
}
