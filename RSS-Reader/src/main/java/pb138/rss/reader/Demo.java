package pb138.rss.reader;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pb138.rss.feed.RssFeed;
import pb138.rss.feed.FeedContainer;
import pb138.rss.reader.downloader.RssFeedDownloader;
import pb138.rss.feed.FeedItem;
import pb138.rss.reader.downloader.FeedReader;
import pb138.rss.reader.downloader.FeedReaderTask;

/**
 *
 * @author Drimal
 */
public class Demo {

    public static void main(String[] args) {
        RssFeed feed;

        //Definovani readeru pro zdroj RSS
        FeedReader readerZiveCz = new FeedReader("http://www.zive.cz/rss/sc-47/default.aspx?rss=1");
        FeedReader readerPctuning = new FeedReader("http://pctuning.tyden.cz/index.php?option=com_ninjarsssyndicator&feed_id=3&format=raw");
        FeedReader readerIdnesKultura = new FeedReader("http://idnes.cz.feedsportal.com/c/34387/f/625944/index.rss");
        //Zobrazeni suroveho rss feedu do konzole
        //readerZiveCz.readFeedToSystemOut();
        //readerPctuning.readFeedToSystemOut();

        //Vytvoreni tasku pro scheduler s dobou startu a zpozdeni sbirani dat
        FeedReaderTask ziveCztask = new FeedReaderTask("ZiveCz", readerZiveCz, 0, 30);
        FeedReaderTask pcTuningTask = new FeedReaderTask("PcTuning", readerPctuning, 0, 40);
        FeedReaderTask idnesKulturaTask = new FeedReaderTask("IdnesKultura", readerIdnesKultura, 0, 30);

        List<FeedReaderTask> tasks = Arrays.asList(pcTuningTask, ziveCztask, idnesKulturaTask);
        RssFeedDownloader downloader = new RssFeedDownloader(10);
        downloader.init();
        //Spusteni naplanovanych ukolu
        downloader.schedule(tasks);

        //Kontroloa nasbiranych dat
        waitSeconds(45);
        FeedContainer instance = FeedContainer.getInstance();
        for (Iterator<String> iterator = instance.getKeys().iterator(); iterator.hasNext();) {
            String key = iterator.next();
            System.out.println("Zdroj: " + key);
            List<FeedItem> items = instance.getFromFeedContainer(key).getItems();
            for (int i = 0; i < 10; i++) {
                System.out.println(items.get(i));
            }
        }
        downloader.stop();
    }

    private static void waitSeconds(int seconds) {
        Logger.getLogger(Demo.class.getName()).log(Level.INFO, "Waiting for " + seconds);
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
