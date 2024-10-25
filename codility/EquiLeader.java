package codility;

/*-
https://app.codility.com/programmers/lessons/8-leader/equi_leader/
https://www.youtube.com/watch?v=CZHukXHrFu4&ab_channel=CodeTrading

A non-empty array A consisting of N integers is given.

The leader of this array is the value that occurs in more than half of the elements of A.

An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences A[0], A[1], ..., A[S] and
A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.

For example, given array A such that:

    A[0] = 4
    A[1] = 3
    A[2] = 4
    A[3] = 4
    A[4] = 4
    A[5] = 2
we can find two equi leaders:

0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
The goal is to count the number of equi leaders.

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A consisting of N integers, returns the number of equi leaders.

For example, given:

    A[0] = 4
    A[1] = 3
    A[2] = 4
    A[3] = 4
    A[4] = 4
    A[5] = 2
the function should return 2, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
*/

public class EquiLeader {
  public static int solution(int[] A) {
    int leader = -1, counter = 0;
    for (int i = 0; i < A.length; i++) {
      if (counter == 0) {
        leader = A[i];
        counter = 1;
      } else if (A[i] == leader) counter++;
      else counter--;
    }

    if (counter == 0) return 0;

    int leaderCount = 0;
    for (int val : A) if (val == leader) leaderCount++;

    if (leaderCount <= A.length / 2) return 0;

    int remainingLeaderCount = leaderCount, equiLeaders = 0;
    for (int i = 0; i < A.length - 1; i++) {
      int leftSize = i + 1, rightSize = A.length - i - 1;
      if (A[i] == leader) remainingLeaderCount--;
      if (
        leaderCount - remainingLeaderCount > leftSize / 2 && remainingLeaderCount > rightSize / 2
      ) equiLeaders++;
    }
    return equiLeaders;
  }

  public static void main(String[] args) {
    assert solution(new int[]{1}) == 0;
    assert solution(new int[]{1, 1}) == 1;
    assert solution(new int[]{1, 2}) == 0;
    assert solution(new int[]{1, 2, 2}) == 0;
    assert solution(new int[]{2, 2, 2}) == 2;
    assert solution(new int[]{2, 1, 2, 1, 2, 2}) == 3;
    assert solution(new int[]{1, 1, 2, 2, 2, 2}) == 1;
    assert solution(new int[]{2, 1, 2, 2, 2, 1}) == 2;
    assert solution(new int[]{2, 1, 2, 1, 3, 3}) == 0;
    assert solution(new int[]{1, 2, 2, 2, 2, 1, 1}) == 0;
  }
}
