package myjava;

import java.util.*;
import java.util.stream.*;

/*-
https://leetcode.com/problems/top-k-frequent-elements/description/

Given an integer array nums and an integer k, return the k most frequent elements. You may return the
answer in any order.

Constraints:

1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.

arr = [1]
k = 1
output = [1]

arr = [1, 2, 2]
k = 1
output = [2]

arr = [1, 1, 1, 2, 2, 3]
k = 2
output = [1, 2]

arr = [1, 1, 1, 2, 2, 3]
{ 1: 3, 2: 2, 3: 1 }
*/

public class TopKFrequentElementsV1 {
  public static int[] topKFrequent(int[] nums, int k) {
    return Arrays.stream(nums)
      .boxed()
      .collect(Collectors.groupingBy(num -> num, Collectors.counting()))
      .entrySet()
      .stream()
      .sorted(
        (entry1, entry2) -> {
          if (entry1.getValue() > entry2.getValue()) return -1;
          if (entry1.getValue() == entry2.getValue()) return 0;
          return 1;
        }
      )
      .limit(k)
      .mapToInt(entry -> entry.getKey())
      .toArray();
  }

  public static void main(String[] args) {
    assert Arrays.equals(topKFrequent(new int[]{1,1,1,2,2,3}, 2), new int[]{1,2});
  }
}
