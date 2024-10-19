from collections import defaultdict

'''
https://www.lintcode.com/problem/634/
Word Squares

There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
'''


def create_hashmap_of_prefixes_and_words(words_set):
    hashmap = defaultdict(list)
    for word in words_set:
        for i in range(len(word) + 1):
            hashmap[word[:i]].append(word)
    return hashmap


def get_words_length(words_set):
    for word in words_set:
        return len(word)


def find_all_word_squares(words_set):
    nlines = get_words_length(words_set)
    hashmap = create_hashmap_of_prefixes_and_words(words_set)
    word_squares = []
    word_square = []

    def dfs(prefix, line):
        if line == nlines:
            word_squares.append(word_square.copy())
            return True
        words = hashmap[prefix]
        for word in words:
            word_square.append(word)
            new_prefix = ''.join(
                [
                    wrd[line + 1] if line + 1 < nlines else 'x'
                    for wrd in word_square
                ]
            )
            dfs(new_prefix, line + 1)
            word_square.pop()

    dfs('', 0)
    return word_squares


words_set = ["area", "lead", "wall", "lady", "ball"]
output = [
    [
        "wall",
        "area",
        "lead",
        "lady"
    ],
    [
        "ball",
        "area",
        "lead",
        "lady"
    ]
]
assert str(find_all_word_squares(words_set)) == str(output)

words_set = ["abat", "baba", "atan", "atal"]
output = [
    [
        "baba",
        "abat",
        "baba",
        "atan"
    ],
    [
        "baba",
        "abat",
        "baba",
        "atal"
    ]
]
assert str(find_all_word_squares(words_set)) == str(output)
