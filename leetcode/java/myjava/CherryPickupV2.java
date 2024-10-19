package myjava;

import java.util.*;
import java.util.stream.*;

/*-
https://leetcode.com/problems/cherry-pickup/description/
*/

public class CherryPickupV2 {
  private static final int EMPTY = 0;
  private static final int CHERRY = 1;
  private static final int THORN = -1;

  private static final int UNDEFINED = -2;
  private static final int IMPOSSIBLE = -3;

  private static int cherryPickup(
    int[][] grid, int nlines, int ncols, int[][][][] dp, int i1, int j1, int i2, int j2
  ) {
    if (
      i1 >= nlines || j1 >= ncols || grid[i1][j1] == THORN ||
        i2 >= nlines || j2 >= ncols || grid[i2][j2] == THORN
    ) return IMPOSSIBLE;
    if (dp[i1][j1][i2][j2] != UNDEFINED) return dp[i1][j1][i2][j2];
    int cherryCount = 0;
    if (grid[i1][j1] == CHERRY) cherryCount++;
    if (grid[i2][j2] == CHERRY) cherryCount++;
    if (i1 == i2 && j1 == j2 && grid[i1][j1] == CHERRY) cherryCount--;
    if (i1 == nlines - 1 && j1 == ncols - 1 && i2 == nlines - 1 && j2 == ncols - 1) return cherryCount;
    int rightRight = cherryPickup(grid, nlines, ncols, dp, i1, j1 + 1, i2, j2 + 1);
    int rightDown = cherryPickup(grid, nlines, ncols, dp, i1, j1 + 1, i2 + 1, j2);
    int downRight = cherryPickup(grid, nlines, ncols, dp, i1 + 1, j1, i2, j2 + 1);
    int downDown = cherryPickup(grid, nlines, ncols, dp, i1 + 1, j1, i2 + 1, j2);
    if (
      rightRight == IMPOSSIBLE && rightDown == IMPOSSIBLE && downRight == IMPOSSIBLE && downDown == IMPOSSIBLE
    )
      return dp[i1][j1][i2][j2] = IMPOSSIBLE;
    return dp[i1][j1][i2][j2] = cherryCount + IntStream.of(
      rightRight == IMPOSSIBLE ? 0 : rightRight,
      rightDown == IMPOSSIBLE ? 0 : rightDown,
      downRight == IMPOSSIBLE ? 0 : downRight,
      downDown == IMPOSSIBLE ? 0 : downDown
    ).max().getAsInt();
  }

  public static int cherryPickup(int[][] grid) {
    int nlines = grid.length, ncols = grid[0].length;
    int[][][][] dp = new int[nlines][ncols][nlines][ncols];
    for (var cube : dp) for (var rectangle : cube) for (var row : rectangle) Arrays.fill(row, UNDEFINED);
    int cherryCount = cherryPickup(grid, nlines, ncols, dp, 0, 0, 0, 0);
    return cherryCount == IMPOSSIBLE ? 0 : cherryCount;
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

    assert cherryPickup(
      new int[][]{
        {1, 1, -1, 1, 1, 1, 0, 1, 1, -1, -1, 1, 1, -1, 1, 1, 1, 1, 0, 1},
        {1, 1, 1, 0, 1, 1, 0, 1, 0, 1, -1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, -1, -1, -1, 1, 1, 1, -1, 1, -1, 1},
        {1, 1, 1, -1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, -1, -1, 1, 1, 1},
        {1, -1, -1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 0, 1, 1, 1, 1, 1, -1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
        {0, 1, 1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
        {1, 1, -1, 1, 1, 1, -1, 1, 0, 1, 1, 1, 1, 1, 1, -1, 1, 1, 1, 1},
        {0, -1, 1, 1, 1, -1, 1, 1, 1, -1, 1, 1, 1, 1, 1, 1, -1, 1, 1, 1},
        {1, 1, -1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, -1, 0, 1, 0, -1, 1},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, -1, 0},
        {1, 1, 1, 1, 1, 1, -1, 1, 0, 1, 1, 1, 1, -1, 1, 1, 1, 0, 1, 1},
        {1, 1, 1, 1, -1, -1, 1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1, 1, 1},
        {-1, 1, 1, 1, 1, -1, 1, 1, 1, 1, 1, 1, -1, 1, 0, 0, 1, 0, 1, 1},
        {0, 1, -1, 1, 1, -1, 1, 1, 1, -1, 1, 1, 1, 1, 1, 1, 0, -1, 1, 1},
        {1, 1, 1, -1, 1, 1, 1, -1, 1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, -1},
        {1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 0, -1, 1, 1, 1, 1, 1, 1, 1, -1},
        {1, 1, 1, -1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, -1, 0, 1, 1},
        {1, 1, -1, 0, -1, 1, 1, -1, -1, 1, 1, -1, 1, 1, 1, 1, -1, -1, 0, 1},
        {-1, 0, 0, 1, 1, 1, 1, 1, -1, -1, 1, 1, 1, 0, 1, 0, 0, 1, -1, 1}
      }
    ) == 71;
  }
}
