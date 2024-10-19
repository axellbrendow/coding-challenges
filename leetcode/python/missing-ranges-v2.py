def add_range(output, lower, upper):
	if lower > upper: return
	if lower == upper: output.append(str(lower))
	else: output.append(f'{lower}->{upper}')

def find_missing_ranges(integers, lower, upper):
	output = []

	if len(integers) == 0:
		add_range(output, lower, upper)
		return output

	add_range(output, lower, integers[0] - 1)
	for i in range(1, len(integers)):
		add_range(output, integers[i - 1] + 1, integers[i] - 1)
	add_range(output, integers[-1] + 1, upper)
	return output

assert str(find_missing_ranges([], 1, 1)) == str(['1'])
assert str(find_missing_ranges([1], 1, 1)) == str([])
assert str(find_missing_ranges([1], 1, 2)) == str(['2'])
assert str(find_missing_ranges([1, 2], 1, 2)) == str([])
assert str(find_missing_ranges([1, 2], 1, 5)) == str(['3->5'])
