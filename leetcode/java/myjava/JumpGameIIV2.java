package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/jump-game-ii/

You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at
nums[i], you can jump to any nums[i + j] where:

0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can
reach nums[n - 1].

Example 1:
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3
steps to the last index.

Example 2:
Input: nums = [2,3,0,1,4]
Output: 2

Constraints:

1 <= nums.length <= 10^4
0 <= nums[i] <= 1000
It's guaranteed that you can reach nums[n - 1].

---

0 2 8 3 0 4 8 4 8 3 0 2 8 2 9 4 0 3 8 3 0 3 2 3 2  (impossible)

---

BFS from starting position always creating the largest possible window of next positions you can jump to.
Why ?
- If the array has only non zero values, then it's clear that you should always jump the maximum
- If the array has zeros, it seems that if you jump the maximum, you could fall into a trap. But you are not
  forced to jump the maximum always. You just need to consider the range of positions that you can jump to:
    - Take this example: 5 0 0 0 0 0 0. Doesn't matter the jump length, you'll end up in a trap.
    - But if you had a value in the middle: 5 0 0 0 3 0 0. You don't necessarily need to jump 5 positions, now
      you can choose to jump only 4.
    - Basically, at every step of the BFS, we'll consider all the positions we can jump to. Like a sliding window:
      5 0 0 0 3 0 0
      i             (window stars i = 0, j = 0)
      j
      5 0 0 0 3 0 0
        i           (those are all positions we can jump to)
                j
    - Now, considering all values in this range, we wanna discover the next window of positions, which will
      include all possibilities: jumping the minimum you can (i = j + 1) or jumping the maximum you can.
    - This works because you can discard all jump possibilities within the window. If you don't do that, you
      would be jumping more times than needed:
        - Take this example: 2 1 3 0 0 4 0 0.
                               i j
          I could jump from 1 to 3. But that doesn't get me outside the current window. I will pass through
          3 eventually and discover that I can go even further. If I can't go further, then there's no solution.
        - Take this example: 2 4 1 0 0 5 0 0.
                               i j
          I could jump from 4 to 1. But why would I do that if I know 4 can reach all the positions 1 can reach ?

---

2 8 3 0 4 8 4 8 3 0 2 8 2 9 4 0 0 0 0 0 3 2 3 2
i
j

2 8 3 0 4 8 4 8 3 0 2 8 2 9 4 0 0 0 0 0 3 2 3 2
  i
    j

2 8 3 0 4 8 4 8 3 0 2 8 2 9 4 0 0 0 0 0 3 2 3 2
      i
                  j

2 8 3 0 4 8 4 8 3 0 2 8 2 9 4 0 0 0 0 0 3 2 3 2
                    i
                              j

2 8 3 0 4 8 4 8 3 0 2 8 2 9 4 0 0 0 0 0 3 2 3 2
                                i
                                            j

2 8 3 0 4 8 4 8 3 0 2 8 2 9 4 0 0 0 0 0 3 2 3 2
                                              i
                                              j

---

1 4 3 0 0 0 3 2 3 2
i
j

1 4 3 0 0 0 3 2 3 2
  i
  j

1 4 3 0 0 0 3 2 3 2
    i
          j

---

1 4 5 0 0 0 3 2 3 2

---

5 9 3 2 1 0 2 3 3 1 0 0
*/

public class JumpGameIIV2 {
  public static int jump(int[] nums) {
    int i = 0, j = 0, bfsDepth = 0;
    while (i <= j && j < nums.length - 1) {
      int maxJump = 0;
      for (int k = i; k <= j; k++) {
        maxJump = Math.max(maxJump, k + nums[k]);
      }
      i = j + 1;
      j = maxJump;
      bfsDepth++;
    }
    return bfsDepth;
  }

  public static void main(String[] args) {
    assert jump(new int[]{5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0}) == 3;
    assert jump(new int[]{2, 8, 3, 0, 4, 8, 4, 8, 3, 0, 2, 8, 2, 9, 4, 0, 0, 0, 0, 0, 3, 2, 3, 2}) == 5;
  }
}
