package myjava;

/*-
Theory base: https://algo.monster/problems/binary_search_boundary

https://leetcode.com/problems/sqrtx/
Given a non-negative integer x, return the square root of x rounded down to the nearest integer.
The returned integer should be non-negative as well.

0 <= x <= 2^31 - 1

Square root of x is guaranteed to be a number between 0 and x, so that's our search space.
Let y be a number between 0 and x, if y*y is greater than x, then we know all elements greater than y
can be discarded from the solution. That's the key idea for the binary search approach because we can
cut big parts of the search space by doing this comparison.
*/

public class Sqrt {
  public static int mySqrt(int x) {
    long left = 0;
    long right = x;
    long result = 0;
    while (left <= right) {
      // left + right cannot overflow int
      long middle = (left + right) / 2;
      long middleSquared = middle * middle;
      if (middleSquared == x) return (int) middle;
      else if (middleSquared > x) right = middle - 1;
      else {
        result = middle;
        left = middle + 1;
      }
    }
    return (int) result;
  }

  public static void main(String[] args) {
    assert mySqrt(0) == 0;
    assert mySqrt(1) == 1;
    assert mySqrt(2) == 1;
    assert mySqrt(3) == 1;
    assert mySqrt(4) == 2;
    assert mySqrt(5) == 2;
  }
}
