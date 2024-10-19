package myjava;

import java.util.*;
import java.util.stream.*;

class TwoSumV3 {
  public static int[] twoSum(int[] nums, int target) {
    final var numToIndices = new HashMap<Integer, Integer>();

    return IntStream.range(0, nums.length)
      .filter(index -> {
        final var complementPresent = numToIndices.containsKey(target - nums[index]);
        if (!complementPresent)
          numToIndices.put(nums[index], index);
        return complementPresent;
      })
      .mapToObj(index -> new int[]{numToIndices.get(target - nums[index]), index})
      .findFirst().get();
  }

  public static void main(String[] args) {
    assert Arrays.equals(twoSum(new int[]{2, 7, 11, 15}, 9), new int[]{0, 1});
    assert Arrays.equals(twoSum(new int[]{3, 2, 4}, 6), new int[]{1, 2});
    assert Arrays.equals(twoSum(new int[]{3, 3}, 6), new int[]{0, 1});
  }
}
