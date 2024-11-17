package codility;

import java.util.*;

/*-
A non-empty array A consisting of N integers is given.

A peak is an array element which is larger than its neighbours. More precisely, it is an index P such that
0 < P < N − 1 and A[P − 1] < A[P] > A[P + 1].

For example, the following array A:

    A[0] = 1
    A[1] = 5
    A[2] = 3
    A[3] = 4
    A[4] = 3
    A[5] = 4
    A[6] = 1
    A[7] = 2
    A[8] = 3
    A[9] = 4
    A[10] = 6
    A[11] = 2
has exactly four peaks: elements 1, 3, 5, and 10.

1 -> 2 4 9
3 -> 2 2 7
5 -> 4 2 5
10 -> 9 7 5

You are going on a trip to a range of mountains whose relative heights are represented by array A, as shown
in a figure below. You have to choose how many flags you should take with you. The goal is to set the maximum
number of flags on the peaks, according to certain rules.

6                               ^   
5    ^                              
4          ^     ^           /      
3       \/    \/          /         
2                      /           \
1 /                 \/              
0                                   
  0  1  2  3  4  5  6  7  8  9 10 11

Flags can only be set on peaks. What's more, if you take K flags, then the distance between any two flags
should be greater than or equal to K. The distance between indices P and Q is the absolute value |P − Q|.

For example, given the mountain range represented by array A, above, with N = 12, if you take:

two flags, you can set them on peaks 1 and 5;
three flags, you can set them on peaks 1, 5 and 10;
four flags, you can set only three flags, on peaks 1, 5 and 10.
You can therefore set a maximum of three flags in this case.

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A of N integers, returns the maximum number of flags that can be set on the
peaks of the array.

For example, the following array A:

    A[0] = 1
    A[1] = 5
    A[2] = 3
    A[3] = 4
    A[4] = 3
    A[5] = 4
    A[6] = 1
    A[7] = 2
    A[8] = 3
    A[9] = 4
    A[10] = 6
    A[11] = 2
the function should return 3, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..400,000];
each element of array A is an integer within the range [0..1,000,000,000].
*/

public class FlagsV1 {
  private static boolean canPlaceFlags(int numFlags, boolean[] peaks) {
    int remainingFlags = numFlags, lastFlagIndex = -1;
    for (int i = 0; i < peaks.length && remainingFlags > 0; i++)
      if (peaks[i] && (lastFlagIndex == -1 || i - lastFlagIndex >= numFlags)) {
        remainingFlags--;
        lastFlagIndex = i;
      }
    return lastFlagIndex != -1 && remainingFlags == 0;
  }

  public static int solution(int[] H) {
    boolean[] peaks = new boolean[H.length];
    for (int i = 1; i < H.length - 1; i++)
      peaks[i] = H[i - 1] < H[i] && H[i] > H[i + 1];

    int minFlags = 1, maxFlags = peaks.length, maxFlagsSolution = 0;
    while (minFlags <= maxFlags) {
      int flags = minFlags + (maxFlags - minFlags) / 2;
      if (canPlaceFlags(flags, peaks)) {
        maxFlagsSolution = flags;
        minFlags = flags + 1;
      } else maxFlags = flags - 1;
    }
    return maxFlagsSolution;
  }

  public static void main(String[] args) {
    assert solution(new int[]{1}) == 0;
    assert solution(new int[]{1, 1}) == 0;
    assert solution(new int[]{1, 1, 1}) == 0;
    assert solution(new int[]{1, 2, 3}) == 0;
    assert solution(new int[]{1, 3, 2}) == 1;
    assert solution(new int[]{1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2}) == 3;
  }
}
