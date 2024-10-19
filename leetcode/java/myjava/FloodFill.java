package myjava;

/*-
https://leetcode.com/problems/flood-fill/description/
*/

import java.util.Arrays;

public class FloodFill {
  public static void floodFill(int[][] image, int sr, int sc, int color, int initialColor, boolean[][] visited) {
    if (
        0 <= sr && sr < image.length
        && 0 <= sc && sc < image[0].length
        && !visited[sr][sc]
        && image[sr][sc] == initialColor
    ) {
      visited[sr][sc] = true;
      image[sr][sc] = color;
      floodFill(image, sr, sc + 1, color, initialColor, visited);
      floodFill(image, sr + 1, sc, color, initialColor, visited);
      floodFill(image, sr, sc - 1, color, initialColor, visited);
      floodFill(image, sr - 1, sc, color, initialColor, visited);
    }
  }

  public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
    boolean[][] visited = new boolean[image.length][image[0].length];
    floodFill(image, sr, sc, color, image[sr][sc], visited);
    return image;
  }

  public static void main(String[] args) {
    int[][] image, expected;

    image = new int[][] {
      new int[]{1,1,1},
      new int[]{1,1,0},
      new int[]{1,0,1}
    };
    expected = new int[][] {
      new int[]{2,2,2},
      new int[]{2,2,0},
      new int[]{2,0,1}
    };
    floodFill(image, 1, 1, 2);
    assert Arrays.deepEquals(image, expected);
  }
}
