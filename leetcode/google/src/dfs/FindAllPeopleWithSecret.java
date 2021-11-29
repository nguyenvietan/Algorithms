// Caution: TLE Solution!!!

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


