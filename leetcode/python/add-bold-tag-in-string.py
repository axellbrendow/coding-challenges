def insert_tags(input, ranges):
	result = ''
	i = 0
	i_range = 0
	start, end = ranges[i_range]
	while i < len(input):
		if i < start or i > end:
			result += input[i]
			i += 1
		else:
			substr = input[start:end+1]
			result += f'<b>{substr}</b>'
			i = end + 1
			i_range += 1
			start, end = (len(input), -1) if i_range >= len(ranges) else ranges[i_range]
	if end != -1:
		substr = input[start:end+1]
		result += f'<b>{substr}</b>'
	return result

# def find_ranges_for(word, input, ranges):
# 	while input:
# 		try:
# 			start = input.index(word)
# 			ranges.append((start, start + len(word) - 1))
# 			input = input[start + len(word):]
# 		except:
# 			return

def find_ranges_for(word, input, ranges):
	offset = 0
	while input:
		try:
			start = input.index(word)
			ranges.append((offset + start, offset + start + len(word) - 1))
			input = input[1:]
			offset += 1
		except:
			return

def find_ranges_for_all_strings(input, words):
	ranges = []
	for word in words:
		find_ranges_for(word, input, ranges)
	return ranges

def join_ranges(ranges):
	ranges.sort()
	new_ranges = []
	curr_range = ranges[0]
	for i in range(1, len(ranges)):
		if ranges[i][0] <= curr_range[1] + 1:
			curr_range = (curr_range[0], ranges[i][1])
		else:
			new_ranges.append(curr_range)
			curr_range = ranges[i]
	if curr_range is not None:
		new_ranges.append(curr_range)
	return new_ranges

def write_bold_tags(input, words):
	ranges = find_ranges_for_all_strings(input, words)
	ranges = join_ranges(ranges)
	return insert_tags(input, ranges)

input = "abcxyz123"
words = ["abc","123"]
output = "<b>abc</b>xyz<b>123</b>"
assert write_bold_tags(input, words) == output

input = "aaabbcc"
words = ["aaa","aab","bc"]
output = "<b>aaabbc</b>c"
assert write_bold_tags(input, words) == output

input = "aaaa"
words = ["aaa"]
output = "<b>aaaa</b>"
assert write_bold_tags(input, words) == output
