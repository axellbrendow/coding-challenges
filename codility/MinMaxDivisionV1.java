package codility;

import java.util.*;

/*-
You are given integers K, M and a non-empty array A consisting of N integers. Every element of the array is
not greater than M.

You should divide this array into K blocks of consecutive elements. The size of the block is any integer
between 0 and N. Every element of the array should belong to some block.

The sum of the block from X to Y equals A[X] + A[X + 1] + ... + A[Y]. The sum of empty block equals 0.

The large sum is the maximal sum of any block.

For example, you are given integers K = 3, M = 5 and array A such that:

  A[0] = 2
  A[1] = 1
  A[2] = 5
  A[3] = 1
  A[4] = 2
  A[5] = 2
  A[6] = 2
The array can be divided, for example, into the following blocks:

[2, 1, 5, 1, 2, 2, 2], [], [] with a large sum of 15;
[2], [1, 5, 1, 2], [2, 2] with a large sum of 9;
[2, 1, 5], [], [1, 2, 2, 2] with a large sum of 8;
[2, 1], [5, 1], [2, 2, 2] with a large sum of 6.
The goal is to minimize the large sum. In the above example, 6 is the minimal large sum.

Write a function:

class Solution { public int solution(int K, int M, int[] A); }

that, given integers K, M and a non-empty array A consisting of N integers, returns the minimal large sum.

For example, given K = 3, M = 5 and array A such that:

  A[0] = 2
  A[1] = 1
  A[2] = 5
  A[3] = 1
  A[4] = 2
  A[5] = 2
  A[6] = 2
the function should return 6, as explained above.

Write an efficient algorithm for the following assumptions:

N and K are integers within the range [1..100,000];
M is an integer within the range [0..10,000];
each element of array A is an integer within the range [0..M].

---

K : num of blocks
M : max value

K = 2 M = 10 A = [1] -> 1
K = 3 M = 10 A = [1] -> 1
K = 100_000 M = 10 A = [1] -> 1

2, 1|, 5, 1,| 2, 2, 2
2, 3,| 8, 9, | 11, 13, 15

2  1  8  3  9  9  9  9
2  3  11 14| 23 32| 41 50
27
21
18

This code is wrong and only works for K = 3. But I liked the code anyway and I'll keep it.
*/

class MinMaxDivisionV1 {
  public static int solution(int K, int M, int[] A) {
    int[] prefixSum = new int[A.length];
    prefixSum[0] = A[0];
    for (int i = 1; i < A.length; i++) prefixSum[i] = prefixSum[i - 1] + A[i];

    int minLargeSum = Integer.MAX_VALUE;
    for (int i = 0; i < A.length; i++) {
      int originalLeft = i + 1, originalRight = A.length - 1;
      int left = i + 1, right = A.length - 1;
      while (left <= right) {
        int mid = left + (right - left) / 2;
        int leftSum = prefixSum[mid] - prefixSum[originalLeft - 1];
        int rightSum = prefixSum[originalRight] - prefixSum[mid];
        minLargeSum = Math.min(minLargeSum, Math.max(prefixSum[i], Math.max(leftSum, rightSum)));
        if (leftSum == rightSum) break;
        else if (leftSum < rightSum) left = mid + 1;
        else right = mid - 1;
      }
    }
    return minLargeSum;
  }

  public static void main(String[] args) {
    assert solution(3, Integer.MAX_VALUE, new int[]{2, 1, 5, 1, 2, 2, 2}) == 6;
    assert solution(3, Integer.MAX_VALUE, new int[]{2, 1, 8, 3, 9, 9, 9, 9}) == 18;
  }
}
