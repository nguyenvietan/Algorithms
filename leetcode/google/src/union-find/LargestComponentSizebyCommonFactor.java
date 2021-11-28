class LargestComponentSizeByCommonFactor {

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
        int n = nums.length;
        UnionFind u = new UnionFind(n+1);
        Map<Integer, List<Integer>> map = new HashMap<>(); // map: prime -> list of nums[i] where nums[i]%prime == 0
        for (int i = 0; i < n; ++i) {
            // create list of factors
            List<Integer> factorsList = primeDecompose(nums[i]);
            for (int prime : factorsList) {
                map.putIfAbsent(prime, new ArrayList<>());
                map.get(prime).add(i);
            }
        }
        for (int key : map.keySet()) {
            List<Integer> list = map.get(key);
            for (int i = 1; i < list.size(); ++i) u.union(list.get(i), list.get(i-1));
        }
        // return max size
        int maxSize = 0;
        for (int i = 0; i < n+1; ++i) maxSize = Math.max(maxSize, u.getSizeOfSet(i));
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
