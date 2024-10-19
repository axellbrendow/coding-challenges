package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/move-zeroes/
*/

public class MoveZerosV2 {
  public static void moveZeroes(int[] nums) {
    int numOfNonZeros = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        final var tmp = nums[numOfNonZeros];
        nums[numOfNonZeros++] = nums[i];
        nums[i] = tmp;
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
