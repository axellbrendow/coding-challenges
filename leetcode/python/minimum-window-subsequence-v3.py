'''
dp[i][j]

Ex.:
i = 1
j = 2

			j
S = '	b	a	b'
		i
T = '	a'

dp[1][2] = 2  # This means that if I consider 1 character from T and
              # 2 characters from S, the best solution starts at index 2
			  # considering that indices start with 1
'''

def find_min_contiguous_substr(S, T):
	dp = [[0] * (len(S) + 1) for _ in range(len(T) + 1)]

	for i in range(len(S) + 1):
		# If I consider 0 characters from T, then the solution can start at any index on S
		dp[0][i] = i + 1

	for i in range(1, len(T) + 1):
		for j in range(1, len(S) + 1):
			if S[j - 1] == T[i - 1]:  # If current characters match,
				# The index where the substring starts is the same that was found on the
				# previous position when I was looking for one character less from T
				dp[i][j] = dp[i - 1][j - 1]
			else:  # If characters do not match,
				# The index where the substring starts is the same that was found on the
				# previous position when I was looking for the same number of characters from T
				dp[i][j] = dp[i][j - 1]

	start, length = 0, len(S) + 1
	for j in range(1, len(S) + 1):
		if dp[len(T)][j] != 0:
			if j - dp[len(T)][j] + 1 < length:
				start = dp[len(T)][j] - 1
				length = j - dp[len(T)][j] + 1

	return '' if length == len(S) + 1 else S[start:start + length]

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
