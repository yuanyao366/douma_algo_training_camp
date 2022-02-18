class SentenceInfo {
    constructor(content, time) {
        this.content = content
        this.time = time
    }
}

class TrieNode {
    constructor() {
        this.children = new Map()
        this.times = 0
    }
}


/**
 * @param {string[]} sentences
 * @param {number[]} times
 */
class AutocompleteSystem  {

    constructor(sentences, times) {
        this.root = new TrieNode()
        this.currSentence = ""

        for (let i = 0; i < sentences.length; i++) {
            this.insert(sentences[i], times[i])
        }
    }
    

    insert (s, time) {
        let curr = this.root
        for (const c of s) {
            if (!curr.children.has(c)) {
                curr.children.set(c, new TrieNode())
            }
            curr = curr.children.get(c)
        }
        curr.times += time
    }

    lookup (s) {
        const res = []
        let curr = this.root
        for (const c of s) {
            if (!curr.children.has(c)) {
                return res
            }
            curr = curr.children.get(c)
        }
        
        const dfs = (node, s) => {
            if (node.times > 0) {
                res.push(new SentenceInfo(s, node.times))
            }
            
            for (const c of node.children.keys()) {
                dfs(node.children.get(c), s + c)
            }
        }

        dfs(curr, s)
        return res
    }

    /** 
    * @param {character} c
    * @return {string[]}
    */
    input(c) {
        const res = []
        if (c == '#') {
            this.insert(this.currSentence, 1)
            this.currSentence = ""
        } else {
            this.currSentence += c
            const sentences = this.lookup(this.currSentence)
            sentences.sort((s1, s2) => {
                if (s1.time == s2.time) {
                    return s1.content.localeCompare(s2.content)
                } else {
                    return s2.time - s1.time
                }
            })
            for (let i = 0; i < Math.min(3, sentences.length); i++) {
                res.push(sentences[i].content)
            }
        }
        return res
    }
};

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * var obj = new AutocompleteSystem(sentences, times)
 * var param_1 = obj.input(c)
 */