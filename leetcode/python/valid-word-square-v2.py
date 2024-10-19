def check_row_and_column(i, words, max_len):
	for j in range(max_len):
		col_word = words[i][j] if len(words[i]) > j else False
		line_word = words[j][i] if len(words[j]) > i else False
		if not col_word == line_word:
			return False
	return True

def is_word_square(words):
	max_len = max([len(word) for word in words])
	for i in range(max_len):
		if not check_row_and_column(i, words, max_len):
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
