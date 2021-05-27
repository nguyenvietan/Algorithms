/* Uva 10660 - Citizen Attention Offices. [https://onlinejudge.org/external/106/10660.pdf]
   Key: 7 loops + Manhattance distance
    ________________
    |x_|__|3_|__|2_|
    |__|__|__|x_|__|
    |__|x_|1_|__|__|
    |__|__|x_|__|x_|
    |__|2_|__|4_|__|

*/

#include <iostream>
#include <vector>
#include <utility>

using namespace std;

struct Area {
    pair<int, int> p;
    int population;
};

int t, n, x, y, a;
int res[5];
int sum, min_sum;
struct Area area;
vector<Area> v;

void init() {
    v.clear();
    res[0] = res[1] = res[2] = res[3] = res[4] = 0;
}

int cal_min_dist(int x, int y, int o1, int o2, int o3, int o4, int o5) {
    int a = min(abs(o1/5-x) + abs(o1%5-y), abs(o2/5-x) + abs(o2%5-y));
    a = min(a, abs(o3/5-x) + abs(o3%5-y));
    a = min(a, abs(o4/5-x) + abs(o4%5-y));
    a = min(a, abs(o5/5-x) + abs(o5%5-y));
    return a;
}

int main() {
    cin >> t;
    while (t--) {
        cin >> n;
        init();
        for (int i = 0; i < n; ++i) {
            cin >> x >> y >> a;
            area = Area{make_pair(x, y), a}; 
            v.push_back(area);
        }

        min_sum = INT32_MAX;
        for (int o1 = 0; o1 < 21; ++o1) {
            for (int o2 = o1+1; o2 < 22; ++o2) {
                for (int o3 = o2+1; o3 < 23; ++o3) {
                    for (int o4 = o3+1; o4 < 24; ++o4) {
                        for (int o5 = o4+1; o5 < 25; ++o5) {
                            sum = 0;
                            for (int i = 0, L = v.size(); i < L; ++i) {
                                area = v[i];
                                sum += area.population * cal_min_dist(area.p.first, area.p.second, o1, o2, o3, o4, o5);
                            }
                            if (sum < min_sum) {
                                min_sum = sum;
                                res[0] = o1; res[1] = o2; res[2] = o3; res[3] = o4; res[4] = o5;
                            }
                        }
                    }
                }
            }
        }
        printf("%d %d %d %d %d\n", res[0], res[1], res[2], res[3], res[4]);
    }    
    return 0;
}
