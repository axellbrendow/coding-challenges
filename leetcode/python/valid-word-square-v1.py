def build_matrix(max_len, words):
	matrix = [[-1] * max_len for _ in range(max_len)]
	for i in range(len(words)):
		word = words[i]
		for j in range(len(word)):
			matrix[i][j] = word[j]
	return matrix

def check_row_and_column(i, matrix, max_len):
	for j in range(max_len):
		if not matrix[i][j] == matrix[j][i]:
			return False
	return True

def is_word_square(words):
	max_len = max([len(word) for word in words])
	matrix = build_matrix(max_len, words)
	for i in range(max_len):
		if not check_row_and_column(i, matrix, max_len):
			return False
	return True

words = [
  "abcd",
  "bnrt",
  "crmy",
  "dtye"
]
assert is_word_square(words) == True

words = [
  "abcd",
  "bnrt",
  "crm",
  "dt"
]
assert is_word_square(words) == True

words = [
  "ball",
  "area",
  "read",
  "lady"
]
assert is_word_square(words) == False
