/* Median of Two Sorted Arrays. Leetcode [https://leetcode.com/problems/median-of-two-sorted-arrays]
*  Keys: Binary search
*/

class Solution {
public:

    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size(), m = nums2.size();
        if (n > m) return findMedianSortedArrays(nums2, nums1);

        int leftA, rightA, leftB, rightB;
        int l = 0, r = n, mid;
        while (l <= r) { // tricky point: r = n (not n-1)
            mid = (l + r)/2;
            
            leftA = (mid > 0) ? nums1[mid-1] : INT_MIN;
            rightA = (mid < n) ? nums1[mid] : INT_MAX;
            
            int idxB = (n + m)/2 - mid;
            
            rightB = (idxB < m) ? nums2[idxB] : INT_MAX;
            leftB = (idxB > 0) ? nums2[idxB-1] : INT_MIN;

            if (leftA <= rightB && leftB <= rightA) break;
            else if (leftA > rightB) r = mid - 1;
            else l = mid + 1;
        }
        if ((n + m)&1) return min(rightA, rightB);
        return (max(leftA, leftB) + min(rightA, rightB))/2.0;
    }
    
};
