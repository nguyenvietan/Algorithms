package random;

/*
    Solution #1: hashmap + bit manipulation
    Technique: enumerate all submasks of a mask:
        while (submask > 0) submask = (submask-1) & mask;
    https://cp-algorithms.com/algebra/all-submasks.html
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class NumberOfValidWordsForEachPuzzle {

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> res = new ArrayList<>();
        // build hashmap: word bitmask -> count
        Map<Integer, Integer> map = new HashMap<>();
        for (String word : words) {
            int mask = getMask(word);
            map.put(mask, map.getOrDefault(mask, 0)+1);
        }
        // for each puzzle, enumerate all submasks of the puzzle, check if first-char-bit is set, count += map.get(submask)
        for (String puzzle : puzzles) {
            int puzzleMask = getMask(puzzle);
            int submask = puzzleMask, firstCharMask = 1 << puzzle.charAt(0)-'a';
            int count = 0;
            while (submask > 0) {
                if ((submask & firstCharMask) != 0) count += map.getOrDefault(submask, 0); // don't forget to check if the first-char bit is set
                submask = (submask-1) & puzzleMask; // keep in mind!!!
            }
            res.add(count);
        }
        return res;
    }

    private int getMask(String word) {
        int mask = 0;
        for (int i = 0; i < word.length(); ++i) {
            int idx = word.charAt(i) - 'a';
            mask |= 1 << idx;
        }
        return mask;
    }

}


