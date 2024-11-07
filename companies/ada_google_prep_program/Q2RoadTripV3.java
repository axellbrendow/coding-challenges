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

Dijkstra's algorithm with pruning when budget is exceeded
*/

public class Q2RoadTripV3 {
  private static final int[][] DIRECTIONS = new int[][]{
    {0, 1}, // RIGHT
    {1, 1}, // RIGHT DOWN
    {1, 0}, // DOWN
  };

  public static int findMinCost(int[][] citiesCost, int budget) {
    int nlines = citiesCost.length, ncols = citiesCost[0].length;
    boolean[][] visited = new boolean[nlines][ncols];

    int[][] costToReach = new int[nlines][ncols];
    for (int[] row : costToReach) Arrays.fill(row, Integer.MAX_VALUE);
    costToReach[0][0] = citiesCost[0][0];

    while (true) {
      int smallestCostNodeI = -1, smallestCostNodeJ = -1;
      for (int i = 0; i < nlines; i++)
        for (int j = 0; j < ncols; j++)
          if (
            !visited[i][j] && costToReach[i][j] != Integer.MAX_VALUE && (smallestCostNodeI == -1 ||
              costToReach[i][j] < costToReach[smallestCostNodeI][smallestCostNodeJ])
          ) {
            smallestCostNodeI = i;
            smallestCostNodeJ = j;
          }

      if (smallestCostNodeI == -1) return -1;

      if (smallestCostNodeI == nlines - 1 && smallestCostNodeJ == ncols - 1)
        return costToReach[nlines - 1][ncols - 1] == Integer.MAX_VALUE
          ? -1
          : costToReach[nlines - 1][ncols - 1];

      visited[smallestCostNodeI][smallestCostNodeJ] = true;

      for (int[] direction : DIRECTIONS) {
        int nextCityI = smallestCostNodeI + direction[0], nextCityJ = smallestCostNodeJ + direction[1];
        if (!(0 <= nextCityI && nextCityI < nlines && 0 <= nextCityJ && nextCityJ < ncols))
          continue;

        int costWithThisEdge = costToReach[smallestCostNodeI][smallestCostNodeJ] +
          citiesCost[nextCityI][nextCityJ];

        if (costWithThisEdge < costToReach[nextCityI][nextCityJ] && costWithThisEdge <= budget) {
          costToReach[nextCityI][nextCityJ] = costWithThisEdge;
        }
      }
    }
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
