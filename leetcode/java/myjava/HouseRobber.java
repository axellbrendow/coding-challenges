package myjava;

/*-
https://leetcode.com/problems/house-robber/description/

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security
systems connected and it will automatically contact the police if two adjacent houses were broken into on the
same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money
you can rob tonight without alerting the police.

Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 2:
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400

---

3 8 2 8 7 5 1 9 7 5 2 4
i
do not rob curr house and get best solution on the next house
rob curr house and get best solution skipping the next house

time: O(n)
space: O(1)
                *       nextHouse       skipNextHouse=0
a       b       c       d

        *       nextHouse       skipNextHouse
a       b       c               d
*/

public class HouseRobber {
  public static int rob(int[] nums) {
    int nextHouse = nums[nums.length - 1], skipNextHouse = 0;
    for (int i = nums.length - 2; i >= 0; i--) {
      int tmp = nextHouse;
      nextHouse = Math.max(nums[i] + skipNextHouse, nextHouse);
      skipNextHouse = tmp;
    }
    return Math.max(nextHouse, skipNextHouse);
  }

  public static void main(String[] args) {
    assert rob(new int[]{1, 2, 3, 1}) == 4;
    assert rob(new int[]{2, 7, 9, 3, 1}) == 12;
  }
}
