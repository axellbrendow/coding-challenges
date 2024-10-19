from collections import defaultdict

def create_hashmaps(picture):
	nlines = len(picture)
	ncolumns = len(picture[0])

	cols_and_rows_with_b = defaultdict(list)
	rows_str = {}
	col_and_black_pixels = defaultdict(int)
	row_and_black_pixels = defaultdict(int)

	for i in range(nlines):
		rows_str[i] = ''.join(picture[i])
		for j in range(ncolumns):
			if picture[i][j] == 'B':
				row_and_black_pixels[i] += 1
				col_and_black_pixels[j] += 1
				cols_and_rows_with_b[j].append(i)

	return cols_and_rows_with_b, rows_str, col_and_black_pixels, row_and_black_pixels

def sum_black_pixels_in_columns_where_all_black_pixels_in_the_column_are_in_lines_that_are_equal(picture, N):
	cols_and_rows_with_b,\
	rows_str,\
	col_and_black_pixels,\
	row_and_black_pixels = create_hashmaps(picture)

	count = 0
	for col in cols_and_rows_with_b:
		rows = cols_and_rows_with_b[col]
		if not all(rows_str[row] == rows_str[rows[0]] for row in rows):
			continue
		if not col_and_black_pixels[col] == row_and_black_pixels[rows[0]] == N:
			continue
		count += N
	return count

N = 3
picture = \
[['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'W', 'B', 'W', 'B', 'W']] 

assert sum_black_pixels_in_columns_where_all_black_pixels_in_the_column_are_in_lines_that_are_equal(picture, N) == 6
