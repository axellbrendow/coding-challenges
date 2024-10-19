def distribute_spaces_evenly(start, end, curr_len, words, max_width):
	num_blocks = (end - start)
	if num_blocks == 0:
		return words[start] + (' ' * (max_width - curr_len))

	remaining_spaces = max_width - curr_len + num_blocks
	num_spaces_foreach_block = remaining_spaces // num_blocks
	extra_spaces = remaining_spaces % num_blocks
	line = words[start]
	for i in range(start + 1, end + 1):
		spaces = ' ' * (num_spaces_foreach_block + (0 if extra_spaces == 0 else 1))
		if extra_spaces > 0: extra_spaces -= 1
		line += f'{spaces}{words[i]}'
	return line

def left_justify(start, end, curr_len, words, max_width):
	line = words[start]
	for i in range(start + 1, end + 1):
		line += f' {words[i]}'
	line += ' ' * (max_width - curr_len)
	return line

def text_justification(words, max_width):
	start = 0
	end = 0
	curr_len = len(words[0])
	output = []
	for i in range(1, len(words)):
		if curr_len + len(words[i]) + 1 > max_width:
			output.append(distribute_spaces_evenly(start, end, curr_len, words, max_width))
			start = end = i
			curr_len = len(words[i])
		else:
			end = i
			curr_len += 1 + len(words[i])
	output.append(left_justify(start, end, curr_len, words, max_width))
	return output

assert str(text_justification(["abc", "bc", "a"], 7)) == str(['abc  bc', 'a      '])

assert str(text_justification([
	"This", "is", "an", "example", "of", "text", "justification."
] ,16)) == str(["This    is    an","example  of text","justification.  "])

assert str(text_justification([
	"What", "must", "be", "acknowledgment", "shall", "be"
] ,16)) == str(["What   must   be","acknowledgment  ","shall be        "])

assert str(text_justification([
	"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", 
	"to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"
] ,20)) == str([
	"Science  is  what we", "understand      well", "enough to explain to",
	"a  computer.  Art is", "everything  else  we", "do                  "
])
