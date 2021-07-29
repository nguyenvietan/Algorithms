/* Uva 10496 - Collecting Beepers. [https://onlinejudge.org/external/104/10496.pdf]
* Keys: Traveling Salesman Problem
*/
#include <iostream>
#include <vector>
#include <cstring>
#include <utility>
#include <climits>

using namespace std;

int t, x_max, y_max, x, y, n, dp[12][1100];
pair<int, int> p0;
vector<pair<int, int>> v;

void init() {
    memset(dp, -1, sizeof(dp));
    v.clear();
}

inline int dist(pair<int, int> x, pair<int, int> y) {
    return abs(x.first - y.first) + abs(x.second - y.second);
}

int solve(int s, int i) {
    if (__builtin_popcount(s) == 1) return dp[i][s] = dist(p0, v[i]);
    if (dp[i][s] != -1) return dp[i][s];
    dp[i][s] = INT_MAX;
    for (int j = 0; j < n; ++j) {
        if (j != i && (s & (1 << j))) {
            int s_nxt = s & ~(1 << i);
            dp[i][s] = min(dp[i][s], dist(v[i], v[j]) + solve(s_nxt, j));
        }
    }
    return dp[i][s];
}

int main() {
    scanf("%d", &t);
    while (t--) {
        init();
        scanf("%d%d", &x_max, &y_max);
        scanf("%d%d", &p0.first, &p0.second);
        scanf("%d", &n);
        for (int i = 0; i < n; ++i) {
            scanf("%d%d", &x, &y);
            v.push_back(make_pair(x, y));
        }
        int ans = INT_MAX;
        int s = (1 << n) - 1;
        for (int i = 0; i < n; ++i) {
            ans = min(ans, dist(p0, v[i]) + solve(s, i));
        }
        printf("The shortest path has length %d\n", ans);
    }
    return 0;
}
