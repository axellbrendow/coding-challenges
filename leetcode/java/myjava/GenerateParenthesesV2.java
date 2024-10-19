package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/generate-parentheses/description/

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example 1:
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

Example 2:
Input: n = 1
Output: ["()"]

Constraints:
1 <= n <= 8
*/

public class GenerateParenthesesV2 {
  public static void generateParentheses(List<String> strs, int n, int openCount, int closeCount, String str) {
    if (openCount + closeCount == n * 2) strs.add(str);
    else {
      if (openCount < n) generateParentheses(strs, n, openCount + 1, closeCount, str + "(");
      if (closeCount < openCount) generateParentheses(strs, n, openCount, closeCount + 1, str + ")");
    }
  }

  public static List<String> generateParenthesis(int n) {
    List<String> strs = new ArrayList<>();
    generateParentheses(strs, n, 0, 0, "");
    return strs;
  }

  public static void main(String[] args) {
    assert generateParenthesis(3).equals(List.of("((()))", "(()())", "(())()", "()(())", "()()()"));
  }
}
