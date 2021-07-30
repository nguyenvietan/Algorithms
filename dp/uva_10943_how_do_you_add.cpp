/* Uva 10943 - How do you add?. [https://onlinejudge.org/external/109/10943.pdf]
*/
/* top-down */
/*
#include <iostream>
#include <cstring>

using namespace std;

int n, k, dp[105][105];

int solve(int i, int s) {
    if (i == k) return s == 0 ? 1 : 0;
    if (s == 0) return 1;
    if (dp[i][s] != -1) return dp[i][s];
    dp[i][s] = 0;
    for (int x = 0; x <= s; ++x) {
        dp[i][s] = (dp[i][s] + solve(i+1, s-x)) % 1000000;
    }
    return dp[i][s];
}

int main() {
    while (1) {
        memset(dp, -1, sizeof(dp));
        scanf("%d%d", &n, &k);
        if (n == 0 && k == 0) break;
        printf("%d\n", solve(0, n) % 1000000);                
    }
    return 0;
}
*/

/* bottom-up */
#include <iostream>
#include <cstring>

using namespace std;

int main() {
    int n, k, dp[105][105];
    while (1) {
        memset(dp, 0, sizeof(dp));
        scanf("%d%d", &n, &k);
        if (n == 0 && k == 0) break;
        for (int i = 0; i <= n; ++i) {
            dp[k-1][i] = 1;
        }
        for (int i = k-2; i >= 0; --i) {
            for (int j = 0; j <= n; ++j) {
                for (int x = 0; x <= j; ++x) {
                    dp[i][j] = (dp[i][j] + dp[i+1][x]) % 1000000;
                }
            }
        }
        printf("%d\n", dp[0][n] % 1000000);
    }
    return 0;
}
