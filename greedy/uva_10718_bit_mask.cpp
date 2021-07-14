/* Uva 10718 - Bit mask. [https://onlinejudge.org/external/107/10718.pdf]
*  Keys: + bit manipulation
*        + long long: 1L
*/
#include <iostream>

using namespace std;

int main() {
    long long n, l, u;
    while (scanf("%lld%lld%lld", &n, &l, &u) == 3) {
        long long hi, tmp = u, idx = 0, m = 0;
        while (tmp) {
            if (tmp & 1) hi = idx;
            tmp >>= 1;
            idx++;
        }
        bool gt_l = false, lt_u = false;
        for (int i = hi; i >= 0; --i) {
            if (n & (1L << i)) {
                if (gt_l) m &= ~(1L << i);
                else {
                    if (l & (1L << i)) m |= (1L << i);
                };                
            } else {
                if (lt_u) m |= (1L << i);
                else {
                    if (u & (1L << i)) m |= (1L << i);
                }
            }
            if ((m & (1L << i)) && !(l & (1L << i))) gt_l = true;
            if (!(m & (1L << i)) && (u & (1L << i))) lt_u = true;
        }
        printf("%lld\n", m);
    }
    return 0;
}
