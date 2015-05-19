package pb138.rss.search;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Michaela
 */
public class SearchQueryManagerImpl implements SearchQueryManager {
    
    private Set<SearchQuery> queries = new HashSet<>();
    
    @Override
    public void addSearchQuery(SearchQuery query) {
        queries.add(query);
    } 
    
    @Override
    public void removeSearchQuery(SearchQuery query) {
        queries.remove(query);
    }
    
    @Override
    public Set<SearchQuery> getAllQueries() {
        return queries;
    }
    
}
