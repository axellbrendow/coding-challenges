package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/description/

You are given an integer array nums and an integer k. You want to find a subsequence of nums of length k that
has the largest sum.

Return any such subsequence as an integer array of length k.

A subsequence is an array that can be derived from another array by deleting some or no elements without
changing the order of the remaining elements.

1 <= nums.length <= 1000
-10^5 <= nums[i] <= 10^5
1 <= k <= nums.length

---

input = 0, 0, -1, 1, 2, 4
k = 3
output = 1, 2, 4

input = 0, 0, -1, 1, 2, 4
k = 2
output = 2, 4
algorithm states:
output = -inf, -inf
output = -inf, 0
output = 0, 0
output = 0, 0
output = 0, 1
output = 1, 2
output = 2, 4

restricted capacity queue
*/

import java.util.*;
import java.util.stream.*;

// A SUBSEQUENCE IS NOT A SUBARRAY !!!

public class FindSubsequenceOfLengthKWithTheLargestSum {
  public static int[] maxSubsequence(int[] nums, int k) {
    Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(index -> nums[index]));
    for (int i = 0; i < nums.length; i++) {
      queue.offer(i);
      if (queue.size() > k) {
        queue.poll();
      }
    }
    return queue.stream().sorted().mapToInt(index -> nums[index]).toArray();
  }

  public static void main(String[] args) {
    assert Arrays.equals(maxSubsequence(new int[]{7}, 1), new int[]{7});
    assert Arrays.equals(maxSubsequence(new int[]{7,10}, 1), new int[]{10});
    assert Arrays.equals(maxSubsequence(new int[]{2,1,3,3}, 2), new int[]{3,3});
    assert Arrays.equals(maxSubsequence(new int[]{-1,-2,3,4}, 3), new int[]{-1,3,4});
    assert Arrays.equals(maxSubsequence(new int[]{3,4,3,3}, 2), new int[]{4,3});
    assert Arrays.equals(maxSubsequence(new int[]{50,-75}, 2), new int[]{50,-75});
    // assert Arrays.equals(maxSubsequence(new int[]{1, 30, 5, 40, 7, 7, 100}, 3), new int[]{7, 7, 100});
  }
}
