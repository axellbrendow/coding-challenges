package myjava;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/*-
https://leetcode.com/problems/spiral-matrix/description/
*/

public class SpiralMatrix {
  public static List<Integer> spiralOrder(int[][] matrix) {
    int horizontalSteps = matrix[0].length;
    int verticalSteps = matrix.length;
    int i = 0, j = -1;
    final List<Integer> output = new ArrayList<>();
    while (true) {
      // going right
      for (int k = 0; k < horizontalSteps; k++) {
        j += 1;
        output.add(matrix[i][j]);
      }
      verticalSteps--;
      if (horizontalSteps <= 0 || verticalSteps <= 0) break;

      // going down
      for (int k = 0; k < verticalSteps; k++) {
        i += 1;
        output.add(matrix[i][j]);
      }
      horizontalSteps--;
      if (horizontalSteps <= 0 || verticalSteps <= 0) break;

      // going left
      for (int k = 0; k < horizontalSteps; k++) {
        j -= 1;
        output.add(matrix[i][j]);
      }
      verticalSteps--;
      if (horizontalSteps <= 0 || verticalSteps <= 0) break;

      // going up
      for (int k = 0; k < verticalSteps; k++) {
        i -= 1;
        output.add(matrix[i][j]);
      }
      horizontalSteps--;
      if (horizontalSteps <= 0 || verticalSteps <= 0) break;
    }
    return output;
  }

  public static void main(String[] args) {
    int[][] matrix;

    matrix = new int[][]{
      {1}
    };
    assert spiralOrder(matrix).equals(List.of(1));

    matrix = new int[][]{
      { 1, 2 }
    };
    assert spiralOrder(matrix).equals(List.of(1, 2));

    matrix = new int[][]{
      { 1 },
      { 2 }
    };
    assert spiralOrder(matrix).equals(List.of(1, 2));

    matrix = new int[][]{
      { 1, 2 },
      { 4, 3 }
    };
    assert spiralOrder(matrix).equals(IntStream.range(1, 5).boxed().toList());

    matrix = new int[][]{
      { 1, 2, 3 },
      { 6, 5, 4 }
    };
    assert spiralOrder(matrix).equals(IntStream.range(1, 7).boxed().toList());

    matrix = new int[][]{
      { 1, 2 },
      { 6, 3 },
      { 5, 4 }
    };
    assert spiralOrder(matrix).equals(IntStream.range(1, 7).boxed().toList());

    matrix = new int[][]{
      { 1, 2, 3, 4, 5, 6 },
      { 20, 21, 22, 23, 24, 7 },
      { 19, 32, 33, 34, 25, 8 },
      { 18, 31, 36, 35, 26, 9 },
      { 17, 30, 29, 28, 27, 10 },
      { 16, 15, 14, 13, 12, 11 },
    };
    assert spiralOrder(matrix).equals(IntStream.range(1, 37).boxed().toList());
  }
}
