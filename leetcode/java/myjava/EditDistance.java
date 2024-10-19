package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/edit-distance/description/

Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character -> i + 1, j + 1
Delete a character -> i, j
Replace a character -> i + 1, j + 1

Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

Example 2:
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')

Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.

---

w1 abc*
w2 abcd
*/

public class EditDistance {
  private static int minDistance(String word1, String word2, int[][] dp, int i, int j) {
    if (i == word1.length() && j == word2.length()) return 0;
    if (
      0 <= i && i <= word1.length() &&
        0 <= j && j <= word2.length() &&
        dp[i][j] != Integer.MAX_VALUE
    ) return dp[i][j];
    if (i == word1.length()) { // adding a character to word1
      return dp[i][j] = word2.length() - j;
    }
    if (j == word2.length()) { // removing a character from word1
      return dp[i][j] = word1.length() - i;
    }
    if (word1.charAt(i) == word2.charAt(j)) {
      return dp[i][j] = minDistance(word1, word2, dp, i + 1, j + 1);
    }
    return dp[i][j] = Math.min(
      1 + minDistance(word1, word2, dp, i, j + 1), // insert a character
      Math.min(
        1 + minDistance(word1, word2, dp, i + 1, j), // delete a character
        1 + minDistance(word1, word2, dp, i + 1, j + 1) // replace a character
      )
    );
  }

  public static int minDistance(String word1, String word2) {
    int[][] dp = new int[word1.length() + 1][word2.length() + 1];
    for (final var row : dp) Arrays.fill(row, Integer.MAX_VALUE);
    return minDistance(word1, word2, dp, 0, 0);
  }

  public static void main(String[] args) {
    assert minDistance("horse", "ros") == 3;
    assert minDistance("intention", "execution") == 5;
  }
}
