package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/move-zeroes/

1 0 0 3 12
  f
      n

find first non zero after first zero

0 1 0 3 12
f
  n

1 0 0 3 12
f
  n

1 0 0 3 12
  f
      n

1 3 0 0 12
  f
      n

1 3 0 0 12
    f
        n

1 3 12 0 0
    f
         n

1 3 12 0 0
       f
           n
*/

public class MoveZerosV1 {
  public static void moveZeroes(int[] nums) {
    int indexOf0 = 0, indexOfNon0 = 0;
    while (true) {
      while (indexOf0 < nums.length && nums[indexOf0] != 0) indexOf0++;
      indexOfNon0 = indexOf0 + 1;
      while (indexOfNon0 < nums.length && nums[indexOfNon0] == 0) indexOfNon0++;
      if (indexOf0 < nums.length && indexOfNon0 < nums.length) {
        nums[indexOf0++] = nums[indexOfNon0];
        nums[indexOfNon0] = 0;
      } else {
        break;
      }
    }
  }

  public static void main(String[] args) {
    int[] input, output;

    input = new int[]{};
    output = new int[]{};
    moveZeroes(input);
    assert Arrays.equals(input, output);

    input = new int[]{0};
    output = new int[]{0};
    moveZeroes(input);
    assert Arrays.equals(input, output);

    input = new int[]{1};
    output = new int[]{1};
    moveZeroes(input);
    assert Arrays.equals(input, output);

    input = new int[]{0, 1};
    output = new int[]{1, 0};
    moveZeroes(input);
    assert Arrays.equals(input, output);

    input = new int[]{1, 0};
    output = new int[]{1, 0};
    moveZeroes(input);
    assert Arrays.equals(input, output);

    input = new int[]{1, 0, 0, 2, 3};
    output = new int[]{1, 2, 3, 0, 0};
    moveZeroes(input);
    assert Arrays.equals(input, output);

    input = new int[]{0, 1, 0, 3, 12};
    output = new int[]{1, 3, 12, 0, 0};
    moveZeroes(input);
    assert Arrays.equals(input, output);
  }
}
