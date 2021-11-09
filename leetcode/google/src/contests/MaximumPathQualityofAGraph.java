package contests;

import java.util.ArrayList;
import java.util.List;

public class MaximumPathQualityofAGraph {
	private List<int[]>[] graph;
	private int ans;

	private void buildGraph(int n, int[][] edges) {
		graph = new List[n]; // keep in mind
		for (int i = 0; i < n; ++i) graph[i] = new ArrayList<>(); // keep in mind
		for (int[] e : edges) {
			graph[e[0]].add(new int[]{e[1], e[2]});		// so concise!
			graph[e[1]].add(new int[]{e[0], e[2]});		// so concise!
		}
	}

	private void dfs(int src, int time, int sum, int[] values, int[] visited, int maxTime) {
		if (time > maxTime) return;
		if (src == 0) { ans = Math.max(ans, sum); }
		for (int[] e : graph[src]) {
			int plus = visited[e[0]] == 0 ? values[e[0]] : 0;
			visited[e[0]]++;
			dfs(e[0], time + e[1], sum + plus, values, visited, maxTime);
			visited[e[0]]--;
		}
	}

	public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
		int n = values.length;
		int[] visited = new int[n];
		buildGraph(n, edges);
		visited[0]++;
		dfs(0, 0, values[0], values, visited, maxTime);
		return ans;
	}
}
