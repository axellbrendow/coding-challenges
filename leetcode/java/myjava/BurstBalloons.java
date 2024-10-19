package myjava;

/*-
https://leetcode.com/problems/burst-balloons/

You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by
an array nums. You are asked to burst all the balloons.

If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out
of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.

Return the maximum coins you can collect by bursting the balloons wisely.

Example 1:
Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167

Example 2:
Input: nums = [1,5]
Output: 10

Constraints:

n == nums.length
1 <= n <= 300
0 <= nums[i] <= 100

---

3 2 5 8 2 7 6 2 9 7 5 3 5

If I choose 2 as the last balloon, it means all the other balloons were bursted so I'll have 1 * 2 * 1 at the end
          *
[3 2 5 8] 2 [7 6 2 9 7 5 3 5]

Now I have 2 subproblems, the subarray [3 2 5 8] and the subarray [7 6 2 9 7 5 3 5]. But it's important to notice
that the number 2 will be used to calculate the number of coins after bursting a balloon if necessary.

-

If I choose 2 as the last balloon, it means all the other balloons were bursted so I'll have 1 * 2 * 2 at the end
     *
1 [3 2 5 8] 2

Now I have 2 subproblems, the subarray [3] and the subarray [5 8]. But it's important to notice
that the number 2 will be used to calculate the number of coins after bursting a balloon if necessary.

-

If I choose 8 as the last balloon, it means all the other balloons were bursted so I'll have 2 * 8 * 2 at the end
     *
2 [5 8] 2
*/

public class BurstBalloons {
  public static int maxCoins(int[] nums, int left, int right, Integer[][] dp) {
    if (left > right) return 0;
    if (dp[left][right] != null) return dp[left][right];
    int localMaxCoins = 0;
    for (int i = left; i <= right; i++) {
      int coins = nums[left - 1] * nums[i] * nums[right + 1] + maxCoins(nums, left, i - 1, dp) + maxCoins(
        nums, i + 1, right, dp
      );
      localMaxCoins = Math.max(localMaxCoins, coins);
    }
    return dp[left][right] = localMaxCoins;
  }

  public static int maxCoins(int[] nums) {
    int[] newNums = new int[nums.length + 2];
    newNums[0] = 1;
    newNums[newNums.length - 1] = 1;
    System.arraycopy(nums, 0, newNums, 1, nums.length);
    return maxCoins(newNums, 1, newNums.length - 2, new Integer[newNums.length][newNums.length]);
  }

  public static void main(String[] args) {
    assert maxCoins(new int[]{1, 5}) == 10;
    assert maxCoins(new int[]{3, 1, 5, 8}) == 167;
    assert maxCoins(new int[]{0, 1}) == 1;
    assert maxCoins(new int[]{1, 0}) == 1;
    assert maxCoins(new int[]{3, 0, 2}) == 9;
  }
}
