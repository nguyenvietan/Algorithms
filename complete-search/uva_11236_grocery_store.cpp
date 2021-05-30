/* Uva 11236 - Grocery store. [https://onlinejudge.org/external/112/11236.pdf]
*  Keys: Complete search + Pruning
*/

#include<iostream>
using namespace std;

int main() {
    const int MAX = 2000;
    for (int a = 0; a < 212; ++a) {
        for (int b = a; b <= MAX; ++b) {
            if (a + b <= MAX) {
                for (int c = b; c <= MAX; ++c) {
                    if (a + b + c <= MAX && a * b * c > 1000000) {
                        if ((1000000 * (a + b + c)) % (a * b * c - 1000000) == 0) {
                            int d = 1000000 * (a + b + c) / (a * b * c - 1000000);
                            if (d > 0 && d >= c && a + b + c + d <= MAX) {
                                printf("%.2f %.2f %.2f %.2f\n",\
                                        double(a/100.0), double(b/100.0), double(c/100.0), double(d/100.0));
                            }
                        }                      
                    }
                }                
            }
        }        
    }
    return 0;
}
