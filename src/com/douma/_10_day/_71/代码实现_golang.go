func simplifyPath(path string) string {
    var dirs = strings.Split(path, "/")
    var stack = []string{}
    for _, dir := range dirs {
        if dir == "" || dir == "." {
            continue
        } else if dir == ".." && len(stack) == 0 {
            continue
        } else if dir == ".." && len(stack) > 0 {
            stack = stack[:len(stack) - 1]
        } else {
            stack = append(stack, dir)
        }
    }
    if len(stack) == 0 {
        return "/"
    }
    return "/" + strings.Join(stack, "/")
}