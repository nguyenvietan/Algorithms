#include <iostream>
#include <string>
#include <vector>
using namespace std;

/*
    3-character string: ABC
        + swap(A,A) -- ABC  --> swap(B,B) -> ABC 
                            |-> swap(B,C) -> ACB 
        + swap(A,B) -- BAC --> swap(A,A) -- BAC
                           |-> swap(A,C) -- BCA
        + swap(A,C) -- CAB --> swap(A,A) -- CAB
                           |-> swap(A,B) -- CBA
    Extend to 4-character string: XABC
        + swap(X,X) -- XABC --> ...
        + swap(X,A) -- AXBC --> ...
        + swap(X,B) -- BAXC --> ...
        + swap(X,C) -- CABX --> ...
*/
void swap(char* c, char* d) {
    char tmp = *c;
    *c = *d;
    *d = tmp;
}
void permutations(string& str, int l, int r) {
    if (l == r) {
        cout << str << "\n";
        return;
    }
    for (int i = l; i <= r; ++i) {
        swap(str[l], str[i]);
        permutations(str, l+1, r);
        swap(str[l], str[i]);
    }
}

int main() {
    string str = "abc";
    permutations(str, 0, str.size()-1);
    return 0;
}
