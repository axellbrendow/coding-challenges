class FenwickTree2D:
    def __init__(self, nlines, ncolumns) -> None:
        self._nlines = nlines
        self._ncolumns = ncolumns
        self._tree = [[0] * ncolumns for _ in range(nlines)]

    def update(self, row, col, delta):
        i, j = row, col
        while i < self._nlines:
            j = col
            while j < self._ncolumns:
                self._tree[i][j] += delta
                j += j & -j
            i += i & -i

    def query(self, row, col):
        total = 0
        i, j = row, col
        while i > 0:
            j = col
            while j > 0:
                total += self._tree[i][j]
                j -= j & -j
            i -= i & -i
        return total

class NumMatrix:
    def __init__(self, matrix):
        self.matrix = matrix
        self.nlines = len(matrix)
        self.ncolumns = len(matrix[0])
        self.tree = FenwickTree2D(self.nlines + 1, self.ncolumns + 1)
        self.fill_tree()

    def fill_tree(self):
        for i in range(self.nlines):
            for j in range(self.ncolumns):
                self.tree.update(i + 1, j + 1, self.matrix[i][j])

    def sumRegion(self, row1, col1, row2, col2):
        return (
            self.tree.query(row2 + 1, col2 + 1)
            - self.tree.query(row2 + 1, col1)
            - self.tree.query(row1, col2 + 1)
            + self.tree.query(row1, col1)
        )

    def update(self, row, col, new_val):
        delta = new_val - self.matrix[row][col]
        self.matrix[row][col] = new_val
        self.tree.update(row + 1, col + 1, delta)

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
