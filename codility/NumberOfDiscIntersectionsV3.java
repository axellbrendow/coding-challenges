package codility;

import java.util.*;

/*-
We draw N discs on a plane. The discs are numbered from 0 to N − 1. An array A of N non-negative integers,
specifying the radiuses of the discs, is given. The J-th disc is drawn with its center at (J, 0) and radius A[J].

We say that the J-th disc and K-th disc intersect if J ≠ K and the J-th and K-th discs have at least one common
point (assuming that the discs contain their borders).

The figure below shows discs drawn for N = 6 and A as follows:

  A[0] = 1
  A[1] = 5
  A[2] = 2
  A[3] = 1
  A[4] = 4
  A[5] = 0


There are eleven (unordered) pairs of discs that intersect, namely:

discs 1 and 4 intersect, and both intersect with all the other discs;
disc 2 also intersects with discs 0 and 3.
Write a function:

class Solution { public int solution(int[] A); }

that, given an array A describing N discs as explained above, returns the number of (unordered) pairs of
intersecting discs. The function should return −1 if the number of intersecting pairs exceeds 10,000,000.

Given array A shown above, the function should return 11, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [0..2,147,483,647].

---

A[1] = 5 (-4, 6)
A[0] = 1 (-1, 1) i
A[2] = 2 (0, 4)
A[4] = 4 (0, 8)
A[3] = 1 (2, 4)
A[5] = 0 (5, 5) j

5 + 2 + 2 + 2 = 11

https://www.youtube.com/watch?v=HV8tzIiidSw&pp=ygUZTnVtYmVyT2ZEaXNjSW50ZXJzZWN0aW9ucw%3D%3D

sorted:
start[] = -4 -1 +0 +0 +2 +5
end[]   = +1 +4 +4 +5 +6 +8

      o        c  c
o  o  o  c  o  c  o  c  c
-4 -1 +0 +1 +2 +4 +5 +6 +8
*/

public class NumberOfDiscIntersectionsV3 {
  public static int solution(int[] A) {
    long[] start = new long[A.length];
    long[] end = new long[A.length];
    for (int i = 0; i < A.length; i++) {
      start[i] = (long) i - A[i];
      end[i] = (long) i + A[i]; // converts i to long before adding
    }
    Arrays.sort(start);
    Arrays.sort(end);

    int pairs = 0, open = 0, i = 0;
    for (int j = 0; j < A.length; j++) {
      while (i < A.length && start[i] <= end[j]) {
        pairs += open++;
        if (pairs > 10_000_000) return -1;
        i++;
      }
      open--;
    }
    return pairs;
  }

  public static void main(String[] args) {
    assert solution(new int[]{0}) == 0;
    assert solution(new int[]{0, 0}) == 0;
    assert solution(new int[]{0, Integer.MAX_VALUE}) == 1;
    assert solution(new int[]{0, 1}) == 1;
    assert solution(new int[]{0, 0, 1, 2}) == 3;
  }
}
