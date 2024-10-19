package myjava;

/*-
https://leetcode.com/problems/longest-common-subsequence/description/

Given two strings text1 and text2, return the length of their longest common subsequence. If there is no
common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none)
deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

Example 1:
Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:
Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.

Example 3:
Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.

Example 4:
Input: text1 = "abede", text2 = "ace"
Output: 2
Explanation: The longest common subsequence is "ae" and its length is 3.

Input: text1 = "abeace", text2 = "ace"

Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.

---


*/

public class LongestCommonSubsequence {
  public static int longestCommonSubsequence(String text1, String text2, Integer[][] dp, int i, int j) {
    if (i < 0 || j < 0 || i >= text1.length() || j >= text2.length()) return 0;
    if (dp[i][j] != null) return dp[i][j];
    if (text1.charAt(i) == text2.charAt(j)) {
      return dp[i][j] = 1 + longestCommonSubsequence(text1, text2, dp, i + 1, j + 1);
    }
    return dp[i][j] = Math.max(
      longestCommonSubsequence(text1, text2, dp, i, j + 1),
      longestCommonSubsequence(text1, text2, dp, i + 1, j)
    );
  }

  public static int longestCommonSubsequence(String text1, String text2) {
    Integer[][] dp = new Integer[text1.length()][text2.length()];
    return longestCommonSubsequence(text1, text2, dp, 0, 0);
  }

  public static void main(String[] args) {
    assert longestCommonSubsequence("abcde", "ace") == 3;
    assert longestCommonSubsequence("abc", "abc") == 3;
    assert longestCommonSubsequence("abc", "def") == 0;
    assert longestCommonSubsequence("psnw", "vozsh") == 1;
  }
}
