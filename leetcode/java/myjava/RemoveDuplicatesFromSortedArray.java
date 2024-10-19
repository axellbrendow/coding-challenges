package myjava;

import java.util.*;

public class RemoveDuplicatesFromSortedArray {
  public static int removeDuplicates(int[] nums) {
    int i = 0, j = 0, countOfUniques = 0;
    while (j < nums.length) {
      while (j < nums.length && nums[i] == nums[j]) {
        j++;
      }
      nums[countOfUniques++] = nums[i];
      i = j;
    }
    return countOfUniques;
  }

  public static void main(String[] args) {
    int[] input, result;

    input = new int[]{};
    result = new int[]{};
    removeDuplicates(input);
    assert Arrays.equals(input, 0, result.length, result, 0, result.length);

    input = new int[]{1};
    result = new int[]{1};
    removeDuplicates(input);
    assert Arrays.equals(input, 0, result.length, result, 0, result.length);

    input = new int[]{1, 1};
    result = new int[]{1};
    removeDuplicates(input);
    assert Arrays.equals(input, 0, result.length, result, 0, result.length);

    input = new int[]{1, 1, 2};
    result = new int[]{1, 2};
    removeDuplicates(input);
    assert Arrays.equals(input, 0, result.length, result, 0, result.length);

    input = new int[]{1, 1, 2, 2, 2, 3};
    result = new int[]{1, 2, 3};
    removeDuplicates(input);
    assert Arrays.equals(input, 0, result.length, result, 0, result.length);
  }
}
