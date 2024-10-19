def find_number_end(start, abbr):
	while start < len(abbr) and '0' <= abbr[start] <= '9': start += 1
	return start

def is_valid_abbr(s, abbr):
	i, j = 0, 0
	while i < len(s) and j < len(abbr):
		if '1' <= abbr[j] <= '9':
			new_j = find_number_end(j, abbr)
			num = int(abbr[j:new_j])
			j = new_j
			i += num
		elif s[i] == abbr[j] and abbr[j] != '0':
			i += 1
			j += 1
		else:
			return False
	return i == len(s) and j == len(abbr)

s = 'a'
abbr = '01'
assert is_valid_abbr(s, abbr) == False

s = 'aa'
abbr = 'a2'
assert is_valid_abbr(s, abbr) == False

s = 'apple'
abbr = 'a2e'
assert is_valid_abbr(s, abbr) == False

s = 'apple'
abbr = 'a2le'
assert is_valid_abbr(s, abbr) == True

s = 'apple'
abbr = 'a2l1'
assert is_valid_abbr(s, abbr) == True

s = 'apple'
abbr = '5'
assert is_valid_abbr(s, abbr) == True
