class Solution:
    def simplifyPath(self, path: str) -> str:
        dirs = [d for d in path.split("/") if d != "." and d != ""]
        stack = []
        for d in dirs:
            if d == ".." and not stack:
                continue;
            elif d == ".." and stack:
                stack.pop()
            else:
                stack.append(d)
        return "/" + "/".join(stack)