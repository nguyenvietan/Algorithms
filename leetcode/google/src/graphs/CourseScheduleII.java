package graphs;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Solution #1: dfs: visited[], visitedDetectCycle[]
 */

// public class CourseScheduleII {
//
// 	private	HashMap<Integer, List<Integer>> graph = new HashMap<>();
// 	private List<Integer> res = new ArrayList<>();
// 	private boolean[] visitedDetectCycle;
// 	private boolean[] visited;
// 	private boolean isCycle = false;
//
// 	private void buildGraph(int[][] prerequisites) {
// 		for (int[] p : prerequisites) {
// 			List<Integer> list = graph.getOrDefault(p[1], new ArrayList<>());
// 			list.add(p[0]);
// 			graph.put(p[1], list);
// 		}
// 	}
//
// 	private void dfs(int node) {
// 		if (isCycle) return;
// 		visited[node] = true;
// 		visitedDetectCycle[node] = true;
// 		for (int i = 0; i < graph.getOrDefault(node, new ArrayList<>()).size(); ++i) {
// 			int v = graph.get(node).get(i);
// 			if (visitedDetectCycle[v]) { isCycle = true; return; }
// 			if (!visited[v]) dfs(v);
// 		}
// 		res.add(node);
// 		System.out.println("added " + node);
// 		visitedDetectCycle[node] = false; // backtracking
// 	}
//
// 	public int[] findOrder(int numCourses, int[][] prerequisites) {
// 		visited = new boolean[numCourses];
// 		visitedDetectCycle = new boolean[numCourses];
// 		buildGraph(prerequisites);
// 		for (int i = 0; i < numCourses; ++i) {
// 			if (!visited[i]) dfs(i);
// 			if (isCycle) return new int[]{};
// 		}
// 		Collections.reverse(res);
// 		int tmp[] = new int[numCourses];
// 		for (int i = 0; i < numCourses; ++i) tmp[i] = res.get(i);
// 		return tmp;
// 	}
// 	public static void main(String[] args) {
// 		int[][] prerequisites = new int[][]{ {1,0}, {2,0}, {3,1}, {3,2}, {0, 3} };
// 		CourseScheduleII schedule = new CourseScheduleII();
// 		int[] res = schedule.findOrder(prerequisites.length, prerequisites);
// 		for (int x : res) System.out.printf(" %d", x);
// 		System.out.println();
// 	}
// }

/**
 * Solution #2: Node degree: degrees[], visited[]
 */
public class CourseScheduleII {

	private HashMap<Integer, List<Integer>> graph = new HashMap<>();
	private List<Integer> res = new ArrayList<>();
	private int[] degrees;
	private boolean[] visited;

	private void buildGraph(int[][] prerequisites) {
		for (int[] p : prerequisites) {
			List<Integer> list = graph.getOrDefault(p[1], new ArrayList<>());
			list.add(p[0]);
			graph.put(p[1], list);
		}
	}

	private void calculateDegrees(int[][] prerequisites) {
		for (int[] p : prerequisites) degrees[p[0]]++;
	}

	private void dfs(int node) {
		res.add(node);
		visited[node] = true;
		for (int i = 0; i < graph.getOrDefault(node, new ArrayList<>()).size(); ++i) {
			int v = graph.get(node).get(i);
			degrees[v]--;
			if (degrees[v] == 0) dfs(v);
		}
	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		degrees = new int[numCourses];
		visited = new boolean[numCourses];
		buildGraph(prerequisites);
		calculateDegrees(prerequisites);
		for (int i = 0; i < numCourses; ++i) {
			if (degrees[i] == 0 && !visited[i]) dfs(i);
		}
		if (res.size() < numCourses) return new int[]{};
		int[] tmp = new int[numCourses];
		for (int i = 0; i < numCourses; ++i) tmp[i] = res.get(i);
		return tmp;
	}

	public static void main(String[] args) {
		// int[][] prerequisites = new int[][]{ {1,0}, {2,0}, {3,1}, {3,2} };
		// int[][] prerequisites = new int[][]{ {1,0}, {3,2}};
		int[][] prerequisites = new int[][]{ {0,1} };
		int numCourses = 2;
		CourseScheduleII schedule = new CourseScheduleII();
		int[] res = schedule.findOrder(numCourses, prerequisites);
		for (int x : res) System.out.printf(" %d", x);
		System.out.println();
	}
}