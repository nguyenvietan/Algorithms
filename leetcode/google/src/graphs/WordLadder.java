package graphs;

import java.util.*;

/**
 * HashMap: build an undirected + unweighted graph
 * BFS: find shortest path length
 * Queue: LinkedList
 */
public class WordLadder {

    private HashMap<String, List<String>> wildCardDict = new HashMap<>();
    private HashMap<String, Boolean> visited = new HashMap<>();

    private void buildWildCardList(List<String> wordList) {
        for (String w : wordList) {
            for (String ww : getWildCardList(w)) {
                List<String> list = wildCardDict.getOrDefault(ww, new LinkedList<String>());
                list.add(w);
                wildCardDict.put(ww, list);
            }
        }
    }

    /** Solution #1: BFS: one direction: beginWord -> ... -> endWord
     * Time complexity: O(N*L^2), where N is the length of the word list, L is the length of a word
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 1. build a hashmap for wildcard matches
        buildWildCardList(wordList);

        // 2. BFS: (word, level); begin -> ... -> end
        Queue<Pair<String, Integer>> q = new LinkedList<>();
        q.add(new Pair<String, Integer>(beginWord, 1));
        visited.put(beginWord, true);
        while (!q.isEmpty()) {
            Pair<String, Integer> p = q.poll();
            for (String wildCardWord : getWildCardList(p.first)) {
                List<String> list = wildCardDict.getOrDefault(wildCardWord, null);
                if (list == null) continue;
                for (String word : list) {
                    if (!visited.getOrDefault(word, false)) {
                        if (word.equals(endWord)) return p.second + 1;
                        visited.put(word, true);
                        q.add(new Pair<String, Integer>(word, p.second + 1));
                    }
                }
            }
        }
        return 0;
    }

    /**
     * Solution #2: beginWord -> ... <- endWord
     * Time complexity: O(N*L^2), where N is the length of the word list, L is the length of a word
     */
    private HashMap<String, Integer> visitedBegin = new HashMap<>();
    private HashMap<String, Integer> visitedEnd = new HashMap<>();
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        // 1. build a hashmap for wildcard matches
        buildWildCardList(wordList);

        // 2. BFS: bi-direction: begin -> ... <- end
        Queue<String> qBegin = new LinkedList<>();
        Queue<String> qEnd = new LinkedList<>();
        qBegin.add(beginWord);
        qEnd.add(endWord);
        visitedBegin.put(beginWord, 1);
        visitedEnd.put(endWord, 1);
        while (!qBegin.isEmpty() && !qEnd.isEmpty()) {
            // begin -> ...
            String word = qBegin.poll();
            for (String wildCardWord : getWildCardList(word)) {
                List<String> listBegin = wildCardDict.getOrDefault(wildCardWord, null);
                if (listBegin == null) continue;
                for (String w : listBegin) {
                    if (visitedEnd.containsKey(w)) return visitedBegin.get(word) + visitedEnd.get(w);
                    if (!visitedBegin.containsKey(w)) {
                        visitedBegin.put(w, visitedBegin.get(word) + 1);
                        qBegin.add(w);
                    }
                }
            }
            // ... <- end
            word = qEnd.poll();
            for (String wildCardWord : getWildCardList(word)) {
                List<String> listEnd = wildCardDict.getOrDefault(wildCardWord, null);
                if (listEnd == null) continue;
                for (String w : listEnd) {
                    if (visitedBegin.containsKey(w)) return visitedEnd.get(word) + visitedBegin.get(w);
                    if (!visitedEnd.containsKey(w)) {
                        visitedEnd.put(w, visitedEnd.get(word) + 1);
                        qEnd.add(w);
                    }
                }
            }
        }
        return 0;
    }

    private class Pair<K, V> {
        private K first;
        private V second;
        public Pair(K first, V second) { this.first = first; this.second = second; }
    }

    private List<String> getWildCardList(String w) {
        List<String> list = new LinkedList<String>();
        for (int i = 0, L = w.length(); i < L; ++i) {
            String ww = w.substring(0, i) + '*' + w.substring(i+1, L);
            list.add(ww);
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> wordList = new LinkedList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        // List<String> wordList = new LinkedList<>(Arrays.asList("abc", "abx", "azc"));
        WordLadder wordLadder = new WordLadder();
        String beginWord = "hit";
        String endWord = "hot";
        int res2 = wordLadder.ladderLength2(beginWord, endWord, wordList);
        System.out.println(res2);
        int res1 = wordLadder.ladderLength(beginWord, endWord, wordList);
        System.out.println(res1);
    }

}
