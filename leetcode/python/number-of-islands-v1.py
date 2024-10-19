from typing import List

"""
grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
visited = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"],
  ["0","0","0","0","0"]
]
time: O(m x n)
space: O(m x n)
"""


def numIslands(grid: List[List[str]]) -> int:
    num_lines = len(grid)
    num_columns = len(grid[0])
    visited = [[False for _ in range(num_columns)] for _ in range(num_lines)]
    directions = [(0, 1), (-1, 0), (0, -1), (1, 0)]

    def dfs(i, j):
        visited[i][j] = True
        for line_offset, column_offset in directions:
            new_i, new_j = i + line_offset, j + column_offset
            if (
                0 <= new_i < num_lines
                and 0 <= new_j < num_columns
                and not visited[new_i][new_j]
                and grid[new_i][new_j] == "1"
            ):
                dfs(new_i, new_j)

    islands = 0
    for i in range(num_lines):
        for j in range(num_columns):
            if grid[i][j] == "1" and not visited[i][j]:
                dfs(i, j)
                islands += 1
    return islands


assert numIslands([
    ['0', '1', '0', '0'],
    ['1', '1', '1', '0'],
    ['0', '1', '0', '0'],
    ['0', '0', '0', '1'],
]) == 2

assert numIslands([
    ["1", "1", "1", "1", "0"],
    ["1", "1", "0", "1", "0"],
    ["1", "1", "0", "0", "0"],
    ["0", "0", "0", "0", "0"]
]) == 1

assert numIslands([
    ["0", "1", "0"],
    ["1", "0", "1"],
    ["0", "1", "0"]
]) == 4
