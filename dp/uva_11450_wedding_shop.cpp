/* Uva 11450 Wedding Shop. [https://onlinejudge.org/external/114/11450.pdf]
*  Keys: DP (define states, employ recursion)
*/
#include <iostream>
#include <vector>
#include <climits>
#include <cstring>

using namespace std;

int n, m, c, k, x;
vector<vector<int>> v;
int dp[25][205];

void init() {
    v.clear();
    v.resize(c);
    memset(dp, -1, sizeof(dp));
}

int f(int level, int m) {
    if (m < 0) return dp[level][m] = -INT_MAX;
    if (level == c) return 0; 
    if (dp[level][m] != -1) return dp[level][m];
    int tmp = -INT_MAX;
    for (int i = 0; i < v[level].size(); ++i) {
        tmp = max(tmp, v[level][i] + f(level+1, m-v[level][i]));
    }
    return dp[level][m] = tmp;
}

int main() {
    cin >> n;
    while (n--) {
        cin >> m >> c;
        init();
        for (int i = 0; i < c; ++i) {
            cin >> k;
            while (k--) {
                cin >> x;
                v[i].push_back(x);
            }
        }
        int ans = f(0, m);
        if (ans < 0) printf("no solution\n");
        else printf("%d\n", ans);
    }
    return 0;
}
