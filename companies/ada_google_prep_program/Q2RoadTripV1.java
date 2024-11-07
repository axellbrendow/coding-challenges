package ada_google_prep_program;

import java.util.*;

/*-
You are on a budgeted road trip through a country with a grid of cities. Each city is represented by a cell
in a 2D grid, and the value in each cell indicates the cost of staying in that city for a day. You start at
the top-left corner of the grid (0, 0), always staying the first day in that city. You also start with a given
amount of money, and your goal is to reach the bottom-right corner (n-1,m-1) which has a cost of 0 since it's
your final destination.

You can only make one move per day, and you can move right, down or diagonally right-down at each step. Write
an algorithm to find the minimum total cost, so you can maximize your remaining budget, from the top-left to
the bottom-right corner. If you cannot reach the final destination with the given amount of money, return -1.

---

This code is wrong, this problem is actually a graph problem. If I remove the dp matrix it works but then the
time complexity is 3^numCities
*/

public class Q2RoadTripV1 {
  private static final int IMPOSSIBLE = -1;
  private static final int UNKNOWN = -2;
  private static final int[][] DIRECTIONS = new int[][]{
    {0, 1}, // RIGHT
    {1, 1}, // RIGHT DOWN
    {1, 0}, // DOWN
  };

  // The Ada platform had a bug with overloaded methods. I needed to change the name of this method to findMinCostRec.
  private static int findMinCostRec(
    int[][] citiesCost,
    int nlines,
    int ncols,
    int budget,
    int spent,
    int[][] dp,
    int i,
    int j
  ) {
    if (!(0 <= i && i < nlines && 0 <= j && j < ncols)) return IMPOSSIBLE;
    spent += citiesCost[i][j];
    if (spent > budget) return IMPOSSIBLE;
    if (dp[i][j] != UNKNOWN) return dp[i][j];
    if (i == nlines - 1 && j == ncols - 1) return 0;
    int minCost = IMPOSSIBLE;
    for (int[] direction : DIRECTIONS) {
      int newI = i + direction[0], newJ = j + direction[1];
      int cost = findMinCostRec(citiesCost, nlines, ncols, budget, spent, dp, newI, newJ);
      if (cost == IMPOSSIBLE) continue;
      cost += citiesCost[i][j];
      if (cost != IMPOSSIBLE && (minCost == IMPOSSIBLE || cost < minCost)) minCost = cost;
    }
    return dp[i][j] = minCost;
  }

  public static int findMinCost(int[][] citiesCost, int budget) {
    int nlines = citiesCost.length, ncols = citiesCost[0].length;
    int[][] dp = new int[nlines][ncols];
    for (int[] row : dp) Arrays.fill(row, UNKNOWN);
    return findMinCostRec(citiesCost, nlines, ncols, budget, 0, dp, 0, 0);
  }

  public static void main(String[] args) {
    assert findMinCost(
      new int[][]{
        {1, 2, 3},
        {3, 1, 2},
        {2, 3, 0},
      },
      2
    ) == 2;

    assert findMinCost(
      new int[][]{
        {1, 2, 3},
        {3, 1, 2},
        {2, 3, 0},
      },
      1
    ) == -1;

    assert findMinCost(
      new int[][]{
        {1, 1, 1},
        {1, 4, 1},
        {1, 2, 0},
      },
      3
    ) == 3;

    assert findMinCost(
      new int[][]{
        {1, 1, 1},
        {1, 4, 2},
        {1, 1, 0},
      },
      3
    ) == 3;

    assert findMinCost(
      new int[][]{
        {1, 4, 9, 10},
        {4, 4, 9, 20},
        {8, 8, 1, 9},
        {8, 8, 9, 0},
      },
      9
    ) == 6;

    assert findMinCost(
      new int[][]{
        {1, 1, 1, 10},
        {1, 4, 9, 1},
        {1, 8, 1, 1},
        {8, 1, 1, 0},
      },
      5
    ) == 5;

    assert findMinCost(
      new int[][]{
        {1, 4, 20, 10},
        {2, 4, 20, 20},
        {2, 2, 9, 1},
        {2, 2, 9, 0},
      },
      300
    ) == 14;
  }
}
