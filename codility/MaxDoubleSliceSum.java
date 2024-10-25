package codility;

/*-
https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_double_slice_sum/
https://www.youtube.com/watch?v=dHVmlBKWRGk&ab_channel=DaveKirkwood

A non-empty array A consisting of N integers is given.

A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.

The sum of double slice (X, Y, Z) is the total of
A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].

For example, array A such that:

    A[0] = 3
    A[1] = 2
    A[2] = 6
    A[3] = -1
    A[4] = 4
    A[5] = 5
    A[6] = -1
    A[7] = 2
contains the following example double slices:

double slice (0, 3, 6), sum is 2 + 6 + 4 + 5 = 17,
double slice (0, 3, 7), sum is 2 + 6 + 4 + 5 − 1 = 16,
double slice (3, 4, 5), sum is 0.
The goal is to find the maximal sum of any double slice.

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A consisting of N integers, returns the maximal sum of any double slice.

For example, given:

    A[0] = 3
    A[1] = 2
    A[2] = 6
    A[3] = -1
    A[4] = 4
    A[5] = 5
    A[6] = -1
    A[7] = 2
the function should return 17, because no double slice of array A has a sum of greater than 17.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [3..100,000];
each element of array A is an integer within the range [−10,000..10,000].

---

1 1 1 == 0;
2 1 2 == 0;
2 1 2 9 == 2;
2 -2 -1 9 == -1;
2 -2 -1 9 7 == 8;

0  1  2  3   4  5  6  7   8   9  10 11  12 13 14 15 16 (indices)
2  2  1  -9  7  5  8  6  -3  -2  7  -9  6  3  0  0  -1 (values)
0  2  3  -6  7  12 20 26 23  21  28 19  25 28 28 28 27 (prefix sum from the left)
26 24 22 21  28 21 16 8   2   5  7   0  9  3  0  0   0 (prefix sum from the right)
*/

public class MaxDoubleSliceSum {
  public static int solution(int[] A) {
    int[] leftSum = new int[A.length];
    int[] rightSum = new int[A.length];
    for (int i = 1, j = A.length - 2; i < A.length - 1; i++, j--) {
      leftSum[i] = Math.max(0, Math.max(A[i], A[i] + leftSum[i - 1]));
      rightSum[j] = Math.max(0, Math.max(A[j], A[j] + rightSum[j + 1]));
    }
    int maxSum = 0;
    for (int i = 1; i < A.length - 1; i++)
      maxSum = Math.max(maxSum, leftSum[i - 1] + rightSum[i + 1]);
    return maxSum;
  }

  public static void main(String[] args) {
    assert solution(new int[]{1, 1, 1}) == 0;
    assert solution(new int[]{2, 1, 2}) == 0;
    assert solution(new int[]{-1, -1, -1, -1, -1, -1}) == 0;
    assert solution(new int[]{2, 1, 2, 9}) == 2;
    assert solution(new int[]{2, -2, -1, 9}) == 0;
    assert solution(new int[]{2, -2, -1, 9, 7}) == 9;
    assert solution(new int[]{0, 10, -5, -2, 0}) == 10;
  }
}
