package myjava;

/*-
https://leetcode.com/problems/jump-game/description/

You are given an integer array nums. You are initially positioned at the array's first index, and each
element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

Example 1:
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it
impossible to reach the last index.

Constraints:

1 <= nums.length <= 10^4
0 <= nums[i] <= 10^5

---

0 2 8 3 0 4 8 4 8 3 0 2 8 2 9 4 0 3 8 3 0 3 2 3 2  (impossible)

2 8 3 0 4 8 4 8 3 0 2 8 2 9 4 0 0 0 0 0 3 2 3 2
                          T F F F F F F T T T T
*/

public class JumpGame {
  public static boolean canJump(int[] nums) {
    int lastTrue = nums.length - 1;
    for (int i = nums.length - 2; i >= 0; i--) {
      if (i + nums[i] >= lastTrue) {
        lastTrue = i;
      }
    }
    return lastTrue == 0;
  }

  public static void main(String[] args) {
    assert canJump(new int[]{2, 3, 1, 1, 4}) == true;
    assert canJump(new int[]{3, 2, 1, 0, 4}) == false;
  }
}
