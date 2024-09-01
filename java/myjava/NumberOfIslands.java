package myjava;

import java.util.stream.IntStream;

/*-
https://leetcode.com/problems/number-of-islands/description/

Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number
of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

Example 1:
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
*/

public class NumberOfIslands {
  public static int find(int[] parent, int index) {
    while (parent[index] != index) index = parent[index];
    return index;
  }

  public static void union(
    int[] parent, int[] islands, int nlines, int ncols, int newI, int newJ, int oldI, int oldJ
  ) {
    int newIndex = ncols * newI + newJ;
    int oldIndex = ncols * oldI + oldJ;
    int newGroupLeader = find(parent, newIndex);
    int oldGroupLeader = find(parent, oldIndex);
    if (newGroupLeader != oldGroupLeader) {
      parent[oldGroupLeader] = newGroupLeader;
      islands[0]--;
    }
  }

  public static int numIslands(char[][] grid) {
    int nlines = grid.length, ncols = grid[0].length;
    int[] parent = new int[nlines * ncols], islands = new int[1];
    IntStream.range(0, parent.length).forEach(i -> parent[i] = i);
    for (int i = 0; i < nlines; i++) {
      for (int j = 0; j < ncols; j++) {
        if (grid[i][j] == '1') {
          islands[0]++;
          if (j - 1 >= 0 && grid[i][j - 1] == '1') {
            union(parent, islands, nlines, ncols, i, j, i, j - 1);
          }
          if (i - 1 >= 0 && grid[i - 1][j] == '1') {
            union(parent, islands, nlines, ncols, i, j, i - 1, j);
          }
        }
      }
    }
    return islands[0];
  }

  public static void main(String[] args) {
    assert numIslands(
      new char[][]{
        new char[]{'1', '1', '1', '1', '0'},
        new char[]{'1', '1', '0', '1', '0'},
        new char[]{'1', '1', '0', '0', '0'},
        new char[]{'0', '0', '0', '0', '0'}
      }
    ) == 1;

    assert numIslands(
      new char[][]{
        new char[]{'1', '1', '0', '0', '0'},
        new char[]{'1', '1', '0', '0', '0'},
        new char[]{'0', '0', '1', '0', '0'},
        new char[]{'0', '0', '0', '1', '1'}
      }
    ) == 3;
  }
}
