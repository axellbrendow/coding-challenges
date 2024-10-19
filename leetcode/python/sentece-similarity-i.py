from collections import defaultdict

def build_words_map(pairs):
	words_map = defaultdict(set)
	for word1, word2 in pairs:
		words_map[word1].add(word2)
		words_map[word2].add(word1)
	return words_map

def are_similar(words1, words2, pairs):
	words1 = words1.split(' ')
	words2 = words2.split(' ')
	if len(words1) != len(words2): return False
	words_map = build_words_map(pairs)
	for word1, word2 in zip(words1, words2):
		if word1 != word2 and not word2 in words_map[word1]:
			return False
	return True

words1 = 'great'
words2 = 'great'
pairs = []
assert are_similar(words1, words2, pairs) == True

words1 = 'great'
words2 = 'doubleplus good'
pairs = [['great', 'doubleplus'], ['great', 'good']]
assert are_similar(words1, words2, pairs) == False

words1 = 'great good'
words2 = 'doubleplus good'
pairs = [['great', 'doubleplus']]
assert are_similar(words1, words2, pairs) == True

words1 = 'great acting skills'
words2 = 'fine drama talent'
pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]]
assert are_similar(words1, words2, pairs) == True
