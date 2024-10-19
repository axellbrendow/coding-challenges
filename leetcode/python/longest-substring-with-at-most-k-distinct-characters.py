"""
We are given a string S and a number K, and we have to find the longest substring with at most K distinct characters.

S = "abcashdguqw"
K = 3
     *   *
S = "abcashdguqw"
result = "abca"
window = "abca"
while i have k distinct characters (or k keys)
window = "bca"
2 distinct characters
window = "ca"
window = "cas"
"""


from collections import defaultdict


"""
s = "abcashd", k = 3
char_count = {
    a: 1,
    c: 1,
    s: 1,
}
"""


def length_of_longest_substring_k_distinct(k: int, s: str) -> int:
    if k <= 0:
        return 0
    char_count = defaultdict(int)
    length_of_longest = 0
    left, right = 0, -1

    for letter in s:
        if letter not in char_count:
            while len(char_count) == k:
                char_count[s[left]] -= 1
                if char_count[s[left]] == 0:
                    del char_count[s[left]]
                left += 1
        right += 1
        char_count[letter] += 1
        length_of_longest = max(length_of_longest, right - left + 1)

    return length_of_longest


assert length_of_longest_substring_k_distinct(0, "") == 0
assert length_of_longest_substring_k_distinct(1, "") == 0
assert length_of_longest_substring_k_distinct(1, "a") == 1
assert length_of_longest_substring_k_distinct(2, "ab") == 2
assert length_of_longest_substring_k_distinct(2, "aab") == 3
assert length_of_longest_substring_k_distinct(2, "abcb") == 3
assert length_of_longest_substring_k_distinct(2, "abbcab") == 3
assert length_of_longest_substring_k_distinct(3, "abbcab") == 6
assert length_of_longest_substring_k_distinct(3, "aabcbcdbca") == 7
