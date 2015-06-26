package pb138.rss.search;

/**
 *
 * @author Michaela
 */
public enum SearchCondition {
    
    /*presná zhoda*/
    IS,
    IS_NOT,
    /*podreťazec*/
    CONTAINS,
    CONTAINS_NOT,
    /*viac podreťazcov*/
    CONTAINS_ANY,
    /*začiatok reťazca*/
    BEGINS_WITH,
    /*koniec reťazca*/
    ENDS_WITH
}
