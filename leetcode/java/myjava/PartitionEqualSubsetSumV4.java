package myjava;

import java.util.*;

class PartitionEqualSubsetSumV4 {
  public static boolean canPartition(int[] nums) {
    final var numsSum = Arrays.stream(nums).sum();

    if (nums.length == 1 || numsSum % 2 == 1)
      return false;

    // If both subsets should have the same sum, our target is half the array sum
    final var targetSum = numsSum / 2;
    Set<Integer> sumsSet = new HashSet<>();
    sumsSet.add(0);

    for (final var num : nums) {
      Set<Integer> moreSums = new HashSet<>();
      for (final var sum : sumsSet) {
        final var newSum = sum + num;
        if (newSum == targetSum)
          return true;
        moreSums.add(newSum);
      }
      sumsSet.addAll(moreSums);
    }

    return false;
  }

  public static void main(String[] args) {
    assert canPartition(new int[]{1, 5, 11, 5}) == true;
    assert canPartition(new int[]{1, 2, 3, 5}) == false;
    assert canPartition(new int[]{2, 2, 3, 5}) == false;
  }
}
