package dfs;
import java.util.*;

/**
 * Account Merge:
 * #Solution #1: DFS, work with a graph built from strings
 * #Solution #2: Union Find: work with hash maps and strings, identify groups to unite
 * [review]
 * */

// Solution #1: DFS
class AccountMerge {

    private Map<String, List<String>> graph;
    private Set<String> vs;

    private void buildGraph(List<List<String>> acc) {
        for (List<String> list : acc) {
            String name = list.get(0);
            String firstMail = list.get(1);
            for (int i = 2; i < list.size(); ++i) {
                String oMail = list.get(i);
                graph.putIfAbsent(firstMail, new ArrayList<>());
                graph.get(firstMail).add(oMail);
                graph.putIfAbsent(oMail, new ArrayList<>());
                graph.get(oMail).add(firstMail);
            }
        }            
    }

    private void dfs(String mail, List<String> temp) {
        vs.add(mail);
        temp.add(mail);
        // System.out.printf("added: %s\n", mail);
        for (String other : graph.getOrDefault(mail, new ArrayList<>())) {
            if (!vs.contains(other)) {
                dfs(other, temp);
            }
        }
    }
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        vs = new HashSet<>();
        graph = new HashMap<>();
        buildGraph(accounts);
        // dfs
        for (List<String> list : accounts) {
            String name = list.get(0);
            String firstMail = list.get(1);
            if (vs.contains(firstMail)) continue;
            List<String> temp = new ArrayList<String>();
            dfs(firstMail, temp);
            Collections.sort(temp); // review??
            temp.add(0, name); // review??
            res.add(temp);
        }
        return res;
    }
    
}
