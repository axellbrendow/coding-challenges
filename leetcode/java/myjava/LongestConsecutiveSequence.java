package myjava;

import java.util.*;
import java.util.stream.*;

/**-
https://leetcode.com/problems/longest-consecutive-sequence/description/

Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

Contraints:
0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9

---

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Idea 1 - Sorting:

Input: nums = [100,4,200,1,3,2]
Input: nums = [1,2,3,4,100,200] <- Sort
n -> nums.length
time complexity O(nlogn)
space complexity O(n)

Remember that Java's sort uses DualPivotQuicksort (unstable) for primitives and TimSort for objects (stable)

DualPivotQuicksort worst case:
  time complexity: O(nlogn)
  space complexity: O(n)
  Java's DualPivotQuicksort is a quicksort that has insertion sort inside (for small subarrays) and
  heapsort inside (for when time complexity is going quadratic)

TimSort worst case:
  time complexity: O(nlogn)
  space complexity: O(n)
  Java's TimSort is a hybrid merge sort and insertion sort

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9

[]
output: 0

[1]
output: 1

[1, 3]
output: 1

[1, 2]
output: 2

[2, 1]
output: 2

[-8, -3, 1, 3]
output: 1

Idea 2 - after reading an element, try to find all neighbors in a precomputed set:

[-8, -3, 1, 3, 2, -2, 0, 0, -1]
output: 7

{
-8: 1
-3: 1
-2: 1
-1: 1
0: 2
1: 1
2: 1
3: 1
}

[-8, 2, 1, -2, 3, -3, 0, 0, -1]
output: 7
*/

public class LongestConsecutiveSequence {
  public static int longestConsecutiveSequence(int[] nums) {
    int longest = 0;
    Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    for (int num : nums) {
      if (!set.contains(num)) continue;
      int currSize = 1;
      set.remove(num);
      for (int i = num + 1; set.contains(i); i++) {
        currSize++;
        set.remove(i);
      }
      for (int i = num - 1; set.contains(i); i--) {
        currSize++;
        set.remove(i);
      }
      longest = Math.max(longest, currSize);
    }

    return longest;
  }

  public static void main(String[] args) {
    assert longestConsecutiveSequence(new int[]{}) == 0;
    assert longestConsecutiveSequence(new int[]{0}) == 1;
    assert longestConsecutiveSequence(new int[]{0,0}) == 1;
    assert longestConsecutiveSequence(new int[]{100,4,200,1,3,2}) == 4;
    assert longestConsecutiveSequence(new int[]{0,3,7,2,5,8,4,6,0,1}) == 9;
  }
}
