# Time Limit Exceeded

def find_missing_ranges(integers, lower, upper):
	MISSING_VALUE = 1
	NO_MISSING_VALUE = -1
	i_integers = 0
	state = NO_MISSING_VALUE
	start = ''
	output = []
	for value in range(lower, upper + 1):
		if i_integers >= len(integers) or value != integers[i_integers]:
			if state == NO_MISSING_VALUE:
				start = value
				state = MISSING_VALUE
		else:
			i_integers += 1
			if state == MISSING_VALUE:
				range_str = str(start) if start == value - 1 else f'{start}->{value-1}'
				output.append(range_str)
			state = NO_MISSING_VALUE
	if state == MISSING_VALUE:
		range_str = str(start) if start == upper else f'{start}->{upper}'
		output.append(range_str)
	return output

assert str(find_missing_ranges([], 1, 1)) == str(['1'])
assert str(find_missing_ranges([1], 1, 1)) == str([])
assert str(find_missing_ranges([1], 1, 2)) == str(['2'])
assert str(find_missing_ranges([1, 2], 1, 2)) == str([])
assert str(find_missing_ranges([1, 2], 1, 5)) == str(['3->5'])
