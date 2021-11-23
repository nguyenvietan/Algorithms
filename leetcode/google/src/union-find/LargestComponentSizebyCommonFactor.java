class Solution {

    private class UnionFind {
        private int[] parent, ranks, sizes;
        public UnionFind(int n) {
            this.parent = new int[n];
            this.ranks = new int[n];
            this.sizes = new int[n];
            for (int i = 0; i < n; ++i) { this.ranks[i] = 0; parent[i] = i; sizes[i] = 1; }
        }
        public void union(int i, int j) {
            int pi = find(i), pj = find(j);
            if (pi == pj) return; // don't forget
            if (ranks[pi] == ranks[pj]) { parent[pj] = pi; sizes[pi] += sizes[pj]; }
            else if (ranks[pi] < ranks[pj]) { parent[pi] = pj; sizes[pj] += sizes[pi]; }
            else { parent[pj] = pi; sizes[pi] += sizes[pj]; }
        }
        public int find(int i) { return (i == parent[i]) ? i : (parent[i] = find(parent[i])); }
        public int getSizeOfSet(int i) { return sizes[find(i)]; }
    }

    private int[] primes;

    public int largestComponentSize(int[] nums) {
        UnionFind u = new UnionFind(100005);
        Map<Integer, Integer> map = new HashMap<>(); // map: num -> first prime factor
        for (int x : nums) {
            // create list of factors
            List<Integer> factorsList = primeDecompose(x);

            map.put(x, factorsList.get(0));

            // union join pairs of factors
            for (int i = 1; i < factorsList.size(); ++i) {
                u.union(factorsList.get(i-1), factorsList.get(i));
            }
        }
        // return max size
        int maxSize = 0;
        for (int k : map.keySet()) {
            int val = map.get(k);
            maxSize = Math.max(maxSize, u.getSizeOfSet(val)+1);
        }
        return maxSize;
    }

    List<Integer> primeDecompose(int num) {
        List<Integer> primeFactors = new ArrayList<Integer>();
        int factor = 2;

        while (num >= factor * factor) {
            if (num % factor == 0) {
                primeFactors.add(factor);
                num = num / factor;
            } else {
                factor += 1;
            }
        }
        primeFactors.add(num);
        return primeFactors;
    }
}
