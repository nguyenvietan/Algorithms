/* Uva 193 - Graph Coloring. [https://onlinejudge.org/external/1/193.pdf].
* Key: backtracking
* Nodes: 1 2 3 4 5 6 ... N
* Each node can be either black or white; use backtracking to color the graph,
* and make sure no two connected nodes are all black along the way.
* Time complexity (TC): as using backtracking, the TC depends on the
* depth of the recursion, and the TC of each layer.
*                    cn ----------------------> 2^0 * cn
                   /     \
                 cn      cn ------------------> 2^1 * cn
                /  \    /  \
               cn  cn  cn  cn ----------------> 2^2 * cn
                    ...
        overall TC = (2^0 + 2^1 + ... + 2^(n-1)) * cn
                   = 2^n * cn
        =>      TC = O(2^n * n) 
*/
#include <iostream>
#include <utility>
#include <vector>
#include <string.h>

#define MAX 105
#define WHITE true
#define BLACK false

using namespace std;

int n, k;
int ans;
bool colors[MAX];
bool ans_colors[MAX];
vector<int> graph[MAX];

void init() {
    for (int i = 0; i < MAX; ++i) {
        graph[i].clear();
        colors[i] = WHITE;
        ans_colors[i] = WHITE;
    }
    ans = 0;        
}

int count_black_nodes() {
    int cnt = 0;
    for (int i = 0; i < MAX; ++i) cnt += colors[i] == BLACK;
    return cnt;
}

// make sure no two connected nodes are all black
bool is_valid(int node) {
    if (colors[node] == WHITE) return true;
    for (int i = 0; i < (int)graph[node].size(); ++i) {
        int x = graph[node][i];
        if (colors[x] == BLACK) return false;
    }
    return true;
}

void backtrack(int node, int num) {
    if (num == n) {
        int n_black_nodes = count_black_nodes();
        if (n_black_nodes > ans) {
            ans = n_black_nodes;
            for (int i = 0; i < MAX; ++i) ans_colors[i] = colors[i];
        }
        return;
    }
    colors[node] = WHITE;
    backtrack(node + 1, num + 1);
    colors[node] = BLACK;
    if (is_valid(node)) backtrack(node + 1, num + 1);
    colors[node] = WHITE;
}

int main() {
    int m;
    cin >> m;
    while (m--) {
        init();
        cin >> n >> k;
        int a, b;
        for (int i = 0; i < k; ++i) {
            cin >> a >> b;
            a--; b--;
            graph[a].push_back(b);
            graph[b].push_back(a); // keep in mind: undirected graph requires two-way insertion to graph map
        }
        backtrack(0, 0);
        printf("%d\n", ans);
        bool first = true;
        for (int i = 0; i < n; ++i) {
            if (ans_colors[i] == BLACK && !first) {
                printf (" %d", i + 1);
            }
            if (ans_colors[i] == BLACK && first) {
                first = false;
                printf ("%d", i + 1);
            }
        }
        printf("\n");
    }
    return 0;
}
