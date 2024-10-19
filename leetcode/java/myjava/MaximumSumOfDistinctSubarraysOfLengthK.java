package myjava;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/*-
https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/

Constraints:

1 <= k <= nums.length <= 10^5
1 <= nums[i] <= 10^5
*/

public class MaximumSumOfDistinctSubarraysOfLengthK {
  public static long maximumSubarraySum(int[] nums, int k) {
      long maxSum = 0L, currSum = 0L;
      int i = 0, j = 0;
      Map<Integer, Integer> numToIndex = new HashMap<>();
      while (j < nums.length) {
          if (numToIndex.containsKey(nums[j])) {
              int prevIndex = numToIndex.get(nums[j]);
              int left = i;
              for (; left <= prevIndex; left++) {
                  numToIndex.remove(nums[left]);
                  currSum -= nums[left];
              }
              i = left;
          } else {
              numToIndex.put(nums[j], j);
              currSum += nums[j];
              if (j - i + 1 == k) {
                  maxSum = Math.max(maxSum, currSum);
                  currSum -= nums[i];
                  numToIndex.remove(nums[i]);
                  i++;
              }
              j++;
          }
      }
      return maxSum;
  }

  public static void main(String[] args) {
    assert maximumSubarraySum(new int[]{1,5,4,2,9,9,9}, 3) == 15L;
    assert maximumSubarraySum(new int[]{4,4,4}, 3) == 0L;
    assert maximumSubarraySum(new int[]{1,1,1,7,8,9}, 3) == 24L;
    assert maximumSubarraySum(new int[]{1,1,1,1,1,1}, 1) == 1L;
  }
}
