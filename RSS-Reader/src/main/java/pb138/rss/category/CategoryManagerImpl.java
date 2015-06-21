package pb138.rss.category;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pb138.rss.feed.RssFeed;
import pb138.rss.feed.RssFeedContainer;

/**
 *
 * @author Michaela
 */
public class CategoryManagerImpl implements CategoryManager {
    
    private Set<Category> categories = new HashSet<>();
    
    public CategoryManagerImpl() {
    }
    
    @Override
    public void init(Category none) {
        this.categories.add(none);
    }
    
    @Override
    public void addCategories(Set<Category> categories) {
        this.categories.addAll(categories);
    }
    
    @Override
    public void createCategory(Category category) {
        categories.add(category);
    }

    @Override
    public void deleteCategory(RssFeedContainer container, Category category) {
        for (String key : container.getKeys()) {
            RssFeed result = container.getFromFeedContainer(key);
            if(result.getCategory().equals(category)) {
                Category none = new Category("none");
                result.setCategory(none);
            }
        }
        categories.remove(category);
    }
    
    @Override
    public Set<Category> getAllCategories() {
        return categories;
    }
    
    public Category findByName(String name) { 
        for (Category category : categories) {
            if(category.getName().equals(name))
                return category;   
        } 
        return null;
    }

    @Override
    public RssFeedContainer showFeedsInCategories(RssFeedContainer container, List<Category> categories) {
        RssFeedContainer filtered = new RssFeedContainer();
        for (String key : container.getKeys()) {
            RssFeed result = container.getFromFeedContainer(key);
            for (Category cat : categories) {
                if(result.getCategory().equals(cat)) {
                    filtered.putIntoFeedContainer(key, result);
                    break;
                }
            }
        }
        return filtered;     
    }
    
}
