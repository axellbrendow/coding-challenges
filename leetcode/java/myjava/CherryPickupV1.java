package myjava;

/*-
https://leetcode.com/problems/cherry-pickup/description/

0   1   1   1
1   1   1   1
1   1   1   1
1  -1   1   1

0   x   1   1
y   x   x   1
y   y  yx   x
1  -1   y   x
x means first path
y means returning path

This is a brute force solution and obviously will give TLE
*/

public class CherryPickupV1 {
  private static final int EMPTY = 0;
  private static final int CHERRY = 1;
  private static final int THORN = -1;

  private static int cherryPickupSecondPath(int[][] grid, int nlines, int ncols, int i, int j) {
    if (i < 0 || j < 0 || grid[i][j] == THORN) return -1;
    int cherryCount = 0;
    if (grid[i][j] == CHERRY) {
      grid[i][j] = EMPTY;
      cherryCount++;
    }
    if (i == 0 && j == 0) return cherryCount;
    int left = cherryPickupSecondPath(grid, nlines, ncols, i, j - 1);
    int up = cherryPickupSecondPath(grid, nlines, ncols, i - 1, j);
    if (left == -1 && up == -1) return -1;
    if (cherryCount > 0) grid[i][j] = CHERRY;
    return cherryCount + Math.max(left == -1 ? 0 : left, up == -1 ? 0 : up);
  }

  private static int cherryPickupFirstPath(
    int[][] grid, int nlines, int ncols, int i, int j
  ) {
    if (i >= nlines || j >= ncols || grid[i][j] == THORN) return -1;
    int cherryCount = 0;
    if (grid[i][j] == CHERRY) {
      grid[i][j] = EMPTY;
      cherryCount++;
    }
    if (i == nlines - 1 && j == ncols - 1) {
      return (cherryCount + cherryPickupSecondPath(grid, nlines, ncols, i, j));
    }
    int right = cherryPickupFirstPath(grid, nlines, ncols, i, j + 1);
    int down = cherryPickupFirstPath(grid, nlines, ncols, i + 1, j);
    if (right == -1 && down == -1) return -1;
    if (cherryCount > 0) grid[i][j] = CHERRY;
    return cherryCount + Math.max(right == -1 ? 0 : right, down == -1 ? 0 : down);
  }

  public static int cherryPickup(int[][] grid) {
    int cherryCount = cherryPickupFirstPath(grid, grid.length, grid[0].length, 0, 0);
    return cherryCount == -1 ? 0 : cherryCount;
  }

  public static void main(String[] args) {
    assert cherryPickup(
      new int[][]{
        {1, -1},
        {-1, 1}
      }
    ) == 0;

    assert cherryPickup(
      new int[][]{
        {1, 1, -1},
        {1, -1, 1},
        {-1, 1, 1}
      }
    ) == 0;

    assert cherryPickup(
      new int[][]{
        {0, 1, -1},
        {1, 0, -1},
        {1, 1, 1}
      }
    ) == 5;

    assert cherryPickup(
      new int[][]{
        {0, 1, -1, -1},
        {-1, 1, 1, -1},
        {1, -1, 1, 1},
        {1, 1, 1, 1}
      }
    ) == 7;

    assert cherryPickup(
      new int[][]{
        {0, 1, -1, 1},
        {1, -1, 1, 1},
        {1, 1, 1, 1},
        {1, 1, 1, 1}
      }
    ) == 9;
  }
}
