package eulerianpath;

import java.util.*;

public class ValidArrangementOfPairs {

	private Map<Integer, List<Integer>> graph=new HashMap<>();
	private Map<Integer, Integer> in=new HashMap<>();
	private Map<Integer, Integer> out=new HashMap<>();
	List<Integer> list=new ArrayList<>();

	// TODO: review: print the Eulerian Path
	private void dfs(int src) {
		List<Integer> adjList=graph.getOrDefault(src, new ArrayList<>());
		while (adjList.size()>0) {
			int v=adjList.get(adjList.size()-1);
			adjList.remove(adjList.size()-1);
			dfs(v);
		}
		list.add(src);
	}

	public int[][] validArrangement(int[][] pairs) {
		// build graph
		// in, out degrees: hashmap
		for (int[] p:pairs) {
			graph.putIfAbsent(p[0], new ArrayList<>());
			graph.get(p[0]).add(p[1]);
			in.putIfAbsent(p[1], 0);
			in.put(p[1], in.get(p[1])+1);
			out.putIfAbsent(p[0], 0);
			out.put(p[0], out.get(p[0])+1);

		}
		// find the starting node
		int src=pairs[0][0];
		for (int[] p:pairs) {
			if (out.getOrDefault(p[0], 0)-in.getOrDefault(p[0], 0)==1) {
				src=p[0]; break;
			}
			if (out.getOrDefault(p[1], 0)-in.getOrDefault(p[1], 0)==1) {
				src=p[1]; break;
			}
		}
		// dfs -> find list
		dfs(src);
		Collections.reverse(list);

		// return
		int n=pairs.length;
		int[][] res=new int[n][];
		for (int i=0; i<list.size()-1; ++i) {
			int u=list.get(i), v=list.get(i+1);
			res[i]=new int[]{u, v};
		}
		return res;
	}

}
