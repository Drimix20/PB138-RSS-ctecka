package pb138.rss.reader;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import pb138.rss.feed.RssFeedContainer;
import pb138.rss.reader.downloader.RssFeedDownloader;
import pb138.rss.feed.RssFeedItem;
import pb138.rss.reader.downloader.RssFeedReader;
import pb138.rss.reader.downloader.RssFeedReaderTask;

/**
 *
 * @author Martin Drimal
 * @UCO 373769
 */
public class Demo {

    public static void main(String[] args) {
        //Vytvoreni kontaineru pro ukladani feedu
        RssFeedContainer feedContainer = new RssFeedContainer();

        //Definovani readeru pro zdroj RSS
        RssFeedReader readerZiveCz = new RssFeedReader("http://www.zive.cz/rss/sc-47/default.aspx?rss=1");
        RssFeedReader readerPctuning = new RssFeedReader("http://pctuning.tyden.cz/index.php?option=com_ninjarsssyndicator&feed_id=3&format=raw");
        RssFeedReader readerIdnesKultura = new RssFeedReader("http://idnes.cz.feedsportal.com/c/34387/f/625944/index.rss");
        RssFeedReader readerHobby = new RssFeedReader("http://servis.idnes.cz/rss.aspx?c=hobby");
        //Zobrazeni suroveho rss feedu do konzole
        //readerZiveCz.readFeedToSystemOut();
        //readerPctuning.readFeedToSystemOut();

        //Vytvoreni tasku pro scheduler s dobou startu a zpozdeni pro sbirani dat
        RssFeedReaderTask ziveCztask = new RssFeedReaderTask("ZiveCz", readerZiveCz, 0, 30, feedContainer);
        RssFeedReaderTask pcTuningTask = new RssFeedReaderTask("PcTuning", readerPctuning, 0, 40, feedContainer);
        RssFeedReaderTask idnesKulturaTask = new RssFeedReaderTask("IdnesKultura", readerIdnesKultura, 0, 30, feedContainer);
        RssFeedReaderTask idnesHobbyTask = new RssFeedReaderTask("IdnesHobby", readerHobby, 0, 25, feedContainer);

        List<RssFeedReaderTask> tasks = Arrays.asList(pcTuningTask, ziveCztask, idnesKulturaTask);
        //Vytvoreni rss downloaderu s definovanym kontainer pro sbirani rss feedu a s maximalne 3 vlakny
        RssFeedDownloader downloader = new RssFeedDownloader(feedContainer, 3);
        //Spusteni naplanovanych ukolu
        downloader.schedule(tasks);

        //Kontrola prvnich 10 nasbiranych dat pro kazdy definovany zdroj
        waitSeconds(45);

        for (Iterator<String> iterator = feedContainer.getKeys().iterator(); iterator.hasNext();) {
            String key = iterator.next();
            System.out.println("Zdroj: " + key);
            Set<RssFeedItem> items = feedContainer.getFromFeedContainer(key).getItems();
            for (Iterator<RssFeedItem> it = items.iterator(); it.hasNext();) {
                System.out.println(it.next());
            }
        }
        //odstraneni jednoho zdroje feedu
        downloader.schedule(Arrays.asList(pcTuningTask, ziveCztask));
        waitSeconds(45);

        //pridani noveho zdroje feedu
        downloader.schedule(Arrays.asList(pcTuningTask, ziveCztask, idnesHobbyTask));
        waitSeconds(45);

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
