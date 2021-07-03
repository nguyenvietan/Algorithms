/* Uva 183 - Bit maps. [https://onlinejudge.org/external/1/183.pdf] 
* Keys: + pre-calculate sum of all cells -> O(n^2) -> O(1)
*       + indices of top-left/right, bottom-left/right quarters of a matrix
*
*            j1      jm    j2
*            _________^_____
*        i1  |_x|__|__|_x|__|      [x] -> [*] quarters
*        im _|__|__|_*|__|_*|
*            |_x|__|__|_x|__|
*        i2  |__|__|_*|__|_*|
*/
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#define MAX 205

using namespace std;

char type;
int rows, cols, idx;
string in, out;
char mat[MAX][MAX];
int s[MAX][MAX];

void pre_cal_sum() {
    for (int i = 0; i < rows; ++i) {
        for (int j = 0; j < cols; ++j) {
            s[i][j] += mat[i][j] == '1';
            if (i > 0) s[i][j] += s[i-1][j];
            if (j > 0) s[i][j] += s[i][j-1];
            if (i > 0 && j > 0) s[i][j] -= s[i-1][j-1];
        }
    }
}

char char_on_rect(int i1, int j1, int i2, int j2) {
    int n = abs((i2 - i1 + 1) * (j2 - j1 + 1));
    int cnt_1s = 0;
    cnt_1s += s[i2][j2];
    if (j1 > 0) cnt_1s -= s[i2][j1-1];
    if (i1 > 0) cnt_1s -= s[i1-1][j2];
    if (i1 > 0 && j1 > 0) cnt_1s += s[i1-1][j1-1];
    if (cnt_1s == n) return '1';
    if (cnt_1s == 0) return '0'; 
    return 'D';
}

void bin2Dec(int i1, int j1, int i2, int j2) {
    char c = char_on_rect(i1, j1, i2, j2);
    out += c;
    if (c == 'D') {
        int i_m = (i1 + i2) / 2;
        int j_m = (j1 + j2) / 2;
        bin2Dec(i1, j1, i_m, j_m);
        if (j_m < j2) bin2Dec(i1, j_m + 1, i_m, j2);
        if (i_m < i2) bin2Dec(i_m + 1, j1, i2, j_m);
        if (i_m < i2 && j_m < j2) bin2Dec(i_m + 1, j_m + 1, i2, j2);
    }
}

// output should be written on a matrix at first,
// then be printed out from left->right, top->bottom
void dec2Bin(int i1, int j1, int i2, int j2) {
    if (idx >= in.size()) return;
    if (in[idx] == 'D') {
        idx++;                
        int i_m = (i1 + i2) / 2;
        int j_m = (j1 + j2) / 2;
        dec2Bin(i1, j1, i_m, j_m);
        if (j_m < j2) dec2Bin(i1, j_m + 1, i_m, j2);
        if (i_m < i2) dec2Bin(i_m + 1, j1, i2, j_m);
        if (i_m < i2 && j_m < j2) dec2Bin(i_m + 1, j_m + 1, i2, j2);
    } else {
        for (int i = i1; i <= i2; ++i) {
            for (int j = j1; j <= j2; ++j) {
                mat[i][j] = in[idx];                
            }
        }
        idx++;
    }
}

void init() {
    idx = 0;
    in = out = "";
    for (int i = 0; i < rows; ++i) {
        for (int j = 0; j < cols; ++j) {
            s[i][j] = 0;
        }
    }
}

int main() {
    while (1) {
        cin >> type;
        if (type == '#') break;
        init();
        cin >> rows >> cols;
        cin.ignore();
        if (type == 'B') {
            string line;
            while(in.size() < rows * cols) {
                getline(cin, line);
                in += line;
            }
            for (int x = 0; x < (int)in.size(); ++x) {
                int i = x / cols;
                int j = x % cols;
                mat[i][j] = in[x];

            }
            pre_cal_sum();
            bin2Dec(0, 0, rows-1, cols-1);
            printf("D%4d%4d\n", rows, cols);
            for (int i = 0; i < (int)out.size(); ++i) {
                if (i % 50 == 0 && i > 0) printf("\n");
                printf("%c", out[i]);
            }
            printf("\n");
            continue;
        }
        if (type == 'D') {
            getline(cin, in);
            dec2Bin(0, 0, rows-1, cols-1);
            printf("B%4d%4d\n", rows, cols);
            int cnt = 0;
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    if (cnt > 0 && cnt % 50 == 0) printf("\n");
                    printf("%c", mat[i][j]);
                    cnt++;
                }
            }
            printf("\n");
        }
    }
    return 0;
}
