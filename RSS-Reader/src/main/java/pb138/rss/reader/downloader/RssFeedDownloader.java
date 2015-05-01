package pb138.rss.reader.downloader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import pb138.rss.feed.Container;

/**
 *
 * @author Drimal
 */
public class RssFeedDownloader {

    //TODO rozsirit o dynamicke pridavani/odebirani tasku
    private ScheduledExecutorService scheduler;
    private Map<String, ScheduledFuture<?>> scheduledTasks;

    public RssFeedDownloader(Container container, int size) {
        scheduledTasks = new HashMap<>();
        scheduler = Executors.newScheduledThreadPool(size);
    }

    public void schedule(List<RssFeedReaderTask> tasksToSchedule) {
        for (RssFeedReaderTask feedReaderTask : tasksToSchedule) {
            ScheduledFuture<?> scheduleFuture = scheduler.scheduleAtFixedRate(feedReaderTask, feedReaderTask.getInitialDelay(),
                    feedReaderTask.getScheduledDelay(), TimeUnit.SECONDS);
            scheduledTasks.put(feedReaderTask.getLabel(), scheduleFuture);
        }
    }

    public void stop() {
        if (scheduler != null) {
            scheduler.shutdown();
            scheduler = null;
        }
    }
}
