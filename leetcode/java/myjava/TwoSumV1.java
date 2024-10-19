package myjava;

import java.util.*;

class TwoSumV1 {
  public static int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          return new int[]{i, j};
        }
      }
    }
    return null;
  }

  public static void main(String[] args) {
    assert Arrays.equals(twoSum(new int[]{2, 7, 11, 15}, 9), new int[]{0, 1});
    assert Arrays.equals(twoSum(new int[]{3, 2, 4}, 6), new int[]{1, 2});
    assert Arrays.equals(twoSum(new int[]{3, 3}, 6), new int[]{0, 1});
  }
}
