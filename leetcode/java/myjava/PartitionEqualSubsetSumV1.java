package myjava;

import java.util.*;

// Time Limit Exceeded
// Time complexity: O(2^(n+1))
// Space complexity: O(n)

/*-
[1,5,11,5]

[1] [5,11,5]
[5] [1,11,5]
[11] [1,5,5]
[5] [1,11,5]

[1,5] [11,5]
[1,11] [5,5]
[1,5] [11,5]

[1,5,11] [5]
[1,5,5] [11]

[1,5,11,5] []
*/

class PartitionEqualSubsetSumV1 {
  private static boolean leftAndRightSumsAreEqual(
    int start, int remainingLeftSubsetSize, int leftSum, int rightSum, int[] nums
  ) {
    if (leftSum == rightSum)
      return true;

    if (remainingLeftSubsetSize <= 0)
      return false;

    for (int i = start; i < nums.length - remainingLeftSubsetSize + 1; i++) {
      if (leftAndRightSumsAreEqual(
        i + 1, remainingLeftSubsetSize - 1, leftSum + nums[i], rightSum - nums[i], nums
      )) {
        return true;
      }
    }

    return false;
  }

  public static boolean canPartition(int[] nums) {
    if (nums.length == 1)
      return false;

    final var numsSum = Arrays.stream(nums).sum();

    for (int currLeftSubsetSize = 1; currLeftSubsetSize < 1 + nums.length / 2; currLeftSubsetSize++) {
      for (int start = 0; start < nums.length - currLeftSubsetSize + 1; start++) {
        if (leftAndRightSumsAreEqual(start, currLeftSubsetSize, 0, numsSum, nums))
          return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    assert canPartition(new int[]{1, 5, 11, 5}) == true;
    assert canPartition(new int[]{1, 2, 3, 5}) == false;
    assert canPartition(new int[]{2, 2, 3, 5}) == false;
  }
}
