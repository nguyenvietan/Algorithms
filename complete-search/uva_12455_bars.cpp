/* Uva 12455 - Bars. [https://onlinejudge.org/external/124/12455.pdf]
* Key:  + generate all subsets of a set
        + bit manipulations
*/
#include<iostream>
#include<vector>

using namespace std;

int main() {
    int t, n, p, x;
    vector<int> v;
    cin >> t;
    while (t--) {
        cin >> n >> p;
        v.clear();
        for (int i = 0; i < p; ++i) {
            cin >> x;
            v.push_back(x);
        }
        bool found = false;
        for (int i = 0; i < (1 << p); ++i) {
            int sum = 0;
            for (int k = 0; k < p; ++k) {
                if (i & (1 << k)) sum += v[k];
            }
            if (sum == n) { found = true; break; }
        }
        if (found) cout << "YES\n"; else cout << "NO\n";            
    }
    return 0;
}
