package mindbody;

/*-
An image blob is a set of pixels '1' connected horizontally or vertically.

Example:
0 0 0 0 1 0 1
0 0 1 1 1 1 0
0 0 0 0 1 0 0
0 1 0 0 0 0 0
0 1 1 0 0 0 0
0 1 0 0 0 0 0
0 0 0 0 0 0 0

Num of blobs: 3

Given an image and a minimumSize, return the number of blobs that have at least the minimumSize.
*/

public class CountImageBlobs {
  private static final char PIXEL_ZERO = '0';
  private static final char PIXEL_ONE = '1';

  private static int getBlobSize(String[] grid, int nlines, int ncols, boolean[][] visited, int i, int j) {
    if (i < 0 || j < 0 || i >= nlines || j >= ncols) return 0;
    if (grid[i].charAt(j) == PIXEL_ZERO || visited[i][j]) return 0;
    visited[i][j] = true;
    return 1 +
      getBlobSize(grid, nlines, ncols, visited, i, j + 1) + // right
      getBlobSize(grid, nlines, ncols, visited, i + 1, j) + // down
      getBlobSize(grid, nlines, ncols, visited, i, j - 1) + // left
      getBlobSize(grid, nlines, ncols, visited, i - 1, j); // up
  }

  private static int countImageBlobs(String[] grid, int minimumSize) {
    int blobs = 0, nlines = grid.length, ncols = grid[0].length();
    boolean[][] visited = new boolean[nlines][ncols];
    for (int i = 0; i < nlines; i++)
      for (int j = 0; j < ncols; j++)
        if (
          !visited[i][j] &&
            grid[i].charAt(j) == PIXEL_ONE &&
            getBlobSize(grid, nlines, ncols, visited, i, j) >= minimumSize
        )
          blobs++;
    return blobs;
  }

  public static void main(String[] args) {
    assert countImageBlobs(
      new String[]{
        "0000101",
        "0011110",
        "0000100",
        "0100000",
        "0110000",
        "0100000",
        "0000000",
      },
      2
    ) == 2;

    assert countImageBlobs(
      new String[]{
        "0000000",
        "0000000",
        "0000000",
        "0000000",
        "0000000",
        "0000000",
        "0000000",
      },
      0
    ) == 0;

    assert countImageBlobs(
      new String[]{
        "1111111",
        "1111111",
        "1111111",
        "1111111",
        "1111111",
        "1111111",
        "1111111",
      },
      0
    ) == 1;

    assert countImageBlobs(
      new String[]{
        "1111111",
        "1111111",
        "1111111",
        "1111111",
        "1111111",
        "1111111",
        "1111111",
      },
      Integer.MAX_VALUE
    ) == 0;
  }
}
