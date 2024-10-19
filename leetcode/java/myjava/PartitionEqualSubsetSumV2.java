package myjava;

import java.util.*;

// Time Limit Exceeded
// Time complexity: O(2^(n+1))
// Space complexity: O(2^(n+1))

/*-
nums = [1, 5, 11, 5]

left right
include in the subset <----> do not include in the subset

Decision tree:

1
5 5
11 11 11 11
5 5 5 5 5 5 5 5
x x x x x x x x x x x x x x x x

2 ^ (n + 1)

where n is the length of the array
*/

class PartitionEqualSubsetSumV2 {
  public static boolean canPartition(int[] nums) {
    final var numsSum = Arrays.stream(nums).sum();

    if (nums.length == 1 || numsSum % 2 == 1)
      return false;

    // If both subsets should have the same sum, our target is half the array sum
    final var targetSum = numsSum / 2;

    final var subsets = includeAndDoNotIncludeIndex(0, nums);

    for (final var subset : subsets) {
      if (subset.stream().mapToInt(i -> i).sum() == targetSum) {
        return true;
      }
    }

    return false;
  }

  private static List<List<Integer>> includeAndDoNotIncludeIndex(int index, int[] nums) {
    if (index >= nums.length)
      return List.of(List.of());

    List<List<Integer>> allSubsetsFromIndexPlus1 = includeAndDoNotIncludeIndex(index + 1, nums);

    List<List<Integer>> allSubsetsFromIndexPlus1AddingCurrentIndex = new ArrayList<>();
    for (final var subset : allSubsetsFromIndexPlus1) {
      List<Integer> newSubsetAddingCurrentIndex = new ArrayList<>(subset);
      newSubsetAddingCurrentIndex.add(nums[index]);
      allSubsetsFromIndexPlus1AddingCurrentIndex.add(newSubsetAddingCurrentIndex);
    }

    List<List<Integer>> subsets = new ArrayList<>();
    subsets.addAll(allSubsetsFromIndexPlus1AddingCurrentIndex);
    subsets.addAll(allSubsetsFromIndexPlus1);
    return subsets;
  }

  public static void main(String[] args) {
    assert canPartition(new int[]{1, 5, 11, 5}) == true;
    assert canPartition(new int[]{1, 2, 3, 5}) == false;
    assert canPartition(new int[]{2, 2, 3, 5}) == false;
  }
}
