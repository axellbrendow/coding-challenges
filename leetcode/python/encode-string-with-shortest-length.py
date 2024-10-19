def compress(i, substr, dp):
	compressed = substr
	for k in range(len(substr)//2):
		pattern = substr[:k+1]
		if len(substr) % len(pattern) == 0 and substr.replace(pattern, '') == '':
			num_repetitions = len(substr) // len(pattern)
			encoded = f'{num_repetitions}[{dp[i][i+k]}]'
			if len(encoded) < len(compressed):
				compressed = encoded
	return compressed

def get_best_solution_for(i, j, dp, compressed):
	for k in range(i + 1, j):
		prev_solution = dp[i][k] + dp[k+1][j]
		if len(prev_solution) < len(compressed):
			compressed = prev_solution
	return compressed

def encode_with_shortest_length(string):
	dp = [[''] * len(string) for _ in range(len(string))]
	for size in range(1, len(string) + 1):
		for i in range(len(string) - size + 1):
			j = i + size - 1
			substr = string[i:j+1]
			if size < 5:
				dp[i][j] = substr
				continue
			compressed = compress(i, substr, dp)
			dp[i][j] = get_best_solution_for(i, j, dp, compressed)
	return dp[0][len(string) - 1]

assert encode_with_shortest_length('a') == 'a'
assert encode_with_shortest_length('aa') == 'aa'
assert encode_with_shortest_length('aaa') == 'aaa'
assert encode_with_shortest_length('aaaa') == 'aaaa'
assert encode_with_shortest_length('aaaaa') == '5[a]'
assert encode_with_shortest_length('aaaaaaaaaa') == '10[a]'
assert encode_with_shortest_length('aabcaabcd') == '2[aabc]d'
assert encode_with_shortest_length('abbbabbbcabbbabbbc') == '2[2[abbb]c]'
