def get_generalized_abbreviations(word):
	if len(word) == 0: return []
	output = []
	for i in range(2 << (len(word) - 1)):  # O(2^len(word))
		count = 0
		curr_str = ''
		for j in range(len(word) - 1, -1, -1):   # O(len(word))
			if (i >> j) & 1 == 1:
				count += 1
			else:
				if count > 0: curr_str += str(count)
				count = 0
				curr_str += word[len(word) - j - 1]
		if count > 0:
			curr_str += str(count)
		output.append(curr_str)
	return output


assert str(get_generalized_abbreviations('')) == str([])
assert str(get_generalized_abbreviations('a')) == str(['a', '1'])
assert str(get_generalized_abbreviations('ab')) == str(['ab', 'a1', '1b', '2'])
