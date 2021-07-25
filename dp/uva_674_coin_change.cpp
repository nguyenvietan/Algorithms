/* Uva 674 - Coin change. [https://onlinejudge.org/external/6/674.pdf]
*  Key: dp (states, top-down recursion, bottom-up)
*/
#include <iostream>
#include <cstring>

using namespace std;

int n = 5, val;
int v[5] = {1, 5, 10, 25, 50};
int dp[6][7500];

int solve(int i, int val) {
	// base cases
	if (val == 0) return 1;
	if (i == n || val < 0) return 0;
	if (dp[i][val] != -1) return dp[i][val];
	// recursion
	return dp[i][val] = solve(i, val-v[i]) + solve(i+1, val);	
}

int main() {
	memset(dp, -1, sizeof(dp)); // only need to initialize this once
	while (scanf("%d", &val) > 0)
		printf("%d\n", solve(0, val));
	return 0;
}
