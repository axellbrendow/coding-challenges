package myjava;

/*-
https://leetcode.com/problems/maximum-subarray/

Given an integer array nums, find the subarray with the largest sum, and return its sum.

Example 1:
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.

Example 2:
Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.

Example 3:
Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.

Constraints:

1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4

Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and
conquer approach, which is more subtle.

---

Input: nums = [-2,1,-3,4,-1,2,1,-6,4]
*/

public class MaximumSubarrayV2 {
  public static int maxSubArray(int[] nums) {
    int maxSubArrSum = nums[0], subArrSum = 0;
    for (int num : nums) {
      subArrSum = Math.max(num, subArrSum + num);
      maxSubArrSum = Math.max(maxSubArrSum, subArrSum);
    }
    return maxSubArrSum;
  }

  public static void main(String[] args) {
    assert maxSubArray(new int[]{-1}) == -1;
    assert maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}) == 6;
    assert maxSubArray(new int[]{5, 4, -1, 7, 8}) == 23;
  }
}
