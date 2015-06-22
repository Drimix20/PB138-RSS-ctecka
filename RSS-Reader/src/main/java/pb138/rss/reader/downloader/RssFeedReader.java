package pb138.rss.reader.downloader;

import pb138.rss.feed.RssFeedImage;
import pb138.rss.feed.RssFeed;
import pb138.rss.feed.RssFeedItem;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import org.apache.log4j.Logger;

/**
 *
 * @author Martin Drimal
 * @UCO 373769
 */
public class RssFeedReader {

    private static Logger logger = Logger.getLogger(RssFeedReader.class);
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String CHANNEL = "channel";
    private static final String LANGUAGE = "language";
    private static final String COPYRIGHT = "copyright";
    private static final String LINK = "link";
    private static final String AUTHOR = "author";
    private static final String ITEM = "item";
    private static final String PUB_DATE = "pubDate";
    private static final String GUID = "guid";
    private static final String IMAGE = "image";
    private URL url;

    public RssFeedReader(String address) {
        super();
        try {
            this.url = new URL(address);
        } catch (Exception ex) {
            throw new IllegalArgumentException("URL format error");
        }
    }

    public String getUrl() {
        return url.toString();
    }

    /**
     * Parsovani rss feedu ze zdrojoveho kanalu
     *
     * @return zparsovany feed
     */
    public RssFeed readFeed() {
        RssFeed feed = new RssFeed();

        try {
            boolean isImage = false;
            boolean isFeedHeader = true;
            String description = "";
            String title = "";
            String link = "";
            String language = "";
            String copyright = "";
            String author = "";
            String pubdate = "";
            String guid = "";
            String previousTag = "";
            String currentTag = "";
            String imgTitle = "";
            String imgUrl = "";
            String imgLink = "";
            RssFeedImage image = new RssFeedImage();

            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                                isImage = false;
                                feed = new RssFeed(title, link, description, language,
                                        copyright, pubdate);
                                feed.setImage(image);
                            }
                            event = eventReader.nextEvent();
                            break;
                        case TITLE:
                            if (isImage) {
                                imgTitle = getCharacterData(event, eventReader);
                                break;
                            }
                            title = getCharacterData(event, eventReader);
                            break;
                        case DESCRIPTION:
                            description = getCharacterData(event, eventReader);
                            break;
                        case LINK:
                            if (isImage) {
                                imgLink = getCharacterData(event, eventReader);
                                break;
                            }
                            link = getCharacterData(event, eventReader);
                            break;
                        case GUID:
                            guid = getCharacterData(event, eventReader);
                            break;
                        case LANGUAGE:
                            language = getCharacterData(event, eventReader);
                            break;
                        case AUTHOR:
                            author = getCharacterData(event, eventReader);
                            break;
                        case PUB_DATE:
                            pubdate = getCharacterData(event, eventReader);
                            break;
                        case COPYRIGHT:
                            copyright = getCharacterData(event, eventReader);
                            break;
                        case "url":
                            imgUrl = getCharacterData(event, eventReader);
                            break;
                        case IMAGE:
                            isImage = true;
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart().equals(ITEM)) {
                        RssFeedItem message = new RssFeedItem();
                        message.setAuthor(author);
                        message.setDescription(description);
                        message.setGuid(guid);
                        message.setLink(link);
                        message.setTitle(title);
                        message.setDate(pubdate);
                        feed.getItems().add(message);
                        event = eventReader.nextEvent();
                        continue;
                    } else if (event.asEndElement().getName().getLocalPart().equals(IMAGE)) {
                        isImage = false;
                        image = new RssFeedImage(imgTitle, imgUrl, imgLink);
                        event = eventReader.nextEvent();
                    }
                }
            }
        } catch (XMLStreamException e) {
            logger.error("Error while parsing url=" + url, e);
            throw new IllegalStateException("Error occurd while opening rss feed stream");
        }
        return feed;
    }

    /**
     * Slouzi pouze pro testovani, vypise surovy rss feed
     */
    public void readFeedToSystemOut() {
        //TODO vymazat testovaci kod
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            while (eventReader.hasNext()) {
                System.out.println(eventReader.nextEvent());

            }
        } catch (XMLStreamException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * Ziskani hodnoty mezi tagy
     *
     * @param event aktualni hodnota
     * @param eventReader zdrojovy stream
     * @return
     * @throws XMLStreamException
     */
    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    /**
     * Inicializace zdrojoveho streamu
     *
     * @return otevreny stream rss kanalu
     */
    public InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new IllegalStateException("Error occurd while opening rss feed stream");
        }
    }
}
