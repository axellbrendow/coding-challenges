package codility;

import java.util.*;

/*-
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

public class MaxProductOfTree {
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
