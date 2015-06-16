package pb138.rss.listener;

import org.apache.log4j.Logger;
import pb138.rss.feed.Container;

/**
 *
 * @author Drimal
 */
public class RssFeedContainerChangeListener implements ContainerChangeListener {

    private static Logger logger = Logger.getLogger(RssFeedContainerChangeListener.class);

    @Override
    public void containerChanged(Container container) {
        logger.info("Container changed, I can write to file");
    }

}
