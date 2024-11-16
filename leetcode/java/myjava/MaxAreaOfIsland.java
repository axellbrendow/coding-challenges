package myjava;

/*-
https://leetcode.com/problems/max-area-of-island/description/

You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected
4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.

Example 1:
Input: grid = [
  [0,0,1,0,0,0,0,1,0,0,0,0,0],
  [0,0,0,0,0,0,0,1,1,1,0,0,0],
  [0,1,1,0,1,0,0,0,0,0,0,0,0],
  [0,1,0,0,1,1,0,0,1,0,1,0,0],
  [0,1,0,0,1,1,0,0,1,1,1,0,0],
  [0,0,0,0,0,0,0,0,0,0,1,0,0],
  [0,0,0,0,0,0,0,1,1,1,0,0,0],
  [0,0,0,0,0,0,0,1,1,0,0,0,0]
]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.

Example 2:
Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] is either 0 or 1.
*/

public class MaxAreaOfIsland {
  private static final int WATER = 0;
  private static final int ISLAND = 1;
  private static final int[][] DIRECTIONS = new int[][]{
    {0, 1}, // RIGHT
    {1, 0}, // DOWN
    {0, -1}, // LEFT
    {-1, 0}, // UP
  };

  private static int maxAreaOfIsland(int[][] grid, int nlines, int ncols, boolean[][] visited, int i, int j) {
    if (!(0 <= i && i < nlines && 0 <= j && j < ncols)) return 0;
    if (grid[i][j] == WATER || visited[i][j]) return 0;
    visited[i][j] = true;
    int maxArea = 1;
    for (int[] direction : DIRECTIONS)
      maxArea += maxAreaOfIsland(grid, nlines, ncols, visited, i + direction[0], j + direction[1]);
    return maxArea;
  }

  public static int maxAreaOfIsland(int[][] grid) {
    int nlines = grid.length, ncols = grid[0].length, maxArea = 0;
    boolean[][] visited = new boolean[nlines][ncols];
    for (int i = 0; i < nlines; i++)
      for (int j = 0; j < ncols; j++)
        if (grid[i][j] == ISLAND && !visited[i][j]) maxArea = Math.max(
          maxArea,
          maxAreaOfIsland(grid, nlines, ncols, visited, i, j)
        );
    return maxArea;
  }

  public static void main(String[] args) {
    assert maxAreaOfIsland(
      new int[][]{
        {0, 0, 0, 0, 0, 0, 0, 0}
      }
    ) == 0;

    assert maxAreaOfIsland(
      new int[][]{
        {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
        {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
        {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
      }
    ) == 6;
  }
}
