package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/coin-change-ii/

You are given an integer array coins representing coins of different denominations and an integer
amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by
any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.

Example 1:
Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

Example 2:
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.

Example 3:
Input: amount = 10, coins = [10]
Output: 1

Constraints:

1 <= coins.length <= 300
1 <= coins[i] <= 5000
All the values of coins are unique.
0 <= amount <= 5000

          coins
          1 2 5
          coinIndex
          0 1 2
amount  0 1 1 1
amount  1 1 0 0
amount  2 2
amount  3
amount  4
amount  5
*/

public class CoinChangeII {
  public static int change(int amount, int[] coins, int[][] dp, int coinIndex) {
    if (amount == 0) return 1;
    if (amount < 0 || coinIndex >= coins.length) return 0;
    if (dp[amount][coinIndex] != -1) return dp[amount][coinIndex];
    return dp[amount][coinIndex] = (change(amount - coins[coinIndex], coins, dp, coinIndex) +
      change(amount, coins, dp, coinIndex + 1));
  }

  public static int change(int amount, int[] coins) {
    int[][] dp = new int[amount + 1][coins.length];
    for (int[] row : dp) Arrays.fill(row, -1);
    return change(amount, coins, dp, 0);
  }

  public static void main(String[] args) {
    assert change(5, new int[]{1, 2, 5}) == 4;
    assert change(3, new int[]{2}) == 0;
    assert change(10, new int[]{10}) == 1;
  }
}
