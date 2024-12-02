package myjava;

import java.util.*;

/*-
Consider an array of n ticket prices, tickets. A number, m, is defined as the size of some
subsequence of tickets, s, where each element covers an unbroken range of integers. If the
elements in s are sorted, the absolute difference between any elements j and j + 1 is either 0 or 1.
Determine the maximum length of a subsequence chosen from the tickets array.

Example
tickets = [8, 5, 4, 8, 4]
Valid subsequences, sorted, are {4, 4, 5} and {8, 8}. These subsequences have m values of 3 and
2, respectively. Return 3.

Function Description
Complete the function maxTickets in the editor below.
maxTickets has the following parameter(s):
int tickets[n]: the ticket prices

Returns
int: the maximum possible value of m

Constraints
1 ≤ n ≤ 10^5
1 ≤ tickets[i] ≤ 10^9
*/

public class PickingTickets {
  public static int maxTickets(int[] tickets) {
    Arrays.sort(tickets);
    int maxTickets = 1, currSize = 1;
    for (int i = 1; i < tickets.length; i++) {
      if (tickets[i] - tickets[i - 1] <= 1) {
        currSize++;
        maxTickets = Math.max(maxTickets, currSize);
      } else {
        currSize = 1;
      }
    }
    return maxTickets;
  }

  public static void main(String[] args) {
    assert maxTickets(new int[]{1}) == 1;
    assert maxTickets(new int[]{1, 1}) == 2;
    assert maxTickets(new int[]{2, 1}) == 2;
    assert maxTickets(new int[]{3, 1}) == 1;
    assert maxTickets(new int[]{3, 3, 1}) == 2;
    assert maxTickets(new int[]{8, 5, 4, 8, 4}) == 3;
    assert maxTickets(new int[]{3, 3, 4, 5, 1, 1, 1}) == 4;
  }
}
