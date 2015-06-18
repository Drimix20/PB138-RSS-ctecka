package pb138.rss.search;

import java.util.Set;
import pb138.rss.feed.RssFeed;
import pb138.rss.feed.RssFeedContainer;
import pb138.rss.reader.downloader.RssFeedReader;

/**
 *
 * @author Michaela
 */
public class SearchDemo {
     
    public static void main(String[] args) {

        RssFeedContainer feedContainer = new RssFeedContainer();
        RssFeedReader feedReader = new RssFeedReader("http://www.zive.cz/rss/sc-47/default.aspx?rss=1");
        RssFeed feed = feedReader.readFeed();
        feedContainer.putIntoFeedContainer(feedReader.getUrl(), feed);       
        
        SearchQuery query = new SearchQuery(SearchField.TITLE, SearchCondition.CONTAINS, "mobile");
        
        SearchQueryManagerImpl qman = new SearchQueryManagerImpl();
        qman.addSearchQuery(query);
        Set<SearchQuery> queries = qman.getAllQueries();   
           
        SearchManagerImpl sman = new SearchManagerImpl();
        sman.runSearchForContainer(feedContainer, queries, true, true, true);
    }
}
