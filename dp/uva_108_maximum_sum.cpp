/* Uva 108 - Maximum sum. [https://onlinejudge.org/external/1/108.pdf]
*  Keys: + pre-calculate sums of rectangulars on the matrix
*        + Kadane's algorithm: O(n^4) -> O(n^3)
*/

// O(n^3) solution, apply Kadane's algorithm
#include <iostream>
#include <climits>

using namespace std;

int n, a[105][105];

int main() {
    cin >> n;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            scanf("%d", &a[i][j]);
            if (j > 0) a[i][j] += a[i][j-1]; // running sum only on rows
        }
    }
    // calculate max sum
    int s_max = -INT_MAX;
    for (int c1 = 0; c1 < n; ++c1) {
        for (int c2 = c1; c2 < n; ++c2) {
            int sum_rect = 0;
            for (int r = 0; r < n; ++r) {
                if (c1 > 0) sum_rect += a[r][c2] - a[r][c1-1];
                else sum_rect += a[r][c2];
                if (sum_rect < 0) sum_rect = 0; // Kadane's algorithm on rows
                s_max = max(sum_rect, s_max);
            }
        }
    }
    printf("%d\n", s_max);
    return 0;
}

// O(n^4) solution
/*
#include <iostream>
#include <climits>

using namespace std;

int n, a[105][105];

int main() {
    cin >> n;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            scanf("%d", &a[i][j]);
            if (i > 0) a[i][j] += a[i-1][j];
            if (j > 0) a[i][j] += a[i][j-1];
            if (i > 0 && j > 0) a[i][j] -= a[i-1][j-1];
        }
    }
    // calculate max sum
    int s_max = -INT_MAX;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            for (int k = i; k < n; ++k) {
                for (int l = j; l < n; ++l) {
                    int sum = a[k][l];
                    if (j > 0) sum -= a[k][j-1];
                    if (i > 0) sum -= a[i-1][l];
                    if (i > 0 && j > 0) sum += a[i-1][j-1];
                    s_max = max(s_max, sum);
                }
            }            
        }
    }
    printf("%d\n", s_max);
    return 0;
}
*/
