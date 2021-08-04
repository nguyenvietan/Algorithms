/* Wiggle Sort II [https://leetcode.com/problems/wiggle-sort-ii].
* Keys: Quick select: find the median and sort the array such that
*       number that are smaller than the median are on the left,
*       and the larger ones are on the right.
*       --> average time complexity = O(n)
*/

#include <iostream>
#include <vector>

using namespace std;

/* pick v[hi] as a pivot, sort v: the smaller on the left, the larger on the right
*  return the index of the leftmost element that's equal to v[hi]
*/
int partition(vector<int>& v, int lo, int hi) {
    int pivot = v[hi], i = lo, idx;
    while (i <= hi) {
        if (v[i] == pivot) i++;
        else if (v[i] < pivot) swap(v[i++], v[lo++]);
        else swap(v[i], v[hi--]);
    }
    return lo;
}

// average time complexity: O(n)
int find_k_th_smallest_elem(vector<int>& v, int lo, int hi, int k) {
    int i = partition(v, lo, hi);
    if (i == k-1) return v[i];
    if (i > k-1) return find_k_th_smallest_elem(v, lo, i-1, k);
    return find_k_th_smallest_elem(v, i+1, hi, k);
}

int main() {
    vector<int> nums {1, 3, 5, 9, 2, 5, 5, 5, 4, 8};
    vector<int> v(nums.begin(), nums.end());
    int n = v.size();
    int median = find_k_th_smallest_elem(v, 0, n-1, n/2);
    cout << "median = " << median << "\n";
    int j = n-1;
    for (int i = 1; i < n; i+=2) nums[i] = v[j--]; // corner case: [4 5 5 6]
    for (int i = 0; i < n; i+=2) nums[i] = v[j--];
    for (int i = 0; i < n; ++i) cout << nums[i] << " ";
    cout << "\n";
    return 0;
}
