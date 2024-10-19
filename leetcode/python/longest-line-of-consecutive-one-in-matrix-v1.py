def find_longest_line_of_consecutive_one(matrix):
	nlines = len(matrix)
	ncolumns = len(matrix[0])
	longest = float('-inf')
	dp_horizontal = [[0] * (ncolumns + 1) for _ in range(nlines)]
	dp_vertical = [[0] * ncolumns for _ in range(nlines + 1)]
	dp_main_diag = [[0] * (ncolumns + 1) for _ in range(nlines + 1)]
	dp_anti_diag = [[0] * (ncolumns + 1) for _ in range(nlines + 1)]
	for i in range(nlines):
		for j in range(ncolumns):
			dp_horizontal[i][j + 1] = 0 if matrix[i][j] == 0 else dp_horizontal[i][j] + 1
			dp_vertical[i + 1][j] = 0 if matrix[i][j] == 0 else dp_vertical[i][j] + 1
			dp_main_diag[i + 1][j + 1] = 0 if matrix[i][j] == 0 else dp_main_diag[i][j] + 1
			dp_anti_diag[i + 1][j] = 0 if matrix[i][j] == 0 else dp_anti_diag[i][j + 1] + 1
			longest = max(
				longest,
				dp_horizontal[i][j + 1],
				dp_vertical[i + 1][j],
				dp_main_diag[i + 1][j + 1],
				dp_anti_diag[i + 1][j],
			)
	return longest

matrix = \
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]

assert find_longest_line_of_consecutive_one(matrix) == 3
