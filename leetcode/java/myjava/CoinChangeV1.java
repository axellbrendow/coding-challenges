package myjava;

import java.util.Arrays;

/*-
https://leetcode.com/problems/coin-change/

You are given an integer array coins representing coins of different denominations and an integer amount
representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be
made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

Example 1:
Input: coins = 1,2,5],amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1

Example 3:
Input: coins = [1], amount = 0
Output: 0

Constraints:

1 <= coins.length <= 12
1 <= coins[i] <= 2^31 - 1
0 <= amount <= 10^4

---

amount = 2
coins = [1,2,3,4,5,6]

5 {
    5 {
        5
        2
        1
    }
    2 {
        5
        2
        1
    }
    1
}
2
1

amount = 100
coins = [7,9,33]
33 -> 33, 66, 99
34 = 9 * x + 7 * y
9 -> 9, 18, 27, 36, 45, 54, 63, *72, 81, 90, 99
7 -> 7, 14, 21, *28, 35, 42, 49, 56, 63, 70, 77, 84, 91, 98
four 7s eight 9s
*/

public class CoinChangeV1 {
  public static int coinChange(int[] coins, int amount) {
    Arrays.sort(coins);
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int currAmount = 1; currAmount <= amount; currAmount++) {
      for (int coin : coins) {
        if (currAmount - coin < 0) break;
        if (dp[currAmount - coin] == Integer.MAX_VALUE) continue;
        dp[currAmount] = Math.min(dp[currAmount], 1 + dp[currAmount - coin]);
      }
    }
    return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
  }

  public static void main(String[] args) {
    assert coinChange(new int[]{1}, 0) == 0;
    assert coinChange(new int[]{2}, 3) == -1;
    assert coinChange(new int[]{1, 2, 5}, 11) == 3;
    assert coinChange(new int[]{1,10,7,2}, 15) == 3;
    assert coinChange(new int[]{186, 419, 83, 408}, 6249) == 20;
  }
}
