class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:
        s2t, t2s = {}, {}
        for sc, tc in zip(s, t):
            if s2t.get(sc, tc) != tc or t2s.get(tc, sc) != sc:
                return False
            s2t[sc], t2s[tc] = tc, sc
        return True