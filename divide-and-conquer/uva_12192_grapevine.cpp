/* Uva 10567 - Password. [https://onlinejudge.org/external/121/12192.pdf] 
* Key: binary search STD:  lower_bound()
*/
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#define MAX 505

using namespace std;

int main() {
    ios_base::sync_with_stdio(0);
	cin.tie(0);

    int n, m, x, q, l, u;
    int mat[MAX][MAX];

    while (1) {
        cin >> n >> m;
        if (n == 0 && m == 0) break;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                cin >> mat[i][j];
            }
        }
        cin >> q;
        while (q--) {
            int ans = 0;
            cin >> l >> u;
            for (int i = 0; i < n; ++i) {
                auto it = lower_bound(mat[i], mat[i] + m, l);
                int r = i + ans, c = it - mat[i] + ans;
                while (r < n && c < m && mat[r][c] <= u) { r++; c++; }
                if (r - i > ans) ans = r - i;
            }
            printf("%d\n", ans);
        }
        printf("-\n");
    }
    return 0;
}
