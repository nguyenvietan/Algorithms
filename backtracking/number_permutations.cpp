#include <iostream>
#include <string>
#include <vector>
using namespace std;

/*
    Based on the string permutations problem:
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

void permutations(int n) {

}

int main() {
    return 0;
}