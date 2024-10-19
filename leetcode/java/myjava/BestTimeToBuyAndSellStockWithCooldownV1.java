package myjava;

import java.util.*;

/*-
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and
sell one share of the stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you
buy again).

Example 1:
Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]

Example 2:
Input: prices = [1]
Output: 0

Constraints:

1 <= prices.length <= 5000
0 <= prices[i] <= 1000

---

8 7 3 9 0 8 2 7 8 1 7 2 3 6 4 8 2 5 1 6 4 8
right to left
bought stock
you are in a cooldown

2 1 4
*/

public class BestTimeToBuyAndSellStockWithCooldownV1 {
  public static int maxProfit(int[] prices) {
    int[] dp = new int[prices.length];
    for (int i = prices.length - 2; i >= 0; i--) {
      dp[i] = dp[i + 1];
      for (int j = i + 1; j < prices.length; j++) {
        dp[i] = Math.max(dp[i], prices[j] - prices[i] + (j + 2 < prices.length ? dp[j + 2] : 0));
      }
    }
    return dp[0];
  }

  public static void main(String[] args) {
    assert maxProfit(new int[]{1}) == 0;
    assert maxProfit(new int[]{2, 1, 4}) == 3;
    assert maxProfit(new int[]{1, 2, 3, 0, 2}) == 3;
  }
}
