/* UVa 416 - LED Test. [https://onlinejudge.org/external/4/416.pdf].
*/
#include <iostream>
#include <utility>
#include <vector>
#include <string>

using namespace std;

const string n9 = "YYYYNYY";
const string n8 = "YYYYYYY";
const string n7 = "YYYNNNN";
const string n6 = "YNYYYYY";
const string n5 = "YNYYNYY";
const string n4 = "NYYNNYY";
const string n3 = "YYYYNNY";
const string n2 = "YYNYYNY";
const string n1 = "NYYNNNN";
const string n0 = "YYYYYYN";

vector<string> v;
vector<string> v_num{n0, n1, n2, n3, n4, n5, n6, n7, n8, n9};
vector<bool> v_burned;

void init() {
    v.clear();
    v_burned.resize(7, false);
}

bool is_possible(const string& num_standard, const string& num_check) {
    for (int i = 0; i < 7; ++i) {
        if ((num_standard[i] == 'N' && num_check[i] == 'Y') ||
            (v_burned[i] && num_check[i] == 'Y'))
            return false;
        if (num_standard[i] == 'Y' && num_check[i] == 'N')
            v_burned[i] = true;
    }
    return true;
}

int main() {
    int n;
    string line;
    while (1) {
        cin >> n;
        if (n == 0) break;
        init();
        cin.ignore();
        for (int i = 0; i < n; ++i) {
            getline(cin, line);
            v.push_back(line);
        }
        bool ok = false;
        int L = v.size();
        for (int i = 9; i >= L-1; --i) {
            for (int x = 0; x < 7; ++x) {
                v_burned[x] = false;
            }
            for (int k = 0; k < L; ++k) {
                if (!is_possible(v_num[i-k], v[k])) break;
                if (k == L-1) {
                    ok = true;
                }
            }
            if (ok) break;
        }
        if (ok) printf("MATCH\n");
        else printf("MISMATCH\n");
    }
    return 0;   
}

// #include <stdio.h>
// char g[10][8] = {
// "YYYYYYN",//0
// "NYYNNNN",//1
// "YYNYYNY",//2
// "YYYYNNY",//3
// "NYYNNYY",//4
// "YNYYNYY",//5
// "YNYYYYY",//6
// "YYYNNNN",//7
// "YYYYYYY",//8
// "YYYYNYY",//9
// };
// int main() {
//     int n, i, j, k;
//     char a[20][8];
//     while(scanf("%d", &n) == 1 && n) {
//         for(i = 0; i < n; i++)
//             scanf("%s", &a[i]);
//         int flag = 0;
//         for(i = 9; i >= n-1; i--) {
//             int bad[10] = {};
//             for(j = 0; j < n; j++) {
//                 for(k = 0; k < 7; k++) {
//                     if(bad[k] && a[j][k] == 'Y')
//                         break;
//                     if(a[j][k] == 'N' && g[i-j][k] == 'Y')
//                         bad[k] = 1;
//                     else if(a[j][k] == 'Y' && g[i-j][k] == 'N')
//                         break;
//                 }
//                 if(k != 7)  break;
//             }
//             if(j == n) {
//                 flag = 1;
//                 break;
//             }
//         }
//         if(!flag)
//             printf("MIS");
//         puts("MATCH");
//     }
//     return 0;
// }