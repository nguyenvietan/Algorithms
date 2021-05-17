/* Uva 11565 - Simple Equations. [https://onlinejudge.org/external/115/11565.pdf]
    x + y + z = A,
    xyz = B,
    x^2 + y^2 + z^2 = C,
    where 1 <= A, B, C <= 10000
    Assume |x| = min(|x|, |y|, |z|), then |x^3| <= B <= 10000, hence |x| <= 22
*/

#include <iostream>
#include <math.h>

using namespace std;

int main() {
    int n, a, b, c;
    int x, y, z;
    bool found;
    cin >> n;
    while (n--) {
        cin >> a >> b >> c; 
        found = false;
        for (x = -22; !found && x <= 22; ++x) {
            if (x*x <= c) {
                for (y = -100; !found && y <= 100; ++y) {
                    if (y != x && x*x + y*y <= c) {
                        for (z = -100; !found && z <= 100; ++z) {
                            if (z != x && z != y && x*x+y*y+z*z == c && x*y*z == b && x+y+z == a) {
                                found = true;
                                printf("%d %d %d\n", x, y,z);
                            }
                        }
                    }
                }
            }
        }
        if (!found) printf("No solution.\n");
    }
    return 0;
}
