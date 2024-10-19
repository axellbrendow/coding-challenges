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
*/

public class TopKFrequentElementsV3 {
  public static int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Long> frequencyMap = Arrays.stream(nums)
      .boxed()
      .collect(Collectors.groupingBy(num -> num, Collectors.counting()));

    List<List<Map.Entry<Integer, Long>>> frequencyLists = new ArrayList<>(nums.length + 1);
    for (int i = 0; i < nums.length + 1; i++) {
      frequencyLists.add(new ArrayList<>());
    }

    for (var frequencyEntry : frequencyMap.entrySet()) {
      frequencyLists.get(frequencyEntry.getValue().intValue()).add(frequencyEntry);
    }

    var arr = new int[k];
    int arrIndex = 0;
    for (int i = frequencyLists.size() - 1; i >= 0; i--) {
      for (var frequencyEntry : frequencyLists.get(i)) {
        arr[arrIndex++] = frequencyEntry.getKey();
        if (arrIndex == k) return arr;
      }
    }
    throw new IllegalStateException();
  }

  public static void main(String[] args) {
    assert Arrays.equals(topKFrequent(new int[]{1,1,1,2,2,3}, 2), new int[]{1,2});
    assert Arrays.equals(topKFrequent(new int[]{1}, 1), new int[]{1});
  }
}
