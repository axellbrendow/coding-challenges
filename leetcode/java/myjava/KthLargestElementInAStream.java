package myjava;

/*-
https://leetcode.com/problems/kth-largest-element-in-a-stream/description/

You are part of a university admissions office and need to keep track of the kth highest test score
from applicants in real-time. This helps to determine cut-off marks for interviews and admissions
dynamically as new applicants submit their scores.

You are tasked to implement a class which, for a given integer k, maintains a stream of test scores and
continuously returns the kth highest test score after a new score has been submitted. More specifically,
we are looking for the kth highest score in the sorted list of all scores.

Implement the KthLargest class:

KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of test scores nums.
int add(int val) Adds a new test score val to the stream and returns the element representing the kth largest
element in the pool of test scores so far.

Example 1:
Input:
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
Output: [null, 4, 5, 5, 8, 8]
Explanation:
KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
kthLargest.add(3); // return 4
kthLargest.add(5); // return 5
kthLargest.add(10); // return 5
kthLargest.add(9); // return 8
kthLargest.add(4); // return 8

Example 2:
Input:
["KthLargest", "add", "add", "add", "add"]
[[4, [7, 7, 7, 7, 8, 3]], [2], [10], [9], [9]]
Output: [null, 7, 7, 7, 8]
Explanation:
KthLargest kthLargest = new KthLargest(4, [7, 7, 7, 7, 8, 3]);
kthLargest.add(2); // return 7
kthLargest.add(10); // return 7
kthLargest.add(9); // return 7
kthLargest.add(9); // return 8

Constraints:

0 <= nums.length <= 10^4
1 <= k <= nums.length + 1
-10^4 <= nums[i] <= 10^4
-10^4 <= val <= 10^4
At most 104 calls will be made to add.
*/

import java.util.*;

class KthLargest {
  private int k;
  private Queue<Integer> heap = new PriorityQueue<>();

  public KthLargest(int k, int[] nums) {
    this.k = k;
    for (int num : nums) heap.offer(num);
    while (heap.size() > k) heap.poll();
  }

  public int add(int val) {
    if (heap.size() < k) heap.offer(val);
    else if (val > heap.peek()) {
      heap.poll();
      heap.offer(val);
    }
    return heap.peek();
  }
}

public class KthLargestElementInAStream {
  public static void main(String[] args) {
    KthLargest kthLargest;

    kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
    assert kthLargest.add(3) == 4;
    assert kthLargest.add(5) == 5;
    assert kthLargest.add(10) == 5;
    assert kthLargest.add(9) == 8;
    assert kthLargest.add(4) == 8;

    kthLargest = new KthLargest(4, new int[]{7, 7, 7, 7, 8, 3});
    assert kthLargest.add(2) == 7;
    assert kthLargest.add(10) == 7;
    assert kthLargest.add(9) == 7;
    assert kthLargest.add(9) == 8;
  }
}