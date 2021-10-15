package graphs;

import java.util.*;

public class EvaluateDivision {
	private	Map<String, List<Pair<String, Double>>> graph = new HashMap<>();
	private Map<String, Boolean> visited = new HashMap<>();

	private void buildGraph(List<List<String>> equations, double[] values) {
		int n = equations.size();
		for (int i = 0; i < n; ++i) {
			List<String> pair = equations.get(i);
			String first = pair.get(0);
			String second = pair.get(1);

			List<Pair<String, Double>> adjListFirst = graph.getOrDefault(first, new ArrayList<>());
			adjListFirst.add(new Pair<String, Double>(second, values[i]));
			graph.put(first, adjListFirst);

			List<Pair<String, Double>> adjListSecond = graph.getOrDefault(second, new ArrayList<>());
			adjListSecond.add(new Pair<String, Double>(first, 1.0/values[i]));
			graph.put(second, adjListSecond);

			visited.put(first, false);
			visited.put(second, false);
		}
	}
	private double dfs(String src, String dst, double p) {
		if (!graph.containsKey(src) || !graph.containsKey(dst)) return -1;
		if (dst.equals(src)) return p;
		visited.put(src, true);
		List<Pair<String, Double>> adjList = graph.getOrDefault(src, new ArrayList<>());
		for (Pair<String, Double> pair : adjList) {
			String vertex = pair.first;
			double weight = pair.second;
			if (visited.get(vertex) == false) {
				double pp = dfs(vertex, dst, p*weight);
				if (pp > 0) return pp;
			}
		}
		visited.put(src, false);
		return -1;
	}
	private void reset() {
		for (String key : visited.keySet()) visited.put(key, false);
	}
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		buildGraph(equations, values);
		int n = queries.size();
		double[] res = new double[n];
		for (int i = 0; i < n; ++i) {
			List<String> pair = queries.get(i);
			String src = pair.get(0);
			String dst = pair.get(1);
			reset();
			res[i] = dfs(src, dst, 1);
		}
		return res;
	}
	private class Pair<K, V> {
		private K first;
		private V second;
		Pair(K first, V second) { this.first = first; this.second = second; }
	}

	public static void main(String[] args) {
		// equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
		// equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
		List<List<String>> equations = new ArrayList<>();
		equations.add(new ArrayList<>(Arrays.asList("a", "b")));
		equations.add(new ArrayList<>(Arrays.asList("b", "c")));
		equations.add(new ArrayList<>(Arrays.asList("bc", "cd")));
		double[] values = new double[] {1.5, 2.5, 5.0};
		List<List<String>> queries = new ArrayList<>();
		queries.add(new ArrayList<>(Arrays.asList("a", "c")));
		queries.add(new ArrayList<>(Arrays.asList("c", "b")));
		queries.add(new ArrayList<>(Arrays.asList("bc", "cd")));
		queries.add(new ArrayList<>(Arrays.asList("cd", "bc")));

		double[] res = new EvaluateDivision().calcEquation(equations, values, queries);
		for (double x : res) {
			System.out.printf(" %f", x);
		}
		System.out.println();
	}

}
