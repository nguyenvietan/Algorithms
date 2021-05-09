#include<iostream>
#include<vector>

using namespace std;

/*
	Idea:
		[1, 5, 3, 2, 8, 7, 6, 4]
		pivot = end
		[1, 3, 2] [4*] [5, 8, 7, 6]
		[1][2*][3] [4] [5] [6*] [8, 7*]
		[1][2] [3] [4] [5] [6]  [7][8]
	
	Time Complexity: O(Nlog(N)) on average. worst case: O(N^2), for example: sorting [1, 2, 3, 4, 5].
	Space Complexity: O(N).
*/

void print_vect(vector<int>& v) {
	for (int i = 0; i < (int)v.size(); ++i) {
		cout << v[i] << " ";
	}
	cout << "\n";
}
int partition(vector<int>& a, int lo, int hi) {
	int pivot = hi;
	int i = 0;
	for (int j = 0; j <= hi; ++j) {
		if (a[j] < a[pivot]) {
			// swap(a[i], a[j])
			int tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;
			++i;
		}
	}
	// move a[pivot] to the midle of the left and right partitions
	int tmp = a[i];
	a[i] = a[pivot];
	a[pivot] = tmp;
	return i;
}

void quicksort(vector<int>& a, int lo, int hi) {
	if (lo >= hi) return;
	int pivot = partition(a, lo, hi);
	quicksort(a, lo, pivot-1);
	quicksort(a, pivot+1, hi);
}

int main() {
	vector<int> v{6, 5, 4, 9, 10, 3, 2, 1, 0, -6};
	quicksort(v, 0, v.size()-1);
	print_vect(v);
	return 0;
}
