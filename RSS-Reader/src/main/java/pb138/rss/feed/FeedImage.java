package pb138.rss.feed;

import java.util.Objects;

/**
 *
 * @author Drimal
 */
public class FeedImage {

    private String title;
    private String url;
    private String link;

    public FeedImage() {
        title = "";
        url = "";
        link = "";
    }

    public FeedImage(String title, String url, String link) {
        this.title = title;
        this.url = url;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getLink() {
        return link;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.title);
        hash = 59 * hash + Objects.hashCode(this.url);
        hash = 59 * hash + Objects.hashCode(this.link);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FeedImage other = (FeedImage) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.link, other.link)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Feed Image [title=" + title + ", url=" + url + ", link=" + link + "]";
    }
}
