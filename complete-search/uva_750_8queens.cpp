/* Uva 759 - Eight Queens. [https://onlinejudge.org/external/7/750.pdf]
* Key: Backtracking.
*/
#include<iostream>
#include<vector>

using namespace std;

bool r[8], c[8], d1[15], d2[15];
vector<int> v;
int cnt;

void init() {
    v.clear();
    cnt = 0;
    for (int i = 0; i < 8; ++i) { r[i] = c[i] = false; }
    for (int i = 0; i < 15; ++i) { d1[i] = d2[i] = false; }
}

void mark(int i, int j, bool lock) {
    r[i] = c[j] = lock;
    d1[i+j] = d2[i+7-j] = lock;   
}

void printVect() {
    printf("%2d      ", cnt);
    for (int i = 0, L = v.size(); i < L; ++i) {
        printf("%d", v[i] + 1);
        if (i < L-1) printf(" ");
    }
    printf("\n");
}

void setQeens(int col, int preset_row, int preset_col) {
    if (col == 8) {
        cnt++;
        printVect();
        return;
    }
    if (col == preset_col) {
        v.push_back(preset_row);
        setQeens(col + 1, preset_row, preset_col);
        v.pop_back();
    }
    for (int i = 0; i < 8; ++i) {
        if (!r[i] && !c[col] && !d1[i+col] && !d2[i+7-col]) {
            mark(i, col, true);
            v.push_back(i);
            setQeens(col + 1, preset_row, preset_col);            
            mark(i, col, false);
            v.pop_back();
        }        
    }
}

int main() {
    int t, x, y;
    cin >> t;
    while (t--) {
        init();
        cin >> x >> y;
        x--; y--;
        mark(x, y, true);
        printf("SOLN       COLUMN\n");
        printf(" #      1 2 3 4 5 6 7 8\n\n");
        setQeens(0, x, y);
        if (t) printf("\n");
    }
    return 0;
}
