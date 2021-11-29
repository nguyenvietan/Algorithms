/**
 * Account Merge:
 * #Solution #1: DFS, work with a graph built from strings
 * #Solution #2: Union Find: work with hash maps and strings, identify groups to unite
 * [review]
 * */

// Solution #2: Union Find
class AccountMerge {
	
	private class UnionFind {
		private int[] ranks, parent;		
		public UnionFind(int n) {
			this.ranks = new int[n];
			this.parent = new int[n];
			for (int i = 0; i < n; ++i) { this.parent[i] = i; this.ranks[i] = 0; }
		}
		public int find(int i) { return i == parent[i] ? i : (parent[i] = find(parent[i])); }
		public void union(int i, int j) {
			int pi = find(i), pj = find(j);
			if (pi == pj) return;
			if (ranks[pi] == ranks[pj]) { parent[pj] = pi; ranks[pi]++; }
			else if (ranks[pi] < ranks[pj]) { parent[pi] = pj; }
			else { parent[pj] = pi; }
		}
	}

	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		int n = accounts.size();
		UnionFind uf = new UnionFind(n);
		// union groups: line-by-line; hashmap<string, int> - mail2group
		Map<String, Integer> mail2GroupMap = new HashMap<>();
		for (int i = 0; i < n; ++i) {
			for (int j = 1; j < accounts.get(i).size(); ++j) {
				String mail = accounts.get(i).get(j);
				if (mail2GroupMap.containsKey(mail)) uf.union(i, mail2GroupMap.get(mail));
				else mail2GroupMap.put(mail, i);
			}
		}
		// hashmap<int, list<string>>: map.get(find(i)).add(...)
		Map<Integer, List<String>> group2MailListMap = new HashMap<>();
		for (String mail : mail2GroupMap.keySet()) {
			int p = uf.find(mail2GroupMap.get(mail));
			group2MailListMap.putIfAbsent(p, new ArrayList<>());
			group2MailListMap.get(p).add(mail);
		}

		// sort, add name for each group, and return
		List<List<String>> res = new ArrayList<>();
		for (int i : group2MailListMap.keySet()) {
			List<String> list = group2MailListMap.get(i);
			Collections.sort(list);
			list.add(0, accounts.get(i).get(0));
			res.add(new ArrayList<>(list));
		}
		return res;		
	}

}


