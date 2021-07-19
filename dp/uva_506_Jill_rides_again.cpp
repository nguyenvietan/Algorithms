/* Uva 506 - Jill rides again. [https://onlinejudge.org/external/5/507.pdf]
*  Key: running sum -> seek for the largest gap
*/
#include <iostream>
#include <vector>

using namespace std;

int main() {
    int b, r, x;
    vector<int> v;
    cin >> b;
    for (int c = 1; c <= b; ++c) {
        v.clear();
        cin >> r;
        for (int i = 0; i < r-1; ++i) {
            cin >> x;
            v.push_back(x);                    
        }
        int sum = 0;
        int s_max = -1;
        // based on the standard solution for finding the maximum subarray
        // for (int i = 0; i < v.size(); ++i) {
        //     sum += v[i];
        //     s_max = max(sum, s_max);
        //     if (sum < 0) sum = 0;
        // }
        int be, en, _be, _en;
        be = en = _be = _en = -1;
        for (int i = 0; i < v.size(); ++i) {
            sum += v[i];
            _en = i;
            if (sum < 0) {
                sum = 0;
                _be = i;
                continue;
            }
            if (sum > s_max || ((sum == s_max) && (_en-_be > en-be))) {
                s_max = sum;
                be = _be;
                en = _en;
            }
        }
        if (s_max <= 0) printf("Route %d has no nice parts\n", c); 
        else printf("The nicest part of route %d is between stops %d and %d\n", c, be+2, en+2);
    } 
    return 0;
}
