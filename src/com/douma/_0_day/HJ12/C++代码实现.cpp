#include<iostream>
#include<string>
using namespace std;
int main() {
    string s;
    cin >> s;
    string res = "";
    for (int i=s.length()-1; i>=0; i--) {
        res += s[i];
    }
    cout << res << endl;
    return 0;
}