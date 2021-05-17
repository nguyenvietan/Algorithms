/* Uva 441 - Loto. [https://onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=382]
*/
#include <iostream>
#include <vector>

using namespace std;

int main() {
    int k, x, cnt = 0;
    vector<int> v;
    int a, b, c, d, e, f;

    while (1) {
        cin >> k;
        if (k == 0) break;
        if (cnt > 0) printf("\n");
        v.clear();
        for (int i = 0; i < k; ++i) {
            cin >> x;
            v.push_back(x);
        }
        for (a = 0; a < k-5; ++a) {
            for (b = a + 1; b < k-4; ++b) {
                for (c = b + 1; c < k-3; ++c) {
                    for (d = c + 1; d < k-2; ++d) {
                        for (e = d + 1; e < k-1; ++e) {
                            for (f = e + 1; f < k; ++f) {
                                printf("%d %d %d %d %d %d\n", v[a], v[b], v[c], v[d], v[e], v[f]);
                            }
                        }
                    }
                }
            }
        }
        cnt++; 
    }
    return 0;
}
