package graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Solution #1: DFS + Backtracking
 */
/*
public class CrackingTheSafe {

    private StringBuilder sb;
    private Set<String> visited;
    private int totalLengthOptimal;

    private boolean dfs(StringBuilder sb, int k, int n) {
        // base case
        if (sb.length() == totalLengthOptimal) return true;
        // iterate over all values not visited yet, backtrack
        String recentSeq = sb.substring(sb.length()-n+1);
        for (int i = 0; i < k; ++i) {
            String nextPassword = recentSeq + i;
            if (visited.contains(nextPassword)) continue;
            visited.add(nextPassword);
            sb.append(i);
            if (dfs(sb, k, n)) return true;
            // backtrack
            sb.setLength(sb.length()-1);
            visited.remove(nextPassword);
        }
        return false;
    }

    public String crackSafe(int n, int k) {
        sb = new StringBuilder();
        for (int i = 0; i < n; ++i) sb.append('0');
        visited = new HashSet<>();
        visited.add(sb.toString()); // added "00...0"
        totalLengthOptimal = (int) Math.pow(k, n) + n - 1;
        if (dfs(sb, k, n)) return sb.toString();
        return null;
    }

    public static void main(String[] args) {
        int n = 1, k = 2;
        String res = new CrackingTheSafe().crackSafe(n, k);
        System.out.println(res);
    }

}
*/

/**
 * Solution #2: HashMap: prefix(n-1 digits) -> next digit
 * Eg. n = 4, k = 3:             prefix = "000":
 *                               HashMap: "000" -> 2, then:
 *                                        "000" -> 1, then:
 *                                        "000" -> 0
 */
public class CrackingTheSafe {

    public String crackSafe(int n, int k) {
        int totalLengthOptimal = (int)Math.pow(k, n) + n - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n-1; ++i) sb.append('0');
        HashMap<String, Integer> map = new HashMap<>();
        while (sb.length() < totalLengthOptimal) {
            String prefix = sb.substring(sb.length()-n+1);
            map.put(prefix, map.getOrDefault(prefix, k)-1);
            sb.append(map.get(prefix));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int n = 2, k = 2;
        String res = new CrackingTheSafe().crackSafe(n, k);
        System.out.println(res);
    }

}