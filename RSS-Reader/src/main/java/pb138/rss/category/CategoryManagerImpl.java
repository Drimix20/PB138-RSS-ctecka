package pb138.rss.category;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pb138.rss.feed.RssFeed;
import pb138.rss.feed.RssFeedContainer;
import org.apache.log4j.Logger;

/**
 *
 * @author Michaela
 */
public class CategoryManagerImpl implements CategoryManager {
    private final Logger logger = Logger.getLogger(CategoryManagerImpl.class);
    private Set<Category> categories = new HashSet<>();
    
    public CategoryManagerImpl() {
    }
    
    /** 
     * Prednastavená kategória none.
     * @param none žiadna kategória
     */
    @Override
    public void init(Category none) {
        logger.info("Initialize categories.");
        this.categories.add(none);
    }
    
    @Override
    public void addCategories(Set<Category> categories) {
        this.categories.addAll(categories);
    }
    
    @Override
    public void createCategory(Category category) {
        logger.info("Create category: " + category.toString());
        categories.add(category);
    }

    /** 
     * Vymaže danú kategóriu. Feedom s danou kategóriou nastaví novú kategóriu none.
     * @param category
     * @param container
     */
    @Override
    public void deleteCategory(RssFeedContainer container, Category category) {
        logger.info("Delete category: " + category.toString());
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

    /** 
     * Zobrazí feedy zo zoznamu kategorií.
     * @param categories
     * @param container
     * @return filtrovaný kontejner
     */
    @Override
    public RssFeedContainer showFeedsInCategories(RssFeedContainer container, List<Category> categories) {
        logger.info("Searching for feeds in given categories.");
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
