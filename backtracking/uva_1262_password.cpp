/* Uva 1262 - Password. [https://onlinejudge.org/external/12/1262.pdf] 
* Keys: + use bakctracking
*       + skip duplicate passwords
        + find common letters of two sequences:
            ABDEPZ
            BCPQXZ
            -> BPZ
        Approach #1: bool letters[26]
        Approach #2: set<char> s1; // contains {A, B, D, E, P, Z}
                     set<char> s2; // contains {B, C, P, Q, X, Z}
                     s1.count('B') ? 
*/
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

vector<char> p1[5];
vector<char> p2[5];
vector<char> cm[5];
bool letters[26];
char pw[5];
int cnt, k;

void init() {
    for (int i = 0; i < 5; ++i) {
        p1[i].clear();
        p2[i].clear();
        cm[i].clear();
    }
    for (int i = 0; i < 26; ++i) {
        letters[i] = false;
    }
    cnt = 0;
}

bool find_kth_pw(int x) {
    if (x == 5) {
        cnt++;
        if (cnt == k) return true;
        return false;
    }
    for (int i = 0; i < (int)cm[x].size(); ++i) {
        if (i > 0 && cm[x][i] == cm[x][i-1]) continue;
        pw[x] = cm[x][i];
        if (find_kth_pw(x + 1)) return true;
    }
    return false;
}

int main() {
    int t, idx;
    string s;
    cin >> t;
    while (t--) {
        init();
        cin >> k;
        for (int i = 0; i < 6; ++i) {
            cin >> s;
            for (int x = 0; x < 5; ++x) {
                p1[x].push_back(s[x]);
            }
        }
        for (int i = 0; i < 6; ++i) {
            cin >> s;
            for (int x = 0; x < 5; ++x) {
                p2[x].push_back(s[x]);
            }
        }
        for (int x = 0; x < 5; ++x) {
            for (int i = 0; i < 26; ++i) letters[i] = false;
            for (int i = 0; i < 6; ++i) {
                idx = (int)p1[x][i] - 'A';
                letters[idx] = true;
            }
            for (int i = 0; i < 6; ++i) {
                idx = (int)p2[x][i] - 'A';
                if (letters[idx]) cm[x].push_back(p2[x][i]);
            }
            sort(cm[x].begin(), cm[x].end());
        }
        int total = 1;
        for (int x = 0; x < 5; ++x) {
            total *= cm[x].size(); 
        }
        if (total < k) printf("NO\n");
        else if (find_kth_pw(0)) printf("%s\n", pw);
        else printf("NO\n");;
    }
    return 0;
}
