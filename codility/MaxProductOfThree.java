package codility;

import java.util.*;

/*-
A non-empty array A consisting of N integers is given. The product of triplet (P, Q, R) equates to
A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).

For example, array A such that:

  A[0] = -3
  A[1] = 1
  A[2] = 2
  A[3] = -2
  A[4] = 5
  A[5] = 6
contains the following example triplets:

(0, 1, 2), product is −3 * 1 * 2 = −6
(1, 2, 4), product is 1 * 2 * 5 = 10
(2, 4, 5), product is 2 * 5 * 6 = 60
Your goal is to find the maximal product of any triplet.

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A, returns the value of the maximal product of any triplet.

For example, given array A such that:

  A[0] = -3
  A[1] = 1
  A[2] = 2
  A[3] = -2
  A[4] = 5
  A[5] = 6
the function should return 60, as the product of triplet (2, 4, 5) is maximal.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [3..100,000];
each element of array A is an integer within the range [−1,000..1,000].

---

-3 -2 1 2 2 6 -> -3 -2 6 (l, l, r)
-3 -2 1 2 5 6 -> 2 5 6 (r, r, r)
-5 -3 -2 -2 -1 6 -> -5 -3 6 (l, l, r)

l l l
l l r (ok)
l r l
l r r
r l l (duplicate)
r l r
r r l
r r r (ok)

-1 0 1 -> (l, r, r)
-1 -1 -1 -> (r, r, r)
-1 1 1 -> (l, r, r)
-1 1 1 -> (l, r, r)
*/

public class MaxProductOfThree {
  public static int solution(int[] A) {
    Arrays.sort(A);
    return Math.max(
      A[A.length - 1] * A[A.length - 2] * A[A.length - 3],
      A[0] * A[1] * A[A.length - 1]
    );
  }

  public static void main(String[] args) {
    assert solution(new int[]{-1, 0, 1}) == 0;
    assert solution(new int[]{-1, 1, 1}) == -1;
    assert solution(new int[]{-1, -1, 1}) == 1;
    assert solution(new int[]{-1000, -1000, 0, 1000, 1000}) == 1_000_000_000;
    assert solution(new int[]{-1000, -1, 2, 1000, 1000}) == 2_000_000;
  }
}
