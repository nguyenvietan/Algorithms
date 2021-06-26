/* Uva 10341 - Solve it. [https://onlinejudge.org/external/103/10341.pdf] 
* Keys:  + binary search to reach the root of a function.
*        + fabs(double) vs. abs(int)
*       + WHILE (fabs(hi - low) > EPS) {...} or FOR (int iter = 0; iter < 50; ++iter) {...}
*         to end the loop
*/
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <sstream>
#include <math.h>
#define EPS 1e-9

using namespace std;

string line;
int p, q, r, s, t, u;

double f(double x) {
    return p*exp(-x) + q*sin(x) + r*cos(x) + s*tan(x) + t*x*x + u;
}

int main() {
    while (scanf("%d%d%d%d%d%d", &p, &q, &r, &s, &t, &u) == 6) {
        if (f(0) * f(1) > 0) {
            printf("No solution\n");
            continue;
        }
        double low = 0, hi = 1, x = 0.5;
        /* Approach #1: use EPS */
        while (fabs(hi - low) > EPS) {
            if (f(low) * f(x) <= 0) hi = x;
            else low = x;
            x = (hi + low) / 2.0;
        }

        /* Approach #2: limit no. iterations = 50 (>> log2(10000) ~= 13)
        *  but take longer runtime
        */
        /* 
        for (int i = 0; i < 50; ++i) {
            if (fabs(f(x)) < EPS) break; 
            if (f(low) * f(x) <= 0) hi = x;
            else low = x;
            x = (hi + low) / 2.0; 
        }
        */        
        printf("%.4lf\n", x);
    };
    return 0;
}


