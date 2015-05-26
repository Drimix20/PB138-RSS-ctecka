package pb138.rss.gui;

/**
 *
 * @author Martin Drimal
 * @UCO 373769
 */
public enum TimeInterval {

    OneMinute("1 min", 60), FiveMinute("5 min", 300), HalfHour("30 min", 1800), OneHours("1 hour", 3600), TwoHours("2 hours", 7200), FiveHours("5 hours", 18000);
    private String name;
    private int timeInMillis;

    private TimeInterval(String name, int time) {
        this.name = name;
        this.timeInMillis = time;
    }

    public int getTime() {
        return timeInMillis;
    }

    @Override

    public String toString() {
        return name;
    }
}
