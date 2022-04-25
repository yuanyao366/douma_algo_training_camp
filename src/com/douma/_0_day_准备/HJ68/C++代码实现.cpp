#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

// c++ 自定义排序的规则
// 比较 a 和 b：
// 如果想升序，那么就定义当 a < b 的时候返回 true
// 如果想降序，那么就定义当 a > b 的时候返回 true
bool cmp0(const pair<string, int> &a,const pair<string, int> &b) {
    return a.second > b.second;
}

bool cmp1(const pair<string, int> &a,const pair<string, int> &b) {
    return a.second < b.second;
}

int main() {
    int n, flag;
    while(cin >> n >> flag) {
       vector<pair<string,int>> students(n);
       for(int i = 0; i < n; i++) {
           cin >> students[i].first >> students[i].second;
       }
       if(flag == 1) {
           stable_sort(students.begin(), students.end(), cmp1);
       } else {
           stable_sort(students.begin(), students.end(), cmp0);
       }

       for(int i = 0; i < n; i++) {
           cout << students[i].first << " " << students[i].second << endl;
       }
    }
    return 0;
}