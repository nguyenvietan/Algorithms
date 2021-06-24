/* Uva 10567 - Password. [https://onlinejudge.org/external/105/10567.pdf] 
* Key: binary search (use STL lower_bound(), upper_bound())
*/
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#define MAX 130

using namespace std;

int main() {
    string s, q;
    vector<int> v[MAX];
    int t;
    cin >> s;
    cin >> t;
    for (int i = 0, L = s.size(); i < L; ++i) {
        v[(int)s[i]].push_back(i);
    }
    while (t--) {
        cin >> q;
        int cur = -1;
        bool ok, matched = true;
        int be = -1;
        for (int i = 0; i < q.size(); ++i) {
            int x = q[i];

            /* find the first element > cur */

            // Approach #1: binary search (manually)
            // ok = false;
            // int l = 0, r = v[x].size()-1, m;
            // while (l <= r) {
            //     if (l == r) {
            //         if (v[x][l] > cur) {
            //             cur = v[x][l];
            //             ok = true;
            //         }
            //         break;
            //     } 
            //     m = (l + r) / 2;
            //     if (v[x][m] > cur) r = m; else l = m + 1;
            // }
            // if (!ok) { matched = false; break; }

            // Approach #2: using STL upper_bound()
            auto it = upper_bound(v[x].begin(), v[x].end(), cur);
            if (it == v[x].end()) { matched = false; break; }
            else cur = *it;            
            if (i == 0) be = cur;
        }
        if (matched) printf("Matched %d %d\n", be, cur);
        else printf("Not matched\n");     
    }
    return 0;
}
