package category;

import pb138.rss.feed.RssFeed;
import pb138.rss.feed.RssFeedContainer;
import java.util.Set;

/**
 *
 * @author Michaela
 */
public interface CategoryManager {
    
    public void createCategory(Category category);    
    public void deleteCategory(RssFeedContainer container, Category category);
    public Set<Category> getAllCategories();
    public RssFeedContainer showFeedsInCategory(RssFeedContainer container, Category category);
}
