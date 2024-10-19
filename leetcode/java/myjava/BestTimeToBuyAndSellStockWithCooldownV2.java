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
*/

public class BestTimeToBuyAndSellStockWithCooldownV2 {
  // if buy == 1, we are deciding the best day to buy
  // if buy == 0, we are deciding the best day to sell
  private static int maxProfit(int i, int buy, int[] prices, int[][] dp) {
    if (i >= prices.length) return 0;
    if (dp[i][buy] != -1) return dp[i][buy];
    if (buy == 1) { // Time to decide to buy or not
      return dp[i][buy] = Math.max(
        maxProfit(i + 1, 0, prices, dp) - prices[i], // Chose to buy, now try to sell
        maxProfit(i + 1, 1, prices, dp) // Try to buy at next position
      );
    } else { // Time to decide to sell or not
      return dp[i][buy] = Math.max(
        maxProfit(i + 2, 1, prices, dp) + prices[i], // Chose to sell, now try to buy again from idx + 2
        maxProfit(i + 1, 0, prices, dp) // Try to sell at next position
      );
    }
  }

  public static int maxProfit(int[] prices) {
    int[][] dp = new int[prices.length + 1][2];
    for (int[] line : dp) Arrays.fill(line, -1);
    return maxProfit(0, 1, prices, dp);
  }

  public static void main(String[] args) {
    assert maxProfit(new int[]{1}) == 0;
    assert maxProfit(new int[]{2, 1, 4}) == 3;
    assert maxProfit(new int[]{1, 2, 3, 0, 2}) == 3;
  }
}
