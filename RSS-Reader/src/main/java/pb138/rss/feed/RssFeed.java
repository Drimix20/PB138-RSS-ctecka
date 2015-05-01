package pb138.rss.feed;

import java.util.ArrayList;
import java.util.List;

/*
 *
 */
public class RssFeed {

    private final String title;
    private final String link;
    private final String description;
    private final String language;
    private final String copyright;
    private final String pubDate;

    private RssFeedImage image = new RssFeedImage();
    private final List<RssFeedItem> items = new ArrayList<RssFeedItem>();

    public RssFeed() {
        this.title = "";
        this.link = "";
        this.description = "";
        this.language = "";
        this.copyright = "";
        this.pubDate = "";
    }

    public RssFeed(String title, String link, String description, String language,
            String copyright, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.language = language;
        this.copyright = copyright;
        this.pubDate = pubDate;
    }

    public List<RssFeedItem> getItems() {
        return items;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getPubDate() {
        return pubDate;
    }

    public RssFeedImage getImage() {
        return image;
    }

    public void setImage(RssFeedImage image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Rss feed [copyright=" + copyright + ", description=" + description
                + ", language=" + language + ", link=" + link + ", pubDate="
                + pubDate + ", title=" + title + ", items=" + items.size() + "]";
    }
}
