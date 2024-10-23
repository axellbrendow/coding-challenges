package codility;

import java.util.*;
import java.util.stream.*;

/*-
Write a function:

class Solution { public int solution(int[] A); }

that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [1, 2, 3], the function should return 4.

Given A = [−1, −3], the function should return 1.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].

---

N = 5
1 2 3 4 5 -> 6
0 1 2 3 4 -> 5
0 1 2 3 5 -> 4
*/

public class MissingInteger {
  public static int solution(int[] A) {
    Set<Integer> set = Arrays.stream(A).boxed().collect(Collectors.toSet());
    for (int i = 1; i <= A.length; i++) {
      if (!set.contains(i)) return i;
    }
    return A.length + 1;
  }

  public static void main(String[] args) {
    assert solution(new int[]{1, 2, 3}) == 4;
    assert solution(new int[]{0, 1, 2}) == 3;
    assert solution(new int[]{1, 2, 4}) == 3;
  }
}
