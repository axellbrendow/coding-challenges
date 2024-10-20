package codility;

/*-
https://app.codility.com/programmers/lessons/5-prefix_sums/min_avg_two_slice/

A non-empty array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P < Q < N, is
called a slice of array A (notice that the slice contains at least two elements). The average of a slice (P, Q)
is the sum of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice. To be precise, the average equals
(A[P] + A[P + 1] + ... + A[Q]) / (Q - P + 1).

For example, array A such that:
    A[0] = 4
    A[1] = 2
    A[2] = 2
    A[3] = 5
    A[4] = 1
    A[5] = 5
    A[6] = 8
contains the following example slices:

slice (1, 2), whose average is (2 + 2) / 2 = 2;
slice (3, 4), whose average is (5 + 1) / 2 = 3;
slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
The goal is to find the starting position of a slice whose average is minimal.

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A consisting of N integers, returns the starting position of the slice with the
minimal average. If there is more than one slice with a minimal average, you should return the smallest
starting position of such a slice.

For example, given array A such that:
    A[0] = 4
    A[1] = 2
    A[2] = 2
    A[3] = 5
    A[4] = 1
    A[5] = 5
    A[6] = 8
the function should return 1, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [2..100,000];
each element of array A is an integer within the range [-10,000..10,000].

---

Optimized solution:
time: O(N)

This question seems to come from Mathematical Olympiads or Competitive Programming. It requires you to prove
that if you divide and array into two subarrays, one of them will have an average <= the original array.

http://disq.us/p/1bcgo23
https://github.com/daotranminh/playground/blob/master/src/codibility/MinAvgTwoSlice/proof.pdf

Consider a non-empty array A = A_1, A_2, ..., A_N and an integer 1 < i < N. We consider the average of A and
its two sub-arrays A_1, ..., A_i and A_i+1, ..., A_N.

Let:
AVG_{1,N} = (A_1 + ... + A_N) / N

AVG_{1,i} = (A_1 + ... + A_i) / i

AVG_{i+1,N} = (A_[i+1] + ... + A_N) / (N - i)

We claim that either AVG_{1,i} <= AVG_{1,N} OR AVG_{i+1,N} <= AVG_{1,N}. Indeed, assume the opposite, that is,
AVG_{1,i} > AVG_{1,N}
AND
AVG_{i+1,N} > AVG_{1,N}.

Now, expanding the inequation AVG_{1,i} > AVG_{1,N}

(A_1 + ... + A_i) / i > (A_1 + ... + A_N) / N

(1) Multiply by i * N both sides

(A_1 + ... + A_i) * N > (A_1 + ... + A_N) * i

---------------------------------

Now, expanding the other inequation AVG_{i+1,N} > AVG_{1,N}

(A_[i+1] + ... + A_N) / (N - i) > (A_1 + ... + A_N) / N

(2) Multiply by N * (N - i) both sides

(A_[i+1] + ... + A_N) * N > (A_1 + ... + A_N) * (N - i)

---------------------------------

Summing inequations (1) and (2)

(A_1 + ... + A_i) * N +
(A_[i+1] + ... + A_N) * N
>
(A_1 + ... + A_N) * i +
(A_1 + ... + A_N) * (N - i)

(A_1 + ... + A_i + A_[i+1] + ... + A_N) * N >
(A_1 + ... + A_N) * (i + (N - i))

(A_1 + ... + A_N) * N > (A_1 + ... + A_N) * N

And that's a contradiction

---

https://math.stackexchange.com/a/2682384

Let a,b,c,d be the four consecutive numbers in the sequence. Then their average is
                a + b   c + d
                ───── + ─────
a + b + c + d     2       2  
───────────── = ─────────────
      4               2      
In other words, the average of the four numbers is equal to the average of (a + b) / 2 and (c + d) / 2.
The average of a collection is always a value between the minimum and the maximum value of the collection.
Example:
avg(2, 3) = 2.5
avg(2, -2) = 0 (the average is always a value in the middle)
avg(2, -3) = -1 / 2 = -0.5
So it is guaranteed that:
avg(a,b) <= avg(a,b,c,d) <= avg(c,d)
OR
avg(c,d) <= avg(a,b,c,d) <= avg(a,b)

If you keep applying this process recursively, you'll notice there's always a sub-slice of length 2 that has
an average <= all the other sub-slices.

---

As the question forces P < Q, we need to consider only slices with at least 2 elements. This also forces us to
consider the slices of length 3 as unbreakable.
*/

public class MinAvgTwoSliceV2 {
  public static int solution(int[] A) {
    double minAvg = (A[0] + A[1]) / ((double) 2);
    int minAvgIndex = 0;
    for (int i = 0; i < A.length - 1; i++) {
      if ((A[i] + A[i + 1]) / ((double) 2) < minAvg) {
        minAvg = (A[i] + A[i + 1]) / ((double) 2);
        minAvgIndex = i;
      }
      if (i + 2 < A.length && (A[i] + A[i + 1] + A[i + 2]) / ((double) 3) < minAvg) {
        minAvg = (A[i] + A[i + 1] + A[i + 2]) / ((double) 3);
        minAvgIndex = i;
      }
    }
    return minAvgIndex;
  }

  public static void main(String[] args) {
    assert solution(new int[]{1, 1}) == 0;
    assert solution(new int[]{3, 2, 1}) == 1;
    assert solution(new int[]{4, 2, 2, 5, 1, 5, 8}) == 1;
  }
}
