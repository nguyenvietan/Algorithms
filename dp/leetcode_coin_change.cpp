/* DP: based on previously-solved solution */
class Solution {
public:
    
    const int LIMIT = 1000000;
    
    int coinChange(vector<int>& coins, int amount) {
        int dp[10005];
        for (int i = 0; i <= amount; ++i) dp[i] = LIMIT;
        dp[0] = 0;
        for (int i = 1; i <= amount; ++i) {
            for (int c : coins) {
                if (i >= c) dp[i] = min(dp[i], 1 + dp[i-c]);
             }
        }
        return dp[amount] == LIMIT ? -1 : dp[amount];
    }
};


/* bottom-up approach */
// class Solution {
// public:

//     const int LIMIT = 1000000;
//     int dp[15][10005];
//     int coinChange(vector<int>& coins, int amount) {
//         int n = coins.size();
//         memset(dp, -1, sizeof(dp));

//         for (int i = 0; i <= amount; ++i)
//             dp[n-1][i] = (i % coins[n-1] == 0) ? i / coins[n-1] : LIMIT;

//         for (int i = n-2; i >= 0; --i) {
//             for (int a = 0; a <= amount; ++a) {
//                 if (a >= coins[i])
//                     dp[i][a] = min(1 + dp[i][a-coins[i]], dp[i+1][a]);
//                 else dp[i][a] = dp[i+1][a];
//             }
//         }
        
//         return dp[0][amount] < LIMIT ? dp[0][amount] : -1;
//     }

// };



/* top-down approach */
// class Solution {
// public:
//     const int LIMIT = 1000000;
//     int dp[15][10005];

//     int solve(int i, int val, vector<int>& v) {
//         if (i >= v.size()) return (val != 0) ? LIMIT : 0;
//         if (val < 0) return LIMIT;
//         if (val == 0) return 0;	
//         if (dp[i][val] != -1) return dp[i][val];
//         return dp[i][val] = min(1 + solve(i, val-v[i], v), solve(i+1, val, v));
//     }

//     int coinChange(vector<int>& coins, int amount) {
//         memset(dp, -1, sizeof(dp));
//         int ans = solve(0, amount, coins);
//         return ans >= LIMIT ? -1 : ans;  
//     }

// };
