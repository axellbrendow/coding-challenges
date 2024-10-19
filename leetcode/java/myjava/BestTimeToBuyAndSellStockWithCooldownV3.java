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

public class BestTimeToBuyAndSellStockWithCooldownV3 {
  public static int maxProfit(int[] prices) {
    int buy = -prices[0];
    int sell = 0;
    int cooldown = 0;

    for (int i = 1; i < prices.length; i++) {
      int newBuy = Math.max(buy, cooldown - prices[i]);
      int newSell = Math.max(sell, buy + prices[i]);

      //update cooldown with current cooldown v/s previous sell
      //that's why new variables are used
      cooldown = Math.max(sell, cooldown);

      //update buy and sell with new values;
      buy = newBuy;
      sell = newSell;
    }

    return sell;
  }

  public static void main(String[] args) {
    assert maxProfit(new int[]{1}) == 0;
    assert maxProfit(new int[]{2, 1, 4}) == 3;
    assert maxProfit(new int[]{1, 2, 3, 0, 2}) == 3;
  }
}
