var removeVowels = function(s) {
    return s.split("").filter(c => !isVowel(c)).join("")
};

var isVowel = function(c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
}