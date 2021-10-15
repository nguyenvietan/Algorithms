package graphs;

public class UnionFind {
    private int n;
    private int[] parent, rank, size;
    private int numDisjointSets;
    public UnionFind(int n) {
        this.n = n;
        this.numDisjointSets = n;
        rank = new int[n];
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    public void union(int i, int j) {
        int pi = find(i);
        int pj = find(j);
        if (pi == pj) return;
        if (rank[pi] > rank[pj]) {
            parent[pj] = pi;
            size[pi] += size[pj];
        } else if (rank[pi] == rank[pj]) {
            parent[pj] = pi;
            rank[pi]++;
            size[pi] += size[pj];
        } else {
            parent[pi] = pj;
            size[pj] += size[pi];
        }
        numDisjointSets--;
    }

    /**
     * find() with compresion: time complexity O(logN), but without compression: O(N)
     */
    public int find(int i) {
        return i == parent[i] ? i : (parent[i] = find(parent[i]));
    }

    public boolean isSameSet(int i, int j) {
        return find(i) == find(j);
    }
    public int getNumDisjointSets() { return numDisjointSets; }
    public int sizeOfSet(int i) {
        int p = find(i);
        return size[p];
    }

    public static void main(String[] args) {
        int n = 5;
        UnionFind unionFind = new UnionFind(n);
        unionFind.union(1, 3);
        unionFind.union(1, 4);
        unionFind.union(0, 2);

        System.out.printf("Parent[]:");
        for (int i = 0; i < n; ++i) {
            System.out.printf(" %d", unionFind.parent[i]);
        }
        System.out.println();
        System.out.printf("Rank[]:");
        for (int i = 0; i < n; ++i) {
            System.out.printf(" %d", unionFind.rank[i]);
        }
        System.out.println();
        System.out.println("num sets: " + unionFind.getNumDisjointSets());
        System.out.printf("sizeOfSet[]:");
        for (int i = 0; i < n; ++i) {
            System.out.printf(" %d", unionFind.sizeOfSet(i));
        }
    }
}
