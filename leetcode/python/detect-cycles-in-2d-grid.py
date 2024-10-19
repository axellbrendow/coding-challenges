from typing import List

"""
- Don't forget to use the dfs() return value
- Don't forget to return False in the end of the function
- Don't forget to set the visited array to True when a node is visited
- I used the wrong condition to check if two points are not equal. I used new_i != last_i and new_j != last_j but
    the real condition is new_i != last_i or new_j != last_j or even better not(new_i == last_i and new_j == last_j).
    Using tuples would facilitate my life too.
- I did this right because the question text mentions this but if you're checking a cycle in a graph it's important
    to store the last visited node and a cycle is detected only if you're not comparing with the last visited node.
"""


def containsCycle(grid: List[List[str]]) -> bool:
    nlines = len(grid)  # 2
    ncol = len(grid[0])  # 2
    visited = [[False for _ in range(ncol)] for _ in range(nlines)]
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]

    # i = 0, j = 0, curr_len = 1, last_i = -1, last_j = -1
    # i = 0, j = 1, curr_len = 2, last_i = 0, last_j = 0
    # i = 1, j = 1, curr_len = 3, last_i = 0, last_j = 1
    # i = 1, j = 0, curr_len = 4, last_i = 1, last_j = 1
    def dfs(i, j, curr_len, last_i, last_j):
        visited[i][j] = True
        # i_offset = 0, j_offset = 1
        # i_offset = 1, j_offset = 0
        # i_offset = 0, j_offset = -1
        # i_offset = -1, j_offset = 0
        for i_offset, j_offset in directions:
            new_i = i + i_offset  # 0 | 1 | 1 | 0
            new_j = j + j_offset  # 1 | 1 | 0 | 0
            if (
                0 <= new_i < nlines
                and 0 <= new_j < ncol
                and grid[i][j] == grid[new_i][new_j]
            ):
                if (
                    curr_len >= 3
                    and (new_i != last_i or new_j != last_j)
                    and visited[new_i][new_j]
                ):
                    return True
                elif not visited[new_i][new_j]:
                    if dfs(new_i, new_j, curr_len + 1, i, j):
                        return True
        return False

    for i in range(nlines):  # i = 0
        for j in range(ncol):  # j = 0
            if not visited[i][j]:
                if dfs(i, j, 1, -1, -1):
                    return True
    return False


assert containsCycle([
    ["a", "b"],
    ["b", "b"]
]) == False

assert containsCycle([
    ["b", "b"],
    ["b", "b"]
]) == True

assert containsCycle([
    ["a", "a", "a", "a"],
    ["a", "b", "b", "a"],
    ["a", "b", "b", "a"],
    ["a", "a", "a", "a"]
]) == True

assert containsCycle([
    ["c", "c", "c", "a"],
    ["c", "d", "c", "c"],
    ["c", "c", "e", "c"],
    ["f", "c", "c", "c"]
]) == True

assert containsCycle([
    ["b", "a", "c"],
    ["c", "a", "c"],
    ["d", "d", "c"],
    ["b", "c", "c"]
]) == False
