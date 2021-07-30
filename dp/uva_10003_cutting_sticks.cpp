/* Uva 10003 - Cutting sticks. [https://onlinejudge.org/external/100/10003.pdf]
*  Keys: dp
*/

#include <iostream>
#include <cstring>
#define MAX 2000000000

using namespace std;

int len, n, c[55], dp[55][55];

int solve(int l, int r) {
    if (l+1 == r) return dp[l][r] = 0;
    if (dp[l][r] != -1) return dp[l][r];
    dp[l][r] = MAX;
    for (int i = l+1; i < r; ++i) {
        dp[l][r] = min(dp[l][r], (c[r]-c[l]) + solve(l, i) + solve(i, r));
    }
    return dp[l][r];
}

int main() {
    while (true) {
        memset(dp, -1, sizeof(dp));
        scanf("%d", &len);
        if (len == 0) break;
        scanf("%d", &n);
        c[0] = 0; c[n+1] = len;
        for (int i = 1; i <= n; ++i)
            scanf("%d", &c[i]);
        printf("The minimum cutting is %d.\n", solve(0, n+1));                        

    }
    return 0;
}
