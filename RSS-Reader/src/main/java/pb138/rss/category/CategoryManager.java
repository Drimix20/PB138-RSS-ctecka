package pb138.rss.category;

import java.util.List;
import java.util.Set;
import pb138.rss.feed.RssFeedContainer;

/**
 *
 * @author Michaela
 */
public interface CategoryManager {
    
    public void init(Category none);
    public void addCategories(Set<Category> categories);
    public void createCategory(Category category);    
    public void deleteCategory(RssFeedContainer container, Category category);
    public Set<Category> getAllCategories();
    public RssFeedContainer showFeedsInCategories(RssFeedContainer container, List<Category> categories);
}
