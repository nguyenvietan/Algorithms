/* Uva 10130 - Supersale. [https://onlinejudge.org/external/101/10130.pdf]
*  Keys: dp (states, top-down recursion/bottom-up)
*/

// Solution #1: Bottom-up
#include <iostream>
#include <cstring>

#define MAX_N 1001
#define MAX_W 31

using namespace std;

int t, n, p, w, g, mw;
int vp[MAX_N], vw[MAX_N];
int dp[MAX_N][MAX_W];

int main() {
    scanf("%d", &t);
    while (t--) {
        memset(dp, -1, sizeof(dp));
        scanf("%d", &n);
        for (int i = 0; i < n; ++i) scanf("%d%d", &vp[i], &vw[i]);

        // bottom-up state calculation
        for (int i = 0; i < MAX_W; ++i)
            dp[n-1][i] = (i < vw[n-1]) ? 0 : vp[n-1];
        for (int i = n-2; i >= 0; --i) {
            for (int j = 0; j < MAX_W; ++j) {
                if (j < vw[i]) dp[i][j] = dp[i+1][j];
                else dp[i][j] = max(vp[i] + dp[i+1][j-vw[i]], dp[i+1][j]);                
            }
        }

        scanf("%d", &g);
        int s_max = 0;
        while (g--) {
            scanf("%d", &mw);
            s_max += dp[0][mw];
        }
        printf("%d\n", s_max);
    }
    return 0;
}

/* Solution #2: Top-down */
/*
#include <iostream>
#include <cstring>

#define MAX_N 1001
#define MAX_W 31

using namespace std;

int t, n, p, w, g, mw;
int vp[MAX_N], vw[MAX_N];
int dp[MAX_N][MAX_W];

int solve(int i, int _mw) {
    if (i == n) return 0;
    if (dp[i][_mw] != -1) return dp[i][_mw];
    if (_mw < vw[i]) return dp[i][_mw] = solve(i+1, _mw);
    return dp[i][_mw] = max(vp[i] + solve(i+1, _mw-vw[i]), solve(i+1, _mw));
}

int main() {
    scanf("%d", &t);
    while (t--) {
        memset(dp, -1, sizeof(dp));
        scanf("%d", &n);
        for (int i = 0; i < n; ++i) scanf("%d%d", &vp[i], &vw[i]);
        scanf("%d", &g);
        int s_max = 0;
        while (g--) {
            scanf("%d", &mw);
            s_max += solve(0, mw);
        }
        printf("%d\n", s_max);
    }
    return 0;
}
 */
