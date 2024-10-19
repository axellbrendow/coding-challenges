package myjava;

import java.util.*;
import java.util.stream.*;

/*-
https://leetcode.com/problems/sliding-window-maximum/description/
*/

public class SlidingWindowMaximumV2 {
  private static void addToDeque(Deque<Integer> deque, int value) {
    while (!deque.isEmpty() && deque.peekLast() < value) deque.pollLast();
    deque.offerLast(value);
  }

  public static int[] maxSlidingWindow(int[] nums, int k) {
    int[] result = new int[nums.length - k + 1];
    int resultCursor = 0;
    Deque<Integer> deque = new LinkedList<>();
    for (int i = 0; i < k; i++) addToDeque(deque, nums[i]);
    result[resultCursor++] = deque.peekFirst();
    for (int i = k; i < nums.length; i++) {
      addToDeque(deque, nums[i]);
      if (deque.peekFirst() == nums[i - k]) deque.pollFirst();
      result[resultCursor++] = deque.peekFirst();
    }
    return result;
  }

  public static void main(String[] args) {
    assert Arrays.equals(maxSlidingWindow(new int[]{10, 5, 2, 7, 8, 7}, 1), new int[]{10, 5, 2, 7, 8, 7});
    assert Arrays.equals(maxSlidingWindow(new int[]{10, 5, 2, 7, 8, 7}, 3), new int[]{10, 7, 8, 8});
  }
}
