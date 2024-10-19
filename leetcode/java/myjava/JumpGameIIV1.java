package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/jump-game-ii/

You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at
nums[i], you can jump to any nums[i + j] where:

0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can
reach nums[n - 1].

Example 1:
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3
steps to the last index.

Example 2:
Input: nums = [2,3,0,1,4]
Output: 2

Constraints:

1 <= nums.length <= 10^4
0 <= nums[i] <= 1000
It's guaranteed that you can reach nums[n - 1].

---

0 2 8 3 0 4 8 4 8 3 0 2 8 2 9 4 0 3 8 3 0 3 2 3 2  (impossible)

2 8 3 0 4 8 4 8 3 0 2 8 2 9 4 0 0 0 0 0 3 2 3 2
5 4 4 - 4 3 4 3 4 - 4 3 3 2 - - - - - - 1 1 1 0

5 9 3 2 1 0 2 3 3 1 0 0
                  - - 0
*/

public class JumpGameIIV1 {
  public static int jump(int[] nums) {
    int[] dp = new int[nums.length];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[nums.length - 1] = 0;
    for (int i = nums.length - 2; i >= 0; i--) {
      if (nums[i] == 0) continue;
      for (int j = 1; j <= nums[i] && i + j < nums.length; j++) {
        if (i + j == nums.length - 1) dp[i] = 1;
        if (nums[i + j] == 0 || dp[i + j] == Integer.MAX_VALUE) continue;
        dp[i] = Math.min(dp[i], 1 + dp[i + j]);
      }
    }
    return dp[0];
  }

  public static void main(String[] args) {
    assert jump(new int[]{5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0}) == 3;
    assert jump(new int[]{2, 8, 3, 0, 4, 8, 4, 8, 3, 0, 2, 8, 2, 9, 4, 0, 0, 0, 0, 0, 3, 2, 3, 2}) == 5;
  }
}
