/* Uva 725 - Division. [https://onlinejudge.org/external/7/725.pdf]
 */
#include <iostream>
using namespace std;

int main() {
    int n, cnt = 0;;
    while (1) {
        cin >> n;
        if (n == 0) break;
        if (cnt > 0) printf("\n");
        int x, y, tmp, used;
        bool found = false;
        for (x = 1234; x * n <= 98765; x++) {
            y = x * n;
            used = 0;
            used |= (x < 10000);
            tmp = x;
            while (tmp) {
                used |= (1 << tmp%10);
                tmp /= 10;
            }
            tmp = y;
            while(tmp) {
                used |= (1 << tmp%10);
                tmp /= 10;
            }
            if (used == (1 << 10) - 1) {
                found = true;
                printf("%05d / %05d = %d\n", y, x, n);
            }
        }
        if (!found) printf("There are no solutions for %d.\n", n);
        cnt++;
    }
    return 0;
}
