package codility;

import java.util.*;

/*-
https://app.codility.com/programmers/lessons/14-binary_search_algorithm/min_max_division/
https://www.youtube.com/watch?v=0sU_XAaoaEM&ab_channel=kination

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

As the question says "Every element of the array should belong to some block.", the minimal large sum is at least
the biggest element in the array and at most the sum of the entire array (e.g. K = 1). Basically we need to find
a value that splits the array into K blocks successfully.

2 1 5 1 2 2 2
5 <= minimal large sum <= 15

If K = 3 and we try minimalLargeSum = 5, we'll get 4 divisions -> 2 1 | 5 | 1 2 2 | 2
If K = 3 and we try minimalLargeSum = 15, we'll get 0 divisions -> 2 1 5 1 2 2 2
If K = 3 and we try minimalLargeSum = 10, we'll get 2 divisions -> 2 1 5 1 | 2 2 2

Basically after trying minimalLargeSum = 10 we know any value greater than 10 will not be valid, that's where
binary search shines

---

5 3
5 <= minimal large sum <= 8

If K = 3, It's impossible to divide this array into 3 blocks with at least 1 element, so, in this case, any
number of divisions < 3 is acceptable because we can imagine that all other divisions will be starting and
ending after the last index of the array.

If K = 3 and we try minimalLargeSum = 6, we'll get 2 divisions -> 5 | 3 (note that this is a valid solution !!)
*/

class MinMaxDivisionV2 {
  public static int solution(int numBlocks, int maxElem, int[] A) {
    int maxValue = A[0], sum = 0;
    for (int value : A) {
      maxValue = Math.max(maxValue, value);
      sum += value;
    }

    int minLargeSum = sum, left = maxValue, right = sum;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      int splits = 1, currSum = 0;
      for (int value : A) {
        if (currSum + value > mid) {
          currSum = value;
          splits++;
          if (splits > numBlocks) break;
        } else currSum += value;
      }
      // splits < K is a valid solution when it's not possible to divide the array into K blocks where all
      // K blocks have at least 1 element
      if (splits <= numBlocks) {
        minLargeSum = mid;
        right = mid - 1;
      } else left = mid + 1;
    }
    return minLargeSum;
  }

  public static void main(String[] args) {
    assert solution(3, 5, new int[]{5, 3}) == 5;
    assert solution(3, 5, new int[]{2, 1, 5, 1, 2, 2, 2}) == 6;
    assert solution(3, 9, new int[]{2, 1, 8, 3, 9, 9, 9, 9}) == 18;
  }
}
