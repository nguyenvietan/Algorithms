/* IOI 2010. Day 1, Task 3: Quality of Living. [https://ioi2010.org/Tasks/Day1/Quality_of_Living.shtml]
*  Submission: [https://dmoj.ca/problem/ioi10p3]
* Keys: + Pre-calculate smaller[][], bigger[][]
*           -> optimize time of finding median of a H by W rectangle to O(1)
*       + Binary search to find answer
*       + memset(): works on byte by byte (only use with 0, -1)
*/

#include <iostream>
#include <cstring>

using namespace std;

int rectangle(int R, int C, int H, int W, int Q[30][30]) {
    int l = 1, r = R * C, mid, ans = R * C;
    int s[R][C], b[R][C];
    // binary search the answer
    while (l <= r) {
        mid = (l + r) / 2;
        memset(s, 0, sizeof(s));
        memset(b, 0, sizeof(b));

        // pre-calculate s[R][C], b[R][C]
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                s[i][j] += Q[i][j] < mid;
                b[i][j] += Q[i][j] > mid;
                if (j > 0) s[i][j] += s[i][j-1], b[i][j] += b[i][j-1];
                if (i > 0) s[i][j] += s[i-1][j], b[i][j] += b[i-1][j];
                if ( i > 0 && j > 0) s[i][j] -= s[i-1][j-1], b[i][j] -= b[i-1][j-1];
            }
        }

        // verify if mid is the smallest median among all H by W rectangulars
        bool is_median_of_a_rect = false;
        bool is_greater_than_a_median = false;
        for (int i = 0; i <= R-H; ++i) {
            for (int j = 0; j <= C-W; ++j) {
                // calculate no. numbers < mid in rectangle (i,j)->(i+H-1,j+W-1)
                int x = s[i+H-1][j+W-1];
                if (j > 0) x -= s[i+H-1][j-1];
                if (i > 0) x -= s[i-1][j+W-1];
                if (i > 0 && j > 0) x += s[i-1][j-1];

                // calculate no. numbers > mid in rectangle (i,j)->(i+H-1,j+W-1)
                int y = b[i+H-1][j+W-1];
                if (j > 0) y -= b[i+H-1][j-1];
                if (i > 0) y -= b[i-1][j+W-1];
                if (i > 0 && j > 0) y += b[i-1][j-1];
                
                if (x == y) is_median_of_a_rect = true;
                if (x > y) is_greater_than_a_median = true;
            }
        }
        if (is_median_of_a_rect) {
            r = mid - 1;
            ans = min(ans, mid);
        }
        else if (is_greater_than_a_median) r = mid - 1;
        else l = mid + 1;
    }
    return ans;
};

/* Testing
input file:
    5 5 3 3
    5 11 12 16 25
    17 18  2  7 10
    4 23 20  3  1
    24 21 19 14  9
    6 22  8 13 15
*/

/*
int main() {
    // please change the function declaration to avoid segmentation fault
    // int rectangle(int R, int C, int H, int W, int Q[30][30]) {...}
    int R, C, H, W;
    int Q[30][30];
    cin >> R >> C >> H >> W;

    for (int i = 0; i < R; ++i) {
        for (int j = 0; j < C; ++j) {
            cin >> Q[i][j];
        }
    }
    cout << rectangle(R, C, H, W, Q) << "\n";
    return 0;
}
*/
