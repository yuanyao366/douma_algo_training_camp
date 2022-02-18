from typing import List


class Solution:
    def restoreIpAddresses(self, s: str) -> List[str]:
        res = []
        if not s: return res

        def is_valid_ip_segment(segment) -> bool:
            # 长度不能大于 3
            if len(segment) > 3: return False

            # 1. ip 段如果是以 0 开始的话，那么这个 ip 段只能是 0
            # 2. ip 段需要小于等于 255
            return len(segment) == 1 if segment[0] == '0' else int(segment) <= 255

        def restore_ip(index, restored, count) -> None:
            if count > 4: return
            if count == 4 and index == len(s):
                res.append(restored)
                return
            for segment_len in range(1, 4):
                if index + segment_len > len(s):
                    break
                segment = s[index:index + segment_len]
                if not is_valid_ip_segment(segment):
                    continue
                suffix = "" if count == 3 else "."
                restore_ip(index + segment_len, restored + segment + suffix, count + 1)

        restore_ip(0, "", 0)
        return res