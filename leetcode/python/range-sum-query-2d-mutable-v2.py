class NumMatrix:
    def __init__(self, matrix):
        self.matrix = matrix
        self.nlines = len(matrix)
        self.ncolumns = len(matrix[0])
        self.create_sum_matrix()

    def create_sum_matrix(self):
        self.sum_matrix = []
        sum = 0
        for i in range(self.nlines):  # 0
            new_arr = [0] * self.ncolumns
            self.sum_matrix.append(new_arr)
            for j in range(self.ncolumns):  # 0
                sum += self.matrix[i][j]
                new_arr[j] = sum

    def sumSegment(self, row, col1, col2):
        return self.sum_matrix[row][col2] - self.sum_matrix[row][col1] + self.matrix[row][col1]

    def sumRegion(self, row1, col1, row2, col2):
        sum = 0
        for row in range(row1, row2 + 1):
            sum += self.sumSegment(row, col1, col2)
        return sum

    def prev_cell_pos(self, i, j):
        cell_i, cell_j = i, j - 1
        if cell_j < 0:
            cell_i -= 1
            cell_j = self.ncolumns - 1
        return cell_i, cell_j

    def prev_cell(self, i, j):
        prev_cell_i, prev_cell_j = self.prev_cell_pos(i, j)
        if prev_cell_i < 0: return None
        return self.sum_matrix[prev_cell_i][prev_cell_j]

    def update(self, row, col, new_val):
        self.matrix[row][col] = new_val

        sum = 0
        if row != 0 and col != 0:
            sum = self.prev_cell(row, col)

        for j in range(col, self.ncolumns):
            sum += self.matrix[row][j]
            self.sum_matrix[row][j] = sum

        for i in range(row + 1, self.nlines):
            for j in range(self.ncolumns):
                sum += self.matrix[i][j]
                self.sum_matrix[i][j] = sum

matrix = NumMatrix([
    [3, 0, 1, 4, 2],
    [5, 6, 3, 2, 1],
    [1, 2, 0, 1, 5],
    [4, 1, 0, 1, 7],
    [1, 0, 3, 0, 5],
])

assert matrix.sumRegion(2, 1, 4, 3) == 8
matrix.update(3, 2, 2)
assert matrix.sumRegion(2, 1, 4, 3) == 10
