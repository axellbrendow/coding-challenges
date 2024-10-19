package myjava;

import java.util.*;

class TwoSumV4 {
  public static int[] twoSum(int[] nums, int target) {
    final Map<Integer, Integer> numToIndex = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (numToIndex.containsKey(target - nums[i])) {
        return new int[]{numToIndex.get(target - nums[i]), i};
      }
      numToIndex.put(nums[i], i);
    }
    return null;
  }

  public static void main(String[] args) {
    assert Arrays.equals(twoSum(new int[]{2, 7, 11, 15}, 9), new int[]{0, 1});
    assert Arrays.equals(twoSum(new int[]{3, 2, 4}, 6), new int[]{1, 2});
    assert Arrays.equals(twoSum(new int[]{3, 3}, 6), new int[]{0, 1});
  }
}
