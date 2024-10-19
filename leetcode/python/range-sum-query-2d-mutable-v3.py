class NumMatrix:
    def __init__(self, matrix):
        self.matrix = matrix
        self.nlines = len(matrix)
        self.ncolumns = len(matrix[0])
        self.create_sum_matrix()

    def create_sum_matrix(self):
        new_arr = [0] * (self.ncolumns + 1)
        self.sum_matrix = [new_arr]
        for i in range(self.nlines):  # 0
            new_arr = [0] * (self.ncolumns + 1)
            self.sum_matrix.append(new_arr)
            for j in range(self.ncolumns):  # 0
                new_arr[j + 1] = (
                    self.matrix[i][j]
                    + self.sum_matrix[i + 1][j]
                    + self.sum_matrix[i][j + 1]
                    - self.sum_matrix[i][j]
                )

    def sumRegion(self, row1, col1, row2, col2):
        return (
            self.sum_matrix[row2 + 1][col2 + 1]
            - self.sum_matrix[row2 + 1][col1]
            - self.sum_matrix[row1][col2 + 1]
            + self.sum_matrix[row1][col1]
        )

    def update(self, row, col, new_val):
        old_val = self.matrix[row][col]
        self.matrix[row][col] = new_val
        self.sum_matrix[row + 1][col + 1] += -old_val + new_val

        for i in range(row, self.nlines):
            for j in range(col, self.ncolumns):
                self.sum_matrix[i + 1][j + 1] += -old_val + new_val

matrix = NumMatrix([
    [3,0,1,4,2],
    [5,6,3,2,1],
    [1,2,0,1,5],
    [4,1,0,1,7],
    [1,0,3,0,5],
])

assert matrix.sumRegion(2,1,4,3) == 8
assert matrix.sumRegion(1,1,2,2) == 11
assert matrix.sumRegion(1,2,2,4) == 12
matrix.update(3,2,2)
assert matrix.sumRegion(2,1,4,3) == 10
