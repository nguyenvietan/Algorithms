// Caution: TLE Solution!!!

import java.util.*;

// Solution #1: DFS
class FindAllPeopleWithSecret {
	
	private Set<Integer> set;

	public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
		set = new HashSet<>();
		set.add(0); set.add(firstPerson);
		// map: time -> [xi, yi]
        int maxT = 0;
		Map<Integer, List<int[]>> map = new HashMap<>();
		for (int[] m : meetings) {
			map.putIfAbsent(m[2], new ArrayList<>());
			map.get(m[2]).add(new int[]{m[0], m[1]});
            maxT = Math.max(maxT, m[2]);
		}
		// set{}; for each time t, build a new graph, dfs on set{}, add new person
		for (int t = 0; t <= maxT; ++t) {
			if (!map.containsKey(t)) continue;
			List<int[]> list = map.get(t);
			Map<Integer, List<Integer>> graph = new HashMap<>();
            Set<Integer> seen = new HashSet<>(); // only dfs from this subset. if dfs from the whole set -> TLE!!!
			for (int[] e : list) {
				graph.putIfAbsent(e[0], new ArrayList<>());
				graph.get(e[0]).add(e[1]);
				graph.putIfAbsent(e[1], new ArrayList<>());
				graph.get(e[1]).add(e[0]);				
                if (set.contains(e[0])) seen.add(e[0]);
                if (set.contains(e[1])) seen.add(e[1]);
            }
            Queue<Integer> q = new LinkedList<>();
            for (int x: seen) q.add(x);
            while (!q.isEmpty()) {
                int v = q.poll();
                for (int x : graph.getOrDefault(v, new ArrayList<>())) {
                    if (!set.contains(x)) {
                        set.add(x);
                        q.add(x);
                    }
                }
            }
        }
		List<Integer> res = new ArrayList<>();
		for (int x : set) res.add(x);
		return res;		
	}

}

// Solution #2: Priority Queue
/*
class FindAllPeopleWithSecret {

    private static final int oo = Integer.MAX_VALUE;

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // build graph: u -> (v, weight)
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] m : meetings) {
            int u = m[0], v = m[1], t = m[2];
            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(new int[]{v, t});
            graph.putIfAbsent(v, new ArrayList<>());
            graph.get(v).add(new int[]{u, t});
        }
        // dist
        int[] dist = new int[n];
        Arrays.fill(dist, oo);
        // pq
        PriorityQueue<int[]> pq = new PriorityQueue<>((u, v) -> (u[1] - v[1])); // (v, weight)
        pq.add(new int[]{0, dist[0] = 0});
        pq.add(new int[]{firstPerson, dist[firstPerson] = 0});
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int u = top[0], t = top[1];
            for (int[] v : graph.getOrDefault(u, new ArrayList<>())) {
                int v1 = v[0], t1 = v[1];
                if (t1 < t) continue;
                if (t1 < dist[v1]) pq.add(new int[]{v1, dist[v1] = t1}); // be careful, (0-5, w=7) and (0-5, w=18) can be coincident
            }
        }
        // return list
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; ++i) if (dist[i] < oo) res.add(i);
        return res;
    }

}

*/
