/* IOI 2011. Day 1, Task 2: Race. [https://ioinformatics.org/files/ioi2011problem2.pdf]
*  Submission: []
*  Keys: + Divide and Conquer: O(N^2) -> O(Nlog(N));
*          find the central node -> the problem to subproblems (max of log(N) levels),
*          try to solve each subproblem in O(N)
*        + DFS + Memoization: O(N^2) -> O(N)
*/

// #include <iostream>
#include <utility>
#include <cstring>
#include <vector>
#define MAX 1000000000

using namespace std;

int n, k;
vector<bool> visited, vs2;
vector<vector<pair<int, int>> graph;
vector<int> descendants;

void init() {
    graph.clear();
    graph.resize(n);
    visited.clear();
    visited.resize(n, false);
    vs2.clear();
    vs2.resize(n, false);
}

int dfs(int node) {
    if (graph[node].size() == 1) {
        descendants[node] = 0;
        return 1;
    }
    int n_desc = 0;
    vs2[node] = true;
    for (int i = 0; i < graph[node].size(); ++i) {
        int v = graph[node][i].first;
        if (!visited[v] && !vs2[v]) {
            if (descendants[v] >= 0) cnt += 1 + descendants[v];
            else n_desc += dfs(v);
        }
    }
    descendants[node] = n_desc;
    return 1 + descendants[node];
}

int find_central_node(int root) {
    descendants.clear();
    descendants.resize(n, -1);
    dfs(root);
    // calculate number of nodes in the sub-tree
    int n_nodes = 0;
    for (int i = 0; i < n; ++i) n_nodes += descendants[i] >= 0;
    int central_node = -1;
    for (int i = 0; i < n; ++i) {
        if (descendants[i] < 0) continue;
        bool ok = true;
        for (int j = 0; j < graph[i].size(); ++j) {
            int v = graph[i][j].first;
            if (descendants[v] > descendants[i]) {
                int n_nodes_on_parent_branch = n_nodes - 1 - descendants[i];
                if (n_nodes_on_parent_branch > n_nodes/2) { ok = false; break; }
            } else {
                int n_nodes_on_child_branch = 1 + descendants[i];
                if (n_nodes_on_child_branch > n_nodess/2) { ok = false; break; }
            }
        }
        if (ok) {central_node = i; break; }
    }
    return central_node;
}

int find_path_starting_from(int cnode, int length_remaining) {
    if (length_remaining == 0) return 1;
    if (length_remaining < 0 || graph[cnode].size() == 1) return MAX;
    int ans = MAX;
    vs2[cnode] = true;
    for (int i = 0; i < graph[cnode].size(); ++i) {
        int v = graph[cnode][i].first;
        int l = graph[cnode][i].second;
        if (!visited[v] && !vs2[v]) {
            ans = min(ans, 1 + find_path_starting_from(v, length_remaining-l));
        }
    }
    return ans;
}

vector<int> p, p_next;
void dfs2(int root, int level, int length) {
                                                
}

int find_path_containing_not_at_end_points(int cnode) {
    // dfs + memoization
    p.resize(k+1, -1);
    p_next.resize(k+1, -1);
    for (int i = 0; i < graph[cnode].size(); ++i) {
        int v = graph[cnode][i].first;
        int l = graph[cnode][i].second;
        dfs2(v, 1, l);   // update p_next
        // copy p_next -> p
        for (int i = 0; i < p.size(); ++i) p[i] = p_next[i];
    }
}

int solve(int root) {
    // 1. find the central node u
    // DFS starts with node root, computes the number of descendants of each node
    vs2.clear(); vs2.resize(n, false);
    int u = find_central_node(root);

    // 2. find valid paths containing the central node u
    // return the optimal path: optimal_tmp
    // + paths starting from u: use dfs
    vs2.clear(); vs2.resize(n, false);
    int tmp_1 = find_path_starting_from(u, 1, k);
    // + paths containing u (not at end points): memoization, dp
    vs2.clear(); vs2.resize(n, false);
    int tmp_2 = find_path_containing_not_at_end_points(u);
    int ans = min(tmp_1, tmp_2);

    visited[u] = true;
    // 3. return min(ans_tmp, solve(r1), solve(r2), ...)
    for (int i = 0; i < graph[u].size(); ++i) {
        int v = graph[u][i].first;
        ans = min(ans, solve(v));
    }
    return ans;
}

int main() {

    // input
    cin >> n >> k;

    return 0;
}
