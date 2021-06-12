/* Uva-10503: The dominoes solitaire. [https://onlinejudge.org/external/105/10503.pdf]
* Key: backtracking
* Example:
*   3 : n
*   4 : m
*    0 1 : first piece
*    3 4 : last piece
*    2 1
*    5 6
*    2 2
*    3 2
*    Analyze:
*        0   1   2   3   4
*            be      en
*        n = 3, m = 4
*        ans = "YES": (1,2), (2,2), (2,3)
*/

#include <iostream>
#include <utility>
#include <vector>
#include <string.h>

using namespace std;

int n, m, be, en;
bool vs[15];
vector<pair<int, int>> v;

bool backtrack(int cur, int nxt) {
    if (cur == n + 1) {
        if (nxt == en) return true;
        else return false;
    }
    for (int i = 0; i < (int)v.size(); ++i) {
        if (!vs[i] && v[i].first == nxt) {
            vs[i] = true;
            bool x = backtrack(cur + 1, v[i].second);
            if (x) return true;
            vs[i] = false;
        }
        if (!vs[i] && v[i].second == nxt) {
            vs[i] = true;
            bool x = backtrack(cur + 1, v[i].first);
            if (x) return true;
            vs[i] = false;
        }
    }
    return false;
}

void init() {
    for (int i = 0; i < 15; ++i) { vs[i] = false; }
    v.clear();
}

int main() {
    while (1) {
        cin >> n;
        if (n == 0) break;
        init();
        cin >> m;
        int l, r;
        cin >> l >> r;
        be = r;
        cin >> l >> r;
        en = l;
        while (m--) {
            cin >> l >> r;
            v.push_back(make_pair(l, r));
        }
        if (backtrack(1, be)) printf("YES\n");
        else printf("NO\n");
    }
    return 0;
}
