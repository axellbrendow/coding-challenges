package codility;

import java.util.*;

/*-
https://codility.com/media/train/solution-min-abs-sum.pdf
https://app.codility.com/programmers/lessons/17-dynamic_programming/min_abs_sum/

Similar to Minimum Subset Sum Difference: https://www.youtube.com/watch?v=FB0KUhsxXGY&ab_channel=Techdose

For a given array A of N integers and a sequence S of N integers from the set {−1, 1},
we define val(A, S) as follows:

val(A, S) = |sum{ A[i]*S[i] for i = 0..N−1 }|

(Assume that the sum of zero elements equals zero.)

For a given array A, we are looking for such a sequence S that minimizes val(A,S).

Write a function:

class Solution { public int solution(int[] A); }

that, given an array A of N integers, computes the minimum value of val(A,S) from all possible values of
val(A,S) for all possible sequences S of N integers from the set {−1, 1}.

For example, given array:

  A[0] =  1
  A[1] =  5
  A[2] =  2
  A[3] = -2
your function should return 0, since for S = [−1, 1, −1, 1], val(A, S) = 0, which is the minimum possible value.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..20,000];
each element of array A is an integer within the range [−100..100].

---

0   1   2   3 (indices)
1   5   2   -2 (values)

|1*1 + 5*1 + 2*1 + -2*1| = |6| = 6
|1*1 + 5*-1 + 2*1 + -2*-1| = |1-5+2+2| = |0| = 0
*/

public class MinAbsSumV1 {
  public static int solution(int[] A) {
    int sum = 0;
    for (int i = 0; i < A.length; i++) {
      int absolute = Math.abs(A[i]);
      A[i] = absolute;
      sum += absolute;
    }

    // if dp[sum] = true, then it's possible to create this sum using elements from the array
    boolean[] dp = new boolean[sum + 1];
    dp[0] = true;
    for (int i = 0; i < A.length; i++)
      for (int currSum = sum; currSum >= 0; currSum--)
        if (dp[currSum] && currSum + A[i] <= sum)
          dp[currSum + A[i]] = true;

    int result = sum;
    for (int currSum = 0; currSum <= sum / 2; currSum++)
      if (dp[currSum])
        result = Math.min(result, (sum - currSum) - currSum);
    return result;
  }

  public static void main(String[] args) {
    assert solution(new int[]{1, 5, 2, -2}) == 0;
  }
}
