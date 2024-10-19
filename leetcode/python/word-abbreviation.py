from collections import defaultdict

def put_on_output_and_create_new_strings(strings, output, abbreviations_map):
	new_strings = []
	for abbr in abbreviations_map:
		for abbr_i in abbreviations_map[abbr]:
			if len(abbreviations_map[abbr]) == 1:
				output[abbr_i] = abbr
			else:
				new_strings.append(strings[abbr_i])
	return new_strings

def abbreviate(string, prefix_len):
	if len(string) - prefix_len <= 3: return string
	abbr = string[0] + string[1:1+prefix_len] + str(len(string) - 2 - prefix_len) + string[-1]
	return string if len(string) <= len(abbr) else abbr

def count_abbreviations(strings, prefix_len, index_of):
	abbreviations_map = defaultdict(list)
	for string in strings:
		abbreviations_map[abbreviate(string, prefix_len)].append(index_of[string])
	return abbreviations_map

def generate_min_possible_abbreviations(strings):
	output = [None] * len(strings)
	prefix_len = 0
	index_of = {string: i for i, string in enumerate(strings)}
	abbreviations_map = count_abbreviations(strings, prefix_len, index_of)
	new_strings = put_on_output_and_create_new_strings(
		strings, output, abbreviations_map
	)
	while len(new_strings) > 0:
		prefix_len += 1
		abbreviations_map = count_abbreviations(new_strings, prefix_len, index_of)
		new_strings = put_on_output_and_create_new_strings(
			strings, output, abbreviations_map
		)
	return output

strings = ['']
assert generate_min_possible_abbreviations(strings) == ['']

strings = ['e']
assert generate_min_possible_abbreviations(strings) == ['e']

strings = ['me']
assert generate_min_possible_abbreviations(strings) == ['me']

strings = ['mee']
assert generate_min_possible_abbreviations(strings) == ['mee']

strings = ['meet', 'meat']
assert generate_min_possible_abbreviations(strings) == ['meet', 'meat']

strings = ['meet', 'meeet']
assert generate_min_possible_abbreviations(strings) == ['m2t', 'm3t']

strings = ['maaat', 'mbbbt', 'mbaat']
assert generate_min_possible_abbreviations(strings) == ['ma2t', 'mbbbt', 'mbaat']
