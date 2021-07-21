/* Longest Increasing Subsequence
* [https://leetcode.com/problems/longest-increasing-subsequence]
*/

// O(N^2) solution:
// class Solution {
// public:
//     int lengthOfLIS(vector<int>& nums) {
//         int n = nums.size();
//         if (n == 1) return 1;
//         vector<int> v(n, 0);
//         v[0] = 1;
//         int ans = -1;
//         for (int i = 1; i < n; ++i) {
//             v[i] = 1;
//             for (int j = 0; j < i; ++j) {
//                 if (nums[j] < nums[i]) {
//                     v[i] = max(v[i], 1 + v[j]);
//                 }
//             }
//             ans = max(ans, v[i]);
//         }
//         return ans;
//     }
// };

// O(Nlog(k)) solution, where k is the length of the longest increasing subsequence
class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return 1;
        vector<int> v;
        v.push_back(nums[0]);
        vector<int>::iterator it;
        for (int i = 1; i < n; ++i) {
            it = lower_bound(v.begin(), v.end(), nums[i]); // rightmost position such that *it >= nums[i]
            if (it - v.begin() == v.size()) {
                v.push_back(nums[i]);
                continue;
            }
            if (*it == nums[i]) continue; // ignore duplicate number
            *it = nums[i]; // select the smaller number
        }
        return v.size();
    }
};
