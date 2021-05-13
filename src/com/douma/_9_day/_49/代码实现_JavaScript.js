/**
 * @param {string[]} strs
 * @return {string[][]}
 */
var groupAnagrams = function(strs) {
    const map = new Map()
    for (let str of strs) {
        const count = new Array(26).fill(0)
        for (const c of str) count[c.charCodeAt() - 97]++
        const key = count.toString()
        let list = map.get(key) ? map.get(key) : new Array()
        list.push(str)
        map.set(key, list)
    }
    return Array.from(map.values())
};

var groupAnagrams1 = function(strs) {
    const map = new Map()
    for (let str of strs) {
        let array = Array.from(str)
        array.sort()
        const key = array.toString()
        let list = map.get(key) ? map.get(key) : new Array()
        list.push(str)
        map.set(key, list)
    }
    return Array.from(map.values())
};