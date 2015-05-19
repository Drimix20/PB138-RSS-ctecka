package pb138.rss.search;

import java.util.Set;

/**
 *
 * @author Michaela
 */
public interface SearchQueryManager {
    
    public void addSearchQuery(SearchQuery query);
    public void removeSearchQuery(SearchQuery query);    
    public Set<SearchQuery> getAllQueries();
}
