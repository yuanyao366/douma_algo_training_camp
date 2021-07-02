class Solution {
private:
    vector<string> res;
public:
    vector<string> restoreIpAddresses(string s) {
        if (s.empty()) return res;
        restoreIp(s, 0, "", 0);
        return res;
    }

    void restoreIp(string s, int index, string restored, int count) {
        if (count > 4) return;
        if (count == 4 && index == s.length()) {
            res.push_back(restored);
            return;
        }

        for (int segmentLen = 1; segmentLen < 4; segmentLen++) {
            if (index + segmentLen > s.length()) break;

            string segment = s.substr(index, segmentLen);
            if (!isValidIpSegment(segment)) continue;

            string suffix = count == 3 ? "" : ".";
            restoreIp(s, index + segmentLen, restored + segment + suffix, count + 1);
        }
    }

    bool isValidIpSegment(string segment) {
        // 长度不能大于 3
        int len = segment.length();
        if (len > 3) return false;

        // 1. ip 段如果是以 0 开始的话，那么这个 ip 段只能是 0
        // 2. ip 段需要小于等于 255
        return (segment[0] == '0') ?
                (len == 1) : (atoi(segment.c_str()) <= 255);
    }
};