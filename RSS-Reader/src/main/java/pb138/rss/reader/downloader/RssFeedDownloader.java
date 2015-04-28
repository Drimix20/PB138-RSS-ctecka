package pb138.rss.reader.downloader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Drimal
 */
public class RssFeedDownloader {

    //TODO rozsirit o dynamicke pridavani/odebirani tasku
    private ScheduledExecutorService scheduler;
    private int size;
    private Map<String, ScheduledFuture<?>> scheduledTasks;

    public RssFeedDownloader(int size) {
        this.size = size;
    }

    public void init() {
        scheduledTasks = new HashMap<>();
        scheduler = Executors.newScheduledThreadPool(size);
    }

    public void schedule(List<FeedReaderTask> tasksToSchedule) {
        for (FeedReaderTask feedReaderTask : tasksToSchedule) {
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
