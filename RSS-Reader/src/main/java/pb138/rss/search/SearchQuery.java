package pb138.rss.search;

import java.util.StringTokenizer;
import pb138.rss.feed.RssFeed;
import pb138.rss.feed.RssFeedItem;

/**
 *
 * @author Michaela
 */
public class SearchQuery {
    
    private String expr;
    private SearchCondition cond;
    private SearchField field;
    
    public SearchQuery(SearchField field, SearchCondition cond, String expr) {
        this.field = field;
        this.cond = cond;
        this.expr = expr;
    } 
    
    public void setSearchField(SearchField field) {
        this.field = field;
    }
    
    public void setSearchCondition(SearchCondition cond) {
        this.cond = cond;
    }
    
    public void setSearxhExpression(String expr) {
        this.expr = expr;
    }
    
    public SearchField getSearchField() {
        return field;
    }
    
    public SearchCondition getCondition() {
        return cond;
    }
    
    public String getSearchExpression() {
        return expr;
    }
    
    @Override
    public String toString() {
       return field + " " + cond + " " + expr; 
    }
    
    /**
     * 
     * @param feed
     * @return 
     */
    public boolean matchFeed(RssFeed feed) {
        
        boolean result = false;
        
        switch(field) {
        case TITLE:
            switch(cond) {
            case IS:
                result = feed.getTitle().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case IS_NOT:
                result = !feed.getTitle().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case CONTAINS:
                result = feed.getTitle().toLowerCase().contains(expr.toLowerCase());
                break;
            case CONTAINS_NOT:
                result = !feed.getTitle().toLowerCase().contains(expr.toLowerCase());
                break;
            case BEGINS_WITH:
                result = feed.getTitle().toLowerCase().startsWith(expr.toLowerCase());
                break;
            case ENDS_WITH:
                result = feed.getTitle().toLowerCase().endsWith(expr.toLowerCase());
                break;
            case CONTAINS_ANY://viac stringov
                StringTokenizer defaultTokenizer = new StringTokenizer(expr.toLowerCase());
                while (defaultTokenizer.hasMoreTokens()) {
                    result = feed.getTitle().toLowerCase().contains(defaultTokenizer.nextToken());
                    if (result) 
                        break;
                }
                break;
            } 
            break;
        case DESCRIPTION:
            switch(cond) {
            case IS:
                result = feed.getDescription().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case IS_NOT:
                result = !feed.getDescription().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case CONTAINS:
                result = feed.getDescription().toLowerCase().contains(expr.toLowerCase());
                break;
            case CONTAINS_NOT:
                result = !feed.getDescription().toLowerCase().contains(expr.toLowerCase());
                break;
            case BEGINS_WITH:
                result = feed.getDescription().toLowerCase().startsWith(expr.toLowerCase());
                break;
            case ENDS_WITH:
                result = feed.getDescription().toLowerCase().endsWith(expr.toLowerCase());
                break;
            case CONTAINS_ANY:
                StringTokenizer defaultTokenizer = new StringTokenizer(expr.toLowerCase());
                while (defaultTokenizer.hasMoreTokens()) {
                    result = feed.getTitle().toLowerCase().contains(defaultTokenizer.nextToken());
                    if (result) 
                        break;
                }
                break;
            } 
            break;
        case LANGUAGE:
            switch(cond) {
            case IS:
                result = feed.getLanguage().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case IS_NOT:
                result = !feed.getLanguage().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case CONTAINS:
                result = feed.getLanguage().toLowerCase().contains(expr.toLowerCase());
                break;
            case CONTAINS_NOT:
                result = !feed.getLanguage().toLowerCase().contains(expr.toLowerCase());
                break;
            case BEGINS_WITH:
                result = feed.getLanguage().toLowerCase().startsWith(expr.toLowerCase());
                break;
            case ENDS_WITH:
                result = feed.getLanguage().toLowerCase().endsWith(expr.toLowerCase());
                break;
            case CONTAINS_ANY:
                StringTokenizer defaultTokenizer = new StringTokenizer(expr.toLowerCase());
                while (defaultTokenizer.hasMoreTokens()) {
                    result = feed.getLanguage().toLowerCase().contains(defaultTokenizer.nextToken());
                    if (result) 
                        break;
                }
                break;
            }
            break;
        case DATE:
            switch(cond) {
            case IS:
                result = feed.getPubDate().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case IS_NOT:
                result = !feed.getPubDate().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case CONTAINS:
                result = feed.getPubDate().toLowerCase().contains(expr.toLowerCase());
                break;
            case CONTAINS_NOT:
                result = !feed.getPubDate().toLowerCase().contains(expr.toLowerCase());
                break;
            case BEGINS_WITH:
                result = feed.getPubDate().toLowerCase().startsWith(expr.toLowerCase());
                break;
            case ENDS_WITH:
                result = feed.getPubDate().toLowerCase().endsWith(expr.toLowerCase());
                break;
            case CONTAINS_ANY:
                StringTokenizer defaultTokenizer = new StringTokenizer(expr.toLowerCase());
                while (defaultTokenizer.hasMoreTokens()) {
                    result = feed.getPubDate().toLowerCase().contains(defaultTokenizer.nextToken());
                    if (result) 
                        break;
                }
                break;
            }
            break;
        }
        return result;
    }
    
    public boolean matchItem(RssFeedItem item) {
        
        boolean result = false;
         
        switch(field) {             
        case TITLE:
            switch(cond) {
            case IS:
                result = item.getTitle().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case IS_NOT:
                result = !item.getTitle().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case CONTAINS:
                result = item.getTitle().toLowerCase().contains(expr.toLowerCase());
                break;
            case CONTAINS_NOT:
                result = !item.getTitle().toLowerCase().contains(expr.toLowerCase());
                break;
            case BEGINS_WITH:
                result = item.getTitle().toLowerCase().startsWith(expr.toLowerCase());
                break;
            case ENDS_WITH:
                result = item.getTitle().toLowerCase().endsWith(expr.toLowerCase());
                break;
            case CONTAINS_ANY:
                StringTokenizer defaultTokenizer = new StringTokenizer(expr.toLowerCase());
                while (defaultTokenizer.hasMoreTokens()) {
                    result = item.getTitle().toLowerCase().contains(defaultTokenizer.nextToken());
                    if (result) 
                        break;
                }
                break;
            }
            break;                          
        case DESCRIPTION:
            switch(cond) {
            case IS:
                result = item.getDescription().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case IS_NOT:
                result = !item.getDescription().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case CONTAINS:
                result = item.getDescription().toLowerCase().contains(expr.toLowerCase());
                break;
            case CONTAINS_NOT:
                result = !item.getDescription().toLowerCase().contains(expr.toLowerCase());
                break;
            case BEGINS_WITH:
                result = item.getDescription().toLowerCase().startsWith(expr.toLowerCase());
                break;
            case ENDS_WITH:
                result = item.getDescription().toLowerCase().endsWith(expr.toLowerCase());
                break;
            case CONTAINS_ANY:
                StringTokenizer defaultTokenizer = new StringTokenizer(expr.toLowerCase());
                while (defaultTokenizer.hasMoreTokens()) {
                    result = item.getDescription().toLowerCase().contains(defaultTokenizer.nextToken());
                    if (result) 
                        break;
                }
                break;
            }
            break;
        case DATE:
            switch(cond) {
            case IS:
                result = item.getDate().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case IS_NOT:
                result = !item.getDate().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case CONTAINS:
                result = item.getDate().toLowerCase().contains(expr.toLowerCase());
                break;
            case CONTAINS_NOT:
                result = !item.getDate().toLowerCase().contains(expr.toLowerCase());
                break;
            case BEGINS_WITH:
                result = item.getDate().toLowerCase().startsWith(expr.toLowerCase());
                break;
            case ENDS_WITH:
                result = item.getDate().toLowerCase().endsWith(expr.toLowerCase());
                break;
            case CONTAINS_ANY:
                StringTokenizer defaultTokenizer = new StringTokenizer(expr.toLowerCase());
                while (defaultTokenizer.hasMoreTokens()) {
                    result = item.getDate().toLowerCase().contains(defaultTokenizer.nextToken());
                    if (result) 
                        break;
                }
                break;
            }
            break;
        case AUTHOR:
            switch(cond) {
            case IS:
                result = item.getAuthor().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case IS_NOT:
                result = !item.getAuthor().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case CONTAINS:
                result = item.getAuthor().toLowerCase().contains(expr.toLowerCase());
                break;
            case CONTAINS_NOT:
                result = !item.getAuthor().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case BEGINS_WITH:
                result = item.getAuthor().toLowerCase().startsWith(expr.toLowerCase());
                break;
            case ENDS_WITH:
                result = item.getAuthor().toLowerCase().endsWith(expr.toLowerCase());
                break;
            case CONTAINS_ANY:
                StringTokenizer defaultTokenizer = new StringTokenizer(expr.toLowerCase());
                while (defaultTokenizer.hasMoreTokens()) {
                    result = item.getAuthor().toLowerCase().contains(defaultTokenizer.nextToken());
                    if (result) 
                        break;
                }
                break;
            }
            break;
        }
        return result;
    }
}
