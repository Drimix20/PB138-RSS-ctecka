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
        
        boolean any = false;
        boolean titleRes = false, descrRes = false, langRes = false, dateRes = false;
        
        switch(field) {
        case ANY: 
            any = true;
            
        case TITLE:
            switch(cond) {
            case IS:
                titleRes = feed.getTitle().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case IS_NOT:
                titleRes = !feed.getTitle().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case CONTAINS:
                titleRes = feed.getTitle().toLowerCase().contains(expr.toLowerCase());
                break;
            case CONTAINS_NOT:
                titleRes = !feed.getTitle().toLowerCase().contains(expr.toLowerCase());
                break;
            case BEGINS_WITH:
                titleRes = feed.getTitle().toLowerCase().startsWith(expr.toLowerCase());
                break;
            case ENDS_WITH:
                titleRes = feed.getTitle().toLowerCase().endsWith(expr.toLowerCase());
                break;
            case CONTAINS_ANY://viac stringov
                StringTokenizer defaultTokenizer = new StringTokenizer(expr.toLowerCase());
                while (defaultTokenizer.hasMoreTokens()) {
                    titleRes = feed.getTitle().toLowerCase().contains(defaultTokenizer.nextToken());
                    if (titleRes) 
                        break;
                }
                break;
            } 
            if (!any) break;
        case DESCRIPTION:
            switch(cond) {
            case IS:
                descrRes = feed.getDescription().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case IS_NOT:
                descrRes = !feed.getDescription().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case CONTAINS:
                descrRes = feed.getDescription().toLowerCase().contains(expr.toLowerCase());
                break;
            case CONTAINS_NOT:
                descrRes = !feed.getDescription().toLowerCase().contains(expr.toLowerCase());
                break;
            case BEGINS_WITH:
                descrRes = feed.getDescription().toLowerCase().startsWith(expr.toLowerCase());
                break;
            case ENDS_WITH:
                descrRes = feed.getDescription().toLowerCase().endsWith(expr.toLowerCase());
                break;
            case CONTAINS_ANY:
                StringTokenizer defaultTokenizer = new StringTokenizer(expr.toLowerCase());
                while (defaultTokenizer.hasMoreTokens()) {
                    descrRes = feed.getTitle().toLowerCase().contains(defaultTokenizer.nextToken());
                    if (descrRes) 
                        break;
                }
                break;
            } 
            if (!any) break;
        case LANGUAGE:
            switch(cond) {
            case IS:
                langRes = feed.getLanguage().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case IS_NOT:
                langRes = !feed.getLanguage().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case CONTAINS:
                langRes = feed.getLanguage().toLowerCase().contains(expr.toLowerCase());
                break;
            case CONTAINS_NOT:
                langRes = !feed.getLanguage().toLowerCase().contains(expr.toLowerCase());
                break;
            case BEGINS_WITH:
                langRes = feed.getLanguage().toLowerCase().startsWith(expr.toLowerCase());
                break;
            case ENDS_WITH:
                langRes = feed.getLanguage().toLowerCase().endsWith(expr.toLowerCase());
                break;
            case CONTAINS_ANY:
                StringTokenizer defaultTokenizer = new StringTokenizer(expr.toLowerCase());
                while (defaultTokenizer.hasMoreTokens()) {
                    langRes = feed.getLanguage().toLowerCase().contains(defaultTokenizer.nextToken());
                    if (langRes) 
                        break;
                }
                break;
            }
            if (!any) break;
        case DATE:
            switch(cond) {
            case IS:
                dateRes = feed.getPubDate().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case IS_NOT:
                dateRes = !feed.getPubDate().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case CONTAINS:
                dateRes = feed.getPubDate().toLowerCase().contains(expr.toLowerCase());
                break;
            case CONTAINS_NOT:
                dateRes = !feed.getPubDate().toLowerCase().contains(expr.toLowerCase());
                break;
            case BEGINS_WITH:
                dateRes = feed.getPubDate().toLowerCase().startsWith(expr.toLowerCase());
                break;
            case ENDS_WITH:
                dateRes = feed.getPubDate().toLowerCase().endsWith(expr.toLowerCase());
                break;
            case CONTAINS_ANY:
                StringTokenizer defaultTokenizer = new StringTokenizer(expr.toLowerCase());
                while (defaultTokenizer.hasMoreTokens()) {
                    dateRes = feed.getPubDate().toLowerCase().contains(defaultTokenizer.nextToken());
                    if (dateRes) 
                        break;
                }
                break;
            }
            break;
        }
        
        return titleRes || descrRes || langRes || dateRes;
    }
    
    public boolean matchItem(RssFeedItem item) {
        
        boolean any = false;
        boolean titleRes = false, descrRes = false, authRes = false, dateRes = false;
         
        switch(field) { 
        case ANY: 
            any = true;
                
        case TITLE:
            switch(cond) {
            case IS:
                titleRes = item.getTitle().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case IS_NOT:
                titleRes = !item.getTitle().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case CONTAINS:
                titleRes = item.getTitle().toLowerCase().contains(expr.toLowerCase());
                break;
            case CONTAINS_NOT:
                titleRes = !item.getTitle().toLowerCase().contains(expr.toLowerCase());
                break;
            case BEGINS_WITH:
                titleRes = item.getTitle().toLowerCase().startsWith(expr.toLowerCase());
                break;
            case ENDS_WITH:
                titleRes = item.getTitle().toLowerCase().endsWith(expr.toLowerCase());
                break;
            case CONTAINS_ANY:
                StringTokenizer defaultTokenizer = new StringTokenizer(expr.toLowerCase());
                while (defaultTokenizer.hasMoreTokens()) {
                    titleRes = item.getTitle().toLowerCase().contains(defaultTokenizer.nextToken());
                    if (titleRes) 
                        break;
                }
                break;
            }
            if (!any) break;                          
        case DESCRIPTION:
            switch(cond) {
            case IS:
                descrRes = item.getDescription().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case IS_NOT:
                descrRes = !item.getDescription().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case CONTAINS:
                descrRes = item.getDescription().toLowerCase().contains(expr.toLowerCase());
                break;
            case CONTAINS_NOT:
                descrRes = !item.getDescription().toLowerCase().contains(expr.toLowerCase());
                break;
            case BEGINS_WITH:
                descrRes = item.getDescription().toLowerCase().startsWith(expr.toLowerCase());
                break;
            case ENDS_WITH:
                descrRes = item.getDescription().toLowerCase().endsWith(expr.toLowerCase());
                break;
            case CONTAINS_ANY:
                StringTokenizer defaultTokenizer = new StringTokenizer(expr.toLowerCase());
                while (defaultTokenizer.hasMoreTokens()) {
                    descrRes = item.getDescription().toLowerCase().contains(defaultTokenizer.nextToken());
                    if (descrRes) 
                        break;
                }
                break;
            }
            if (!any) break;
        case DATE:
            switch(cond) {
            case IS:
                dateRes = item.getDate().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case IS_NOT:
                dateRes = !item.getDate().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case CONTAINS:
                dateRes = item.getDate().toLowerCase().contains(expr.toLowerCase());
                break;
            case CONTAINS_NOT:
                dateRes = !item.getDate().toLowerCase().contains(expr.toLowerCase());
                break;
            case BEGINS_WITH:
                dateRes = item.getDate().toLowerCase().startsWith(expr.toLowerCase());
                break;
            case ENDS_WITH:
                dateRes = item.getDate().toLowerCase().endsWith(expr.toLowerCase());
                break;
            case CONTAINS_ANY:
                StringTokenizer defaultTokenizer = new StringTokenizer(expr.toLowerCase());
                while (defaultTokenizer.hasMoreTokens()) {
                    dateRes = item.getDate().toLowerCase().contains(defaultTokenizer.nextToken());
                    if (dateRes) 
                        break;
                }
                break;
            }
            if (!any) break;
        case AUTHOR:
            switch(cond) {
            case IS:
                authRes = item.getAuthor().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case IS_NOT:
                authRes = !item.getAuthor().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case CONTAINS:
                authRes = item.getAuthor().toLowerCase().contains(expr.toLowerCase());
                break;
            case CONTAINS_NOT:
                authRes = !item.getAuthor().toLowerCase().contentEquals(expr.toLowerCase());
                break;
            case BEGINS_WITH:
                authRes = item.getAuthor().toLowerCase().startsWith(expr.toLowerCase());
                break;
            case ENDS_WITH:
                authRes = item.getAuthor().toLowerCase().endsWith(expr.toLowerCase());
                break;
            case CONTAINS_ANY:
                StringTokenizer defaultTokenizer = new StringTokenizer(expr.toLowerCase());
                while (defaultTokenizer.hasMoreTokens()) {
                    authRes = item.getAuthor().toLowerCase().contains(defaultTokenizer.nextToken());
                    if (authRes) 
                        break;
                }
                break;
            }
            break;
        }
        return titleRes || descrRes || authRes || dateRes;
    }
}
