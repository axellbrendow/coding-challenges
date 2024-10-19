package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/koko-eating-bananas/description/

Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have
gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k
bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any
more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

Example 1:
Input: piles = [3,6,7,11], h = 8
0 .. highest number
Output: 4

Example 2:
Input: piles = [30,11,23,4,20], h = 5
Output: 30

Example 3:
Input: piles = [30,11,23,4,20], h = 6
Output: 23
 
Constraints:

1 <= piles.length <= 10^4
piles.length <= h <= 10^9
1 <= piles[i] <= 10^9

---

piles = [10], h = 5
bestEatingSpeed = 2

piles = [10], h = 4
bestEatingSpeed = 3

piles = [10], h = 3
bestEatingSpeed = 4

piles = [10], h = 2
bestEatingSpeed = 5

piles = [10], h = 1
bestEatingSpeed = 10

piles = [10, 20], h = 1
bestEatingSpeed = impossible
*/

public class KokoEatingBananasV2 {
  // piles = [30,11,23,4,20], h = 5
  public static int minEatingSpeed(int[] piles, int h) {
    Arrays.sort(piles); // [4,11,20,23,30]
    int left = 1, right = Arrays.stream(piles).max().getAsInt(); // 16, 30
    int bestEatingSpeed = right; // 30
    while (left <= right) {
      int eatingSpeed = left + (right - left) / 2; // 0 + (30 - 0) / 2 = 15
      long hoursNeeded = 0; // 8
      for (int i = 0; i < piles.length; i++) { // 5
        int bananas = piles[i]; // 0
        hoursNeeded += bananas / eatingSpeed;
        if (bananas % eatingSpeed > 0) hoursNeeded++;
      }
      if (hoursNeeded <= h) {
        bestEatingSpeed = Math.min(bestEatingSpeed, eatingSpeed);
        right = eatingSpeed - 1;
      } else {
        left = eatingSpeed + 1;
      }
    }
    return bestEatingSpeed;
  }

  public static void main(String[] args) {
    assert minEatingSpeed(new int[]{30,11,23,4,20}, 5) == 30;
    assert minEatingSpeed(new int[]{34392671,891616382,813261297}, 712127987) == 3;
    assert minEatingSpeed(new int[]{805306368,805306368,805306368}, 1000000000) == 3;
  }
}
