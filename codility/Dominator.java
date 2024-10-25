package codility;

import java.util.*;

/*-
https://app.codility.com/programmers/lessons/8-leader/dominator/
https://codility.com/media/train/6-Leader.pdf

An array A consisting of N integers is given. The dominator of array A is the value that occurs in more than
half of the elements of A.

For example, consider array A such that

 A[0] = 3    A[1] = 4    A[2] =  3
 A[3] = 2    A[4] = 3    A[5] = -1
 A[6] = 3    A[7] = 3
The dominator of A is 3 because it occurs in 5 out of 8 elements of A (namely in those with indices 0, 2, 4,
6 and 7) and 5 is more than a half of 8.

Write a function

class Solution { public int solution(int[] A); }

that, given an array A consisting of N integers, returns index of any element of array A in which the dominator
of A occurs. The function should return −1 if array A does not have a dominator.

For example, given array A such that

 A[0] = 3    A[1] = 4    A[2] =  3
 A[3] = 2    A[4] = 3    A[5] = -1
 A[6] = 3    A[7] = 3
the function may return 0, 2, 4, 6 or 7, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].

---

2 -> 0 (expected in my algo) but could be 0 (index of the dominator)
2 1 -> -1 (expected in my algo) but could be -1
2 1 3 -> -1 (expected in my algo) but could be -1
3 3 3 2 2 1 -> -1 (expected in my algo) but could be -1
3 3 3 2 2 2 -> -1 (expected in my algo) but could be -1
3 3 3 3 2 2 2 -> 0 (expected in my algo) but could be 0, 1, 2 or 3
3 3 3 2 2 2 3 -> 6 (expected in my algo) but could be 0, 1, 2 or 6
3 3 3 2 2 2 2 -> 6 (expected in my algo) but could be 3, 4, 5 or 6
3 2 3 2 3 2 2 -> 6 (expected in my algo) but could be 1, 3, 5 or 6
2 2 3 2 3 2 3 -> 0 (expected in my algo) but could be 0, 1, 3 or 5
*/

public class Dominator {
  public static int solution(int[] A) {
    int dominator = -1, counter = 0;

    for (int i = 0; i < A.length; i++) {
      if (counter == 0) {
        dominator = i;
        counter = 1;
      } else if (A[dominator] == A[i]) counter++;
      else counter--;
    }

    if (counter == 0) return -1;

    counter = 0;
    for (int val : A)
      if (val == A[dominator]) counter++;

    if (counter > A.length / 2) return dominator;
    return -1;
  }

  public static void main(String[] args) {
    assert solution(new int[]{2}) == 0;
    assert solution(new int[]{2, 1}) == -1;
    assert solution(new int[]{2, 1, 3}) == -1;
    assert solution(new int[]{3, 3, 3, 2, 2, 1}) == -1;
    assert solution(new int[]{3, 3, 3, 2, 2, 2}) == -1;
    assert solution(new int[]{3, 3, 3, 3, 2, 2, 2}) == 0;
    assert solution(new int[]{3, 3, 3, 2, 2, 2, 3}) == 6;
    assert solution(new int[]{3, 3, 3, 2, 2, 2, 2}) == 6;
    assert solution(new int[]{3, 2, 3, 2, 3, 2, 2}) == 6;
    assert solution(new int[]{2, 2, 3, 2, 3, 2, 3}) == 0;
  }
}
