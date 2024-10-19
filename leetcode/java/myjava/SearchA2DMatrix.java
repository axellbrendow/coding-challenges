package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/search-a-2d-matrix/description/

You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.

Example 1:
Input: matrix = [
  [1,3,5,7],
  [10,11,16,20],
  [23,30,34,60]
], target = 3
Output: true

Example 2:
Input: matrix = [
  [1,3,5,7],
  [10,11,16,20],
  [23,30,34,60]
], target = 13
Output: false

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-10^4 <= matrix[i][j], target <= 10^4

---

Example 1:
Input: matrix = [
  [1,3,5,7],
  [10,11,16,20],
  [23,30,34,60]
], target = 3
Output: true

4 x 3 matrix = 12 elems = 0 ... 11
(0 + 11) / 2 = 11 / 2 = 5
(0 + 5) / 2 = 5 / 2 = 2
(0 + 2) / 2 = 2 / 2 = 1
*/

public class SearchA2DMatrix {
  public static boolean searchMatrix(int[][] matrix, int target) {
    int nlines = matrix.length, ncols = matrix[0].length, nElems = nlines * ncols;
    int left = 0, right = nElems - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      int midElem = matrix[mid / ncols][mid % ncols];
      if (midElem == target) return true;
      else if (midElem > target) right = mid - 1;
      else left = mid + 1;
    }
    return false;
  }

  public static void main(String[] args) {
    assert searchMatrix(
      new int[][]{
        new int[]{1,3,5,7},
        new int[]{10,11,16,20},
        new int[]{23,30,34,60}
      },
      3
    ) == true;

    assert searchMatrix(
      new int[][]{
        new int[]{1,3,5,7},
        new int[]{10,11,16,20},
        new int[]{23,30,34,60}
      },
      13
    ) == false;
  }
}
