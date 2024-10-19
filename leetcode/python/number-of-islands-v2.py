class Solution(object):
    def numIslands(self, grid):
        nlines = len(grid)
        ncolumns = len(grid[0])
        num_of_islands = 0
        group_leader = [i for i in range(nlines * ncolumns)]

        def find_group_leader(cell):
            while group_leader[cell] != cell:
                cell = group_leader[cell]
            return cell

        def union(curr_cell, prev_cell):
            nonlocal num_of_islands
            curr_cell_leader = find_group_leader(curr_cell)
            prev_cell_leader = find_group_leader(prev_cell)
            if curr_cell_leader != prev_cell_leader:
                # All subordinated cells will have a new leader
                group_leader[curr_cell_leader] = prev_cell_leader
                num_of_islands -= 1

        for i in range(nlines):
            for j in range(ncolumns):
                if grid[i][j] == '1':
                    num_of_islands += 1
                    if i - 1 >= 0 and grid[i - 1][j] == '1':
                        union(i * ncolumns + j, (i - 1) * ncolumns + j)

                    if j - 1 >= 0 and grid[i][j - 1] == '1':
                        union(i * ncolumns + j, i * ncolumns + (j - 1))

        return num_of_islands


assert Solution().numIslands([
    ['0', '1', '0', '0'],
    ['1', '1', '1', '0'],
    ['0', '1', '0', '0'],
    ['0', '0', '0', '1'],
]) == 2

assert Solution().numIslands([
    ["1", "1", "1", "1", "0"],
    ["1", "1", "0", "1", "0"],
    ["1", "1", "0", "0", "0"],
    ["0", "0", "0", "0", "0"]
]) == 1

assert Solution().numIslands([
    ["0", "1", "0"],
    ["1", "0", "1"],
    ["0", "1", "0"]
]) == 4
