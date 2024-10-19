package myjava;

import java.util.*;

/*-
nums = [1, 5, 11, 5], sum of the array = 22

left right
do not include in the subset <----> include in the subset

Decision tree:

1
5 5
11 11 11 11
5 5 5 5 5 5 5 5
x x x x x x x x x x x x x x x x

tree size = 2 ^ (n + 1)

where n is the length of the array

Now the idea is to have a dynamic programming cache where the indexes are:
i : index I'm in the array
j : sum that I wanna know if it is possible to achieve from this index

What's interesting about this approach is that the number of possible subset sums in the array is limited.
sum(array) is the maximum value because it considers all elements and all the other possible sums are under
this value. In most cases, 2^n will be much bigger than n * sum(array), so that's why DP is better given
the problem constraints.

Another important point to observe is that, as the number of possible sums is probably smaller than 2^n,
if we do not use DP, we may be recomputing the question "Can I achieve the sum 10 from the index 5 ?".

nums = [1, 5, 11, 5], sum of the array = 22, half of the sum = 11

Table with indexes (0..3) vs all possible sums (0..11):

N = null -> The algorithm doesn't know if it is possible to achieve the sum from the current index
T = true -> The algorithm knows it is possible to achieve the sum from the current index
F = false -> The algorithm knows it is impossible to achieve the sum from the current index

| | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 |
| 0 | N | N | N | N | N | N | N | N | N | N | N | T |
| 1 | N | N | N | N | N | N | N | N | N | N | N | T |
| 2 | N | N | N | N | N | N | N | N | N | N | N | T |
| 3 | N | N | N | N | N | N | N | N | N | N | N | F |

For the input [2, 2, 3, 5], the DP table would end up with:

| | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
| 0 | N | N | N | N | N | N | F |
| 1 | N | N | N | N | F | N | F |
| 2 | N | N | F | N | F | N | F |
| 3 | N | F | F | F | F | N | F |

The entry [2][4] is reused one time.
*/

class PartitionEqualSubsetSumV3 {
  public static boolean canPartition(int[] nums) {
    final var numsSum = Arrays.stream(nums).sum();

    if (nums.length == 1 || numsSum % 2 == 1)
      return false;

    // If both subsets should have the same sum, our target is half the array sum
    final var targetSum = numsSum / 2;
    final var canAchieve = new Boolean[nums.length][targetSum + 1];

    return canAchieveTargetSumFromIndex(0, targetSum, nums, canAchieve);
  }

  private static boolean canAchieveTargetSumFromIndex(
    int index, int targetSum, int[] nums, Boolean[][] canAchieve
  ) {
    if (targetSum == 0)
      return true;
    if (targetSum < 0 || index >= nums.length)
      return false;
    if (canAchieve[index][targetSum] != null)
      return canAchieve[index][targetSum];

    return canAchieve[index][targetSum] = canAchieveTargetSumFromIndex(
      index + 1, targetSum, nums, canAchieve
    ) || canAchieveTargetSumFromIndex(index + 1, targetSum - nums[index], nums, canAchieve);
  }

  public static void main(String[] args) {
    assert canPartition(new int[]{1, 5, 11, 5}) == true;
    assert canPartition(new int[]{1, 2, 3, 5}) == false;
    assert canPartition(new int[]{2, 2, 3, 5}) == false;
  }
}
