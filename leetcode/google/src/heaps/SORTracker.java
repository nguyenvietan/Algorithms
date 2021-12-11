package heaps;
import java.util.PriorityQueue;
/**
 * Biweekly contest 67: Sequentially Ordinal Rank Tracker
 */
public class SORTracker {
    PriorityQueue<Entry> pqMax;
    PriorityQueue<Entry> pqMin;
    public SORTracker() {
        pqMax = new PriorityQueue<>((a, b) -> (a.score == b.score) ? a.name.compareTo(b.name) : b.score - a.score);
        pqMin = new PriorityQueue<>((a, b) -> (a.score == b.score) ? b.name.compareTo(a.name) : a.score - b.score);
    }
    public void add(String name, int score) {
        Entry e = new Entry(name, score);
        pqMin.add(e);
        pqMax.add(pqMin.poll());
    }
    public String get() {
        Entry e = pqMax.poll();
        pqMin.add(e);
        return e.name;
    }
    private class Entry {
        private String name;
        private int score;
        public Entry(String name, int score) { this.name = name; this.score = score; }
    }
}
