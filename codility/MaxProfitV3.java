package codility;

import java.util.*;

/*-
https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_profit/

An array A consisting of N integers is given. It contains daily prices of a stock share for a period of
N consecutive days. If a single share was bought on day P and sold on day Q, where 0 ≤ P ≤ Q < N, then
the profit of such transaction is equal to A[Q] − A[P], provided that A[Q] ≥ A[P]. Otherwise, the
transaction brings loss of A[P] − A[Q].

For example, consider the following array A consisting of six elements such that:

  A[0] = 23171
  A[1] = 21011
  A[2] = 21123
  A[3] = 21366
  A[4] = 21013
  A[5] = 21367
If a share was bought on day 0 and sold on day 2, a loss of 2048 would occur because A[2] − A[0] =
21123 − 23171 = −2048. If a share was bought on day 4 and sold on day 5, a profit of 354 would occur
because A[5] − A[4] = 21367 − 21013 = 354. Maximum possible profit was 356. It would occur if a share
was bought on day 1 and sold on day 5.

Write a function,

class Solution { public int solution(int[] A); }

that, given an array A consisting of N integers containing daily prices of a stock share for a period of
N consecutive days, returns the maximum possible profit from one transaction during this period. The
function should return 0 if it was impossible to gain any profit.

For example, given array A consisting of six elements such that:

  A[0] = 23171
  A[1] = 21011
  A[2] = 21123
  A[3] = 21366
  A[4] = 21013
  A[5] = 21367
the function should return 356, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..400,000];
each element of array A is an integer within the range [0..200,000].

---

{} == 0;
{1} == 0;
{1,2} == 1;
{2,1} == 0;
{1,1,1} == 0;
{1,1,2} == 1;
{1,2,3} == 2;
{1,2,3,4,5,1} == 4;
{1,2,3,4,5,1,9} == 8;
{7,9,3,4,5,10,90} == 8;
7
7 9
3
3 4
3 4 5
3 4 5 10
3 4 5 10 90

{7,9,3,4,5,1,10,90} == 8;
7
7 9
3
3 4
3 4 5
1
1 10
1 10 90

{7,9,3,5,6,4,10,90} == 8;
7
7 9
3
3 5
3 5 6
3 4
3 4 10
3 4 10 90

{3,5,6,4,10,90,7,9} == 8;
3
3 5
3 5 6
3 4
3 4 10
3 4 10 90
3 4 7
3 4 7 9
*/

public class MaxProfitV3 {
  public static int solution(int[] A) {
    int maxProfit = 0, minValue = Integer.MAX_VALUE;
    for (int i = 0; i < A.length; i++) {
      if (minValue != Integer.MAX_VALUE && A[i] <= minValue) minValue = Integer.MAX_VALUE;
      if (minValue != Integer.MAX_VALUE) maxProfit = Math.max(maxProfit, A[i] - minValue);
      else minValue = Math.min(minValue, A[i]);
    }
    return maxProfit;
  }

  public static void main(String[] args) {
    assert solution(new int[]{}) == 0;
    assert solution(new int[]{1}) == 0;
    assert solution(new int[]{1, 2}) == 1;
    assert solution(new int[]{2, 1}) == 0;
    assert solution(new int[]{1, 1, 1}) == 0;
    assert solution(new int[]{1, 1, 2}) == 1;
    assert solution(new int[]{1, 2, 3}) == 2;
    assert solution(new int[]{1, 2, 3, 4, 5, 1}) == 4;
    assert solution(new int[]{1, 2, 3, 4, 5, 1, 9}) == 8;
    assert solution(new int[]{7, 9, 3, 4, 5, 10, 90}) == 90 - 3;
    assert solution(new int[]{7, 9, 3, 4, 5, 1, 10, 90}) == 90 - 1;
    assert solution(new int[]{7, 9, 3, 5, 6, 4, 10, 90}) == 90 - 3;
    assert solution(new int[]{3, 5, 6, 4, 10, 90, 7, 9}) == 90 - 3;
  }
}
