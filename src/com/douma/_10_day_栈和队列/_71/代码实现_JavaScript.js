/**
 * @param {string} path
 * @return {string}
 */
var simplifyPath = function(path) {
    const dirs = path.split("/")
    const stack = []
    for (const dir of dirs) {
        if (dir == "" || dir == ".") continue
        else if (dir == ".." && stack.length == 0) continue
        else if (dir == ".." && stack.length > 0) stack.pop()
        else stack.push(dir)
    }
    if (stack.length == 0) return "/"
    return "/" + stack.join("/")
};