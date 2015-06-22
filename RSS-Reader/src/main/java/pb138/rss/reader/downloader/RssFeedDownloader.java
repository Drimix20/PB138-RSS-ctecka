package pb138.rss.reader.downloader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import pb138.rss.feed.Container;

/**
 *
 * @author Martin Drimal
 * @UCO 373769
 */
public class RssFeedDownloader {

    private Logger logger = Logger.getLogger(RssFeedDownloader.class);
    private ScheduledExecutorService scheduler;
    private List<RssFeedReaderTask> oldReaderTasks;
    private Map<String, ScheduledFuture<?>> scheduledTasks;

    public RssFeedDownloader(Container container, int size) {
        oldReaderTasks = new ArrayList<>();
        scheduledTasks = new HashMap<>();
        scheduler = Executors.newScheduledThreadPool(size);
    }

    public void schedule(List<RssFeedReaderTask> tasksToSchedule) {
        logger.info("Task scheduling - numb of tasks: " + tasksToSchedule.size());
        cancelUnsupportedTasks(tasksToSchedule);

        for (RssFeedReaderTask feedReaderTask : tasksToSchedule) {
            if (oldReaderTasks.contains(feedReaderTask)) {
                continue;
            }
            ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate(feedReaderTask, feedReaderTask.getInitialDelay(),
                    feedReaderTask.getScheduledDelay(), TimeUnit.SECONDS);
            scheduledTasks.put(feedReaderTask.getAssociatedUrl(), scheduledFuture);
        }
        oldReaderTasks = createNewInstanceOf(tasksToSchedule);
        logger.info("Tasks were scheduled");
    }

    private List<RssFeedReaderTask> createNewInstanceOf(List<RssFeedReaderTask> tasks) {
        List<RssFeedReaderTask> oldTasks = new ArrayList<>();
        for (RssFeedReaderTask currrentTask : oldTasks) {
            RssFeedReaderTask rssFeedReaderTask = new RssFeedReaderTask(currrentTask.getLabel(), currrentTask.getFeedReader(), currrentTask.getInitialDelay(),
                    currrentTask.getScheduledDelay(), currrentTask.getFeedContainer());
            oldTasks.add(rssFeedReaderTask);
        }

        return oldTasks;
    }

    private void cancelUnsupportedTasks(List<RssFeedReaderTask> tasksToSchedule) {
        logger.info("Cancel old tasks");
        for (RssFeedReaderTask oldTask : oldReaderTasks) {
            if (!tasksToSchedule.contains(oldTask)) {
                ScheduledFuture<?> task = scheduledTasks.get(oldTask.getAssociatedUrl());
                task.cancel(true);
                logger.info("canceling " + oldTask.getAssociatedUrl());
            }
        }
    }

    public void stop() {
        logger.info("Stop scheduler");
        if (scheduler != null) {
            scheduler.shutdown();
            scheduler = null;
        }
    }
}
