/* Uva 1047 - Zones. [https://onlinejudge.org/external/10/1047.pdf]
*  Keys: + bit manipulation
*        + inclusion-exclusion principle
*/
#include <iostream>

using namespace std;

int n, na;
int people[25];
int m;
int t, x, p;
int c_towers[15], c_people[15];

int count_bit_1s(int x) {
    int cnt = 0;
    while (x) {
        x -= x & (-x);
        cnt++;
    }
    return cnt;
}

void init() {
    for (int i = 0; i < 15; ++i) {
        c_towers[i] = c_people[i] = 0;
    }
}

int main() {
    int tc = 0;
    while (1) {
        cin >> n >> na;
        if (n == 0 && na == 0) break;
        tc++;
        init();
        for (int i = 0; i < n; ++i) cin >> people[i];
        cin >> m;
        for (int i = 0; i < m; ++i) {
            cin >> t;
            for (int j = 0; j < t; ++j) {
                cin >> x;
                c_towers[i] |= 1 << (x-1); 
            }
            cin >> p;
            c_people[i] = p;
        }
        int max_sum = 0, loc;
        for (int i = 0; i < (1 << n); ++i) { // iterate over all na-element subsets
            if (count_bit_1s(i) != na) continue;
            int sum = 0;
            for (int j = 0; j < n; ++j) {
                if (i & (1 << j)) {
                    sum += people[j];
                }
            }
            for (int r = 0; r < m; ++r) { // iterate over all common areas
                int tmp = i & c_towers[r];
                int intersections = count_bit_1s(tmp);
                if (intersections > 1) sum -= (intersections - 1) * c_people[r]; // inclusion-exclusion principle
            }
            if (sum > max_sum) {
                max_sum = sum;
                loc = i;
            }
        }
        printf("Case Number  %d\n", tc);
        printf("Number of Customers: %d\n", max_sum);
        printf("Locations recommended:");
        for (int j = 0; j < n; ++j) {
            if (loc & (1 << j)) printf(" %d", j+1);
        }
        printf("\n\n");
    }
    return 0;
}

