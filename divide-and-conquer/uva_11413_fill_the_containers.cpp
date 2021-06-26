/* Uva 10341 - Fill the containers. [https://onlinejudge.org/external/114/11413.pdf] 
*  Keys: binary search, find the range of [min, max]
*       mid = (min + max) / 2 -> validate -> then adjust by repetitions
*/
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <sstream>
#include <math.h>

#define MAX 1005

using namespace std;

bool isValid(int *v, int cap_max, int n_vessels, int n_containers) {
    int cnt = 0, sum = 0;
    for (int i = 0; i < n_vessels; ++i) {
        sum += v[i];
        if (sum > cap_max) {
            cnt++;
            sum = v[i];
        }
    }
    cnt++; // don't forget the last element
    return cnt <= n_containers;
}

int main() {
    int n_vessels, n_contanters;
    int v[MAX];
    while (scanf("%d%d", &n_vessels, &n_contanters) != EOF) {
        for (int i = 0; i < n_vessels; ++i) {
            scanf("%d", &v[i]);
        }
        int sum = 0, v_max = 0;
        for (int i = 0; i < n_vessels; ++i) {
            sum += v[i];
            v_max = max(v_max, v[i]);
        }
        int hi = sum, lo = max(v_max, sum/n_contanters);
        int x, ans;
        while (lo <= hi) {
            x = (hi + lo) / 2;
            if (isValid(v, x, n_vessels, n_contanters)) {
                hi = x - 1;
                ans = x;
            } else {
                lo = x + 1;
            }
        }
        printf("%d\n", ans);
    }
    return 0;
}
