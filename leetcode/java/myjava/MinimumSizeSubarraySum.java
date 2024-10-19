package myjava;

/*-
https://leetcode.com/problems/minimum-size-subarray-sum/description/

Given an array of positive integers nums and a positive integer target, return the minimal length of a 
subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.

1 <= target <= 10^9
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^4

keywords in the problem:
  minimal -> binary search | dynamic programming | greedy
  subarray -> prefix sum | sliding window
  greater than or equal to target -> binary search

Desired time complexity in leetcode is O(n). If we create the prefix sum array, then we can calculate the sum
of each subarray in O(1) time. To make the solution O(n) we need to be able to give the answer by passing only
one more time in the array. Basic that means two pointers or sliding window. What's interesting about the
sliding window is that we can calculate the sum of the sliding window in O(1) time using the prefix sum array.
We can increase the sliding window while the sum is less than target. Then decrease the sliding window while
the sum is greater than or equal to target and repeat the process.

target = 7
nums = [2,3,1,2,4,3]
        i
        j

target = 4
nums = [1,4,4]

target = 11
nums = [1,1,1,1,1,1,1,1]
*/

public class MinimumSizeSubarraySum {
  public static int minSubArrayLen(int target, int[] nums) {
    int[] prefixSum = new int[nums.length];
    prefixSum[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      prefixSum[i] = prefixSum[i - 1] + nums[i];
    }

    int i = 0, j = 0, minSubArrLength = Integer.MAX_VALUE;
    while (j < nums.length && i < nums.length) {
      while (j < nums.length && prefixSum[j] - prefixSum[i] + nums[i] < target) j++;
      if (j >= nums.length) break;
      while (i < nums.length && prefixSum[j] - prefixSum[i] + nums[i] >= target) {
        minSubArrLength = Math.min(minSubArrLength, j - i + 1);
        i++;
      }
    }
    return minSubArrLength != Integer.MAX_VALUE ? minSubArrLength : 0;
  }

  public static void main(String[] args) {
    assert minSubArrayLen(1, new int[]{-1}) == 0;
    assert minSubArrayLen(1, new int[]{1}) == 1;
    assert minSubArrayLen(1, new int[]{2}) == 1;
    assert minSubArrayLen(7, new int[]{2,3,1,2,4,3}) == 2;
    assert minSubArrayLen(4, new int[]{1,4,4}) == 1;
    assert minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1}) == 0;
  }
}
