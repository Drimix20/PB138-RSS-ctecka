package pb138.rss.reader;

import pb138.rss.gui.ReaderUI;

/**
 * Main class for running application
 *
 * @author Martin Drimal
 * @UCO 373769
 */
public class Main {

    public static void main(String[] args) {
        ReaderUI rssFeedApplication = new ReaderUI();
        rssFeedApplication.setLocationRelativeTo(null);
        rssFeedApplication.setVisible(true);
    }
}
