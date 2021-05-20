/* Uva 11742 - Social constraints. [https://onlinejudge.org/external/117/11742.pdf]
* key: generate all the permutations of an array.
*/
#include <iostream>
#include <algorithm>

using namespace std;

int constr[25][3];
int p[8];
int cnt;

void init() {
    cnt = 0;
    for (int i = 0; i < 8; ++i) p[i] = i;
    for (int i = 0; i < 25; ++i) {
        constr[i][0] = constr[i][1] = constr[i][2] = 0;
    }
}

bool is_valid(int n, int m) {
    int a, b, c, idx1, idx2;
    for (int i = 0; i < m; ++i) {
        a = constr[i][0];
        b = constr[i][1];
        c = constr[i][2];
        for (int j = 0; j < n; ++j) {
            if (p[j] == a) idx1 = j;
            if (p[j] == b) idx2 = j;
        }
        if (c > 0) {
            if (abs(idx1 - idx2) > abs(c)) return false; 
        }
        if (c < 0) {
            if (abs(idx1 - idx2) < abs(c)) return false;
        }
    }
    return true;
}

// void perm(int l, int r, int n, int m) {
//     if (l == r) {
//         if (is_valid(n, m)) cnt++;
//         return;
//     }
//     for (int i = l; i <= r; ++i) {
//         swap(p[l], p[i]);
//         perm(l+1, r, n, m);
//         swap(p[l], p[i]);
//     }
// }

int main() {
    int n, m;
    while (1) {
        cin >> n >> m;
        if (n == 0 && m == 0) break;
        init();
        for (int i = 0; i < m; ++i) {
            cin >> constr[i][0] >> constr[i][1] >> constr[i][2];
        }
        // perm(0, n-1, n, m);
        do {
            if (is_valid(n, m)) cnt++;
        } while(next_permutation(p, p+n));
        cout << cnt << "\n";
    }
    return 0;
}
