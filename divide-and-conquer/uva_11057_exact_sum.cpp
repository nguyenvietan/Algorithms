/* Uva 10567 - Password. [https://onlinejudge.org/external/110/11057.pdf] 
* Key: unordered_map
*/
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <climits>
#include <unordered_map>

using namespace std;

int main() {
    unordered_map<int, int> m;
    vector<int> v;
    int n, x, s;
    int p1, p2;

    while (scanf("%d", &n) != EOF) {
        v.clear();
        m.clear();
        for (int i = 0; i < n; ++i) {
            scanf("%d", &x);
            v.push_back(x);
        }
        scanf("%d", &s);
        for (int i = 0; i < n; ++i) {
            if (m.find(v[i]) == m.end()) {
                m[v[i]] = 1;
            } else {
                m[v[i]]++;
            }
        }

        int gap = INT_MAX;
        for (int i = 0; i < n; ++i) {
            int z = s - v[i];
            if (z == v[i]) {
                if (m[z] > 1) {
                    p1 = p2 = z;
                    break;
                }
                continue;
            }
            if (m.find(z) != m.end()) {
                if (abs(z - v[i]) < gap) {
                    gap = abs(z - v[i]);
                    p1 = v[i];
                    p2 = z;
                }
            }
        }
        printf("Peter should buy books whose prices are %d and %d.\n\n", p1, p2);
    }
    return 0;
}