def build_main_diags(nlines, ncolumns):
	diags = {}
	for j in range(ncolumns):
		diags[(0, j)] = 0
	for i in range(1, nlines):
		diags[(i, 0)] = 0
	return diags

def build_anti_diags(nlines, ncolumns):
	diags = {}
	for j in range(ncolumns):
		diags[(0, j)] = 0
	for i in range(1, nlines):
		diags[(i, ncolumns - 1)] = 0
	return diags

def get_main_diag(i, j):
	min_index = min(i, j)
	return (i - min_index, j - min_index)

def get_anti_diag(i, j, ncolumns):
	line_offset = i
	column_offset = (ncolumns - 1) - j
	if line_offset <= column_offset:
		return (0, j + line_offset)
	else:
		return (i - column_offset, ncolumns - 1)

def find_longest_line_of_consecutive_one(matrix):
	nlines = len(matrix)
	ncolumns = len(matrix[0])
	longest = float('-inf')
	dp_horizontal = [0] * nlines
	dp_vertical = [0] * ncolumns
	dp_main_diag = build_main_diags(nlines, ncolumns)
	dp_anti_diag = build_anti_diags(nlines, ncolumns)

	for i in range(nlines):
		for j in range(ncolumns):
			dp_horizontal[i] = 0 if matrix[i][j] == 0 else dp_horizontal[i] + 1
			dp_vertical[j] = 0 if matrix[i][j] == 0 else dp_vertical[j] + 1

			main_diag = get_main_diag(i, j)
			dp_main_diag[main_diag] = 0 if matrix[i][j] == 0 \
				else dp_main_diag[main_diag] + 1

			anti_diag = get_anti_diag(i, j, ncolumns)
			dp_anti_diag[anti_diag] = 0 if matrix[i][j] == 0 \
				else dp_anti_diag[anti_diag] + 1

			longest = max(
				longest,
				dp_horizontal[i],
				dp_vertical[j],
				dp_main_diag[main_diag],
				dp_anti_diag[anti_diag],
			)
	return longest

matrix = \
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]

assert find_longest_line_of_consecutive_one(matrix) == 3
