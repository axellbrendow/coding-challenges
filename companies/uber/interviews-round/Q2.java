import java.util.*;

/*-
Very similar question: Bomb Enemy https://www.lintcode.com/problem/553/

Stun gard:

Given a 2D grid, each cell is either a wall 'W', an guard 'G' or empty '0', return the maximum guard you can stun using one bomb.
The bomb stuns all the guards in the same row and column from the planted point until it hits the wall ’W’.


Example:
For the given grid
0 G 0 0
G 0 W G
0 G 0 0
return 3. (Placing a bomb at (1,1) stuns 3 gaurds)

2 G 1 2
G 3 W G
2 G 1 2

Example:
For the given grid
0 G 0 G 0 G 0 0 0 0
G 0 W G G W 0 G G G
0 G 0 0 W W G W W W

3 G 3 G 3 G 3 3 3 3
G 1 W G G W 3 G G G
1 G 1 1 W W G W W W

time: O(4 * nlines * ncols)
space: O(4 * nlines * ncols)
*/

class Solution {
  private static void incrementLineBlock(char[][] grid, int[][] dp, int numGuards, int line, int start, int end) {
    for (int i = start; i < end; i++) {
      if (grid[line][i] == '0') {
        dp[line][i] += numGuards;
      }
    }
  }

  private static void incrementColumnBlock(char[][] grid, int[][] dp, int numGuards, int column, int start, int end) {
    for (int i = start; i < end; i++) {
      if (grid[i][column] == '0')
        dp[i][column] += numGuards;
    }
  }

  private static int getMax(int[][] dp) {
    int max = 0;
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[0].length; j++) {
        if (dp[i][j] > max)
          max = dp[i][j];
      }
    }
    return max;
  }

  private static int maxGuard(char[][] grid) {
    int nlines = grid.length, ncols = grid[0].length;
    int[][] dp = new int[nlines][ncols];
    for (int i = 0; i < nlines; i++) {
      int numGuards = 0, start = 0, end = -1;
      for (int j = 0; j < ncols; j++) {
        if (grid[i][j] == 'G')
          numGuards++;
        if (grid[i][j] == 'W') {
          if (start == -1)
            start = j + 1;
          else {
            end = j;
            incrementLineBlock(grid, dp, numGuards, i, start, end);
            start = j + 1;
            end = -1;
            numGuards = 0;
          }
        }
      }
      if (start == -1 && end == -1)
        incrementLineBlock(grid, dp, numGuards, i, 0, ncols);
      if (start != -1 && end == -1)
        incrementLineBlock(grid, dp, numGuards, i, start, ncols);
    }

    for (int j = 0; j < ncols; j++) {
      int numGuards = 0, start = 0, end = -1;
      for (int i = 0; i < nlines; i++) {
        if (grid[i][j] == 'G')
          numGuards++;
        if (grid[i][j] == 'W') {
          if (start == -1)
            start = i + 1;
          else {
            end = i;
            incrementColumnBlock(grid, dp, numGuards, j, start, end);
            start = i + 1;
            end = -1;
            numGuards = 0;
          }
        }
      }
      if (start == -1 && end == -1)
        incrementColumnBlock(grid, dp, numGuards, j, 0, nlines);
      if (start != -1 && end == -1)
        incrementColumnBlock(grid, dp, numGuards, j, start, nlines);
    }

    int max = getMax(dp);
    print(dp);
    return max;
  }

  private static void print(int[][] dp) {
    for (int[] row : dp)
      System.out.println(Arrays.toString(row));
    System.out.println();
  }

  public static void main(String[] args) {
    assert maxGuard(
        new char[][] {
            new char[] { '0', 'G', '0', 'G', '0', 'G', '0', '0', '0', '0' },
            new char[] { 'G', '0', 'W', 'G', 'G', 'W', '0', 'G', 'G', 'G' },
            new char[] { '0', 'G', '0', '0', 'W', 'W', 'G', 'W', 'W', 'W' }
        }) == 4;

    assert maxGuard(
        new char[][] {
            new char[] { '0', 'G', '0', 'G', '0', 'G', 'G', '0', '0', '0' },
            new char[] { 'G', '0', 'W', 'G', 'G', 'W', '0', 'G', 'G', 'G' },
            new char[] { '0', 'G', '0', '0', 'W', 'W', 'G', 'W', 'W', 'W' }
        }) == 5;
  }
}
