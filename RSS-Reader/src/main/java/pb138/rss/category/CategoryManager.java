package category;

import pb138.rss.feed.RssFeed;
import pb138.rss.feed.RssFeedContainer;

/**
 *
 * @author Michaela
 */
public interface CategoryManager {
    
    public void createCategory(Category category);
    public void deleteCategory(RssFeedContainer container, Category category);
    public void showFeedsInCategory(RssFeedContainer container, Category category);
}
