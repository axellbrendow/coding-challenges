package myjava;

/*-
https://leetcode.com/problems/maximum-subarray/description/

Given an array arr[], the task is to find the subarray that has the maximum sum and return its sum.

Examples:

Input: arr[] = {2, 3, -8, 7, -1, 2, 3}
Output: 11
Explanation: The subarray {7, -1, 2, 3} has the largest sum 11.

Input: arr[] = {-2, -4}
Output: –2
Explanation: The subarray {-2} has the largest sum -2.

Input: arr[] = {-4, -2}
Output: –2
Explanation: The subarray {-2} has the largest sum -2.

Input: arr[] = {5, 4, 1, 7, 8}
Output: 25
Explanation: The subarray {5, 4, 1, 7, 8} has the largest sum 25.

Input: arr[] = {5, 4, 1, -7, 8}
Output: 17
Explanation: The subarray {5, 4, 1, -7, 8} has the largest sum 17.
*/

public class MaximumSubarrayV1 {
  public static int maxSubarraySum(int[] arr) {
    int i = 0, sum = 0, maxSum = Integer.MIN_VALUE;
    while (i < arr.length) {
      sum += arr[i];
      maxSum = Math.max(maxSum, sum);
      if (sum < 0) sum = 0;
      i++;
    }
    return maxSum;
  }

  public static void main(String[] args) {
    assert maxSubarraySum(new int[]{2, 3, -8, 7, -1, 2, 3}) == 11;
    assert maxSubarraySum(new int[]{-2, -4}) == -2;
    assert maxSubarraySum(new int[]{-4, -2}) == -2;
    assert maxSubarraySum(new int[]{5, 4, 1, 7, 8}) == 25;
    assert maxSubarraySum(new int[]{5, 4, 1, -7, 8}) == 11;
  }
}
