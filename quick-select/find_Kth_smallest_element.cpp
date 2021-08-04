#include<iostream>
#include<vector>

using namespace std;

int partition(vector<int>& v, int lo, int hi) {
    int pivot = v[hi], i = lo, idx;
    while (i <= hi) {
        if (v[i] == pivot) i++;
        else if (v[i] < pivot) swap(v[i++], v[lo++]);
        else swap(v[i], v[hi--]);
    }
    return lo;
}

/* Simplified analysis of Time complexity:
	Assume the partition function divides the array into two halves at each round.
	Each round takes O(L) = cL, where L is the length of the one half, c is constant.
	=> T = c*n + c*n/2 + ... + c*1 < 2cn (easy proof)
	=> T = O(n)
	Similarly, the space complexity is O(n).
	Note: if the random pivot strategy is used, the algorithm is rarely suffer from the worst case,
	which takes up to O(N^2).
*/
int find_Kth_smallest_element(vector<int>&a, int lo, int hi, int k) {
	if (lo >= hi) return a[lo];
	int i = partition(a, lo, hi);
	if (i+1 == k) return a[i];
	if (i+1 > k) return find_Kth_smallest_element(a, lo, i-1, k);
	return find_Kth_smallest_element(a, i+1, hi, k);
}

int main() {
	int k = 4, x;
	vector<int> v{6, 5, 4, 9, 10, 3, 2, 1, 0, -6};
	x = find_Kth_smallest_element(v, 0, v.size()-1, k);
	printf("The %dth smallest element: %d\n", k, x);
	return 0;
}
