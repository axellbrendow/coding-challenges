package myjava;

import java.util.*;
import java.util.stream.*;

/*-
https://leetcode.com/problems/sliding-window-maximum/description/
*/

public class SlidingWindowMaximumV1 {
  private static record ValueAndIndex(int value, int index) {
  }

  public static int[] maxSlidingWindow(int[] nums, int k) {
    int[] result = new int[nums.length - k + 1];
    int resultCursor = 0;
    Queue<ValueAndIndex> heap = new PriorityQueue<>(
      (valueAndIndex1, valueAndIndex2) -> valueAndIndex2.value() - valueAndIndex1.value()
    );
    for (int i = 0; i < k; i++) heap.offer(new ValueAndIndex(nums[i], i));
    result[resultCursor++] = heap.peek().value();
    for (int i = k; i < nums.length; i++) {
      heap.offer(new ValueAndIndex(nums[i], i));
      while (heap.peek().index() <= i - k) heap.poll();
      result[resultCursor++] = heap.peek().value();
    }
    return result;
  }

  public static void main(String[] args) {
    assert Arrays.equals(maxSlidingWindow(new int[]{10, 5, 2, 7, 8, 7}, 1), new int[]{10, 5, 2, 7, 8, 7});
    assert Arrays.equals(maxSlidingWindow(new int[]{10, 5, 2, 7, 8, 7}, 3), new int[]{10, 7, 8, 8});
  }
}
