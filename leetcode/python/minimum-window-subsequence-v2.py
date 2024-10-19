def window_has_all_characters_of(T, window):
	i = 0
	for letter in T:
		try:
			i = 1 + window.index(letter, i)
		except:
			return False
	return True

def find_min_contiguous_substr(S, T):
	i, j = 0, 0
	min_substr = S + '0'
	while j < len(S):
		while window_has_all_characters_of(T, S[i+1:j+1]):
			if j - (i + 1) + 1 < len(min_substr):
				min_substr = S[i+1:j+1]
			i += 1

		if window_has_all_characters_of(T, S[i:j+1]):
			if j - i + 1 < len(min_substr):
				min_substr = S[i:j+1]
			i += 1
		else:
			j += 1
	return '' if min_substr == S + '0' else min_substr

S = 'aaa'
T = 'b'
assert find_min_contiguous_substr(S, T) == ''

S = 'aaa'
T = 'aa'
assert find_min_contiguous_substr(S, T) == 'aa'

S = 'abca'
T = 'ac'
assert find_min_contiguous_substr(S, T) == 'abc'

S = 'cbca'
T = 'ac'
assert find_min_contiguous_substr(S, T) == ''

S = 'dbacd'
T = 'acd'
assert find_min_contiguous_substr(S, T) == 'acd'

S = 'abcac'
T = 'ac'
assert find_min_contiguous_substr(S, T) == 'ac'

S = 'cnhczmccqouqadqtmjjzl'
T = 'mm'
assert find_min_contiguous_substr(S, T) == 'mccqouqadqtm'

S = 'dwmxzkzxwqegknd'
T = 'kzed'
assert find_min_contiguous_substr(S, T) == 'kzxwqegknd'
