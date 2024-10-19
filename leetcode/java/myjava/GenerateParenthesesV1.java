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


"()" {
  "(())" {
    "((()))"
    "(()())"
    "(())()"
  }
  "()()" {
    "()(())"
    "()()()"
  }
}

"()" {
  "(())" {
    "((()))" {
      "(((())))"
      "((()()))"
      "((())())"
      "((()))()"
    }
    "(()())" {
      "(()(()))"
      "(()()())"
      "(()())()"
    }
  }
  "()()" {
    "()(())" {
      "()((()))"
      "()(()())"
      "()(())()"
    }
    "()()()" {
      "()()(())"
      "()()()()"
    }
  }
}

*/

public class GenerateParenthesesV1 {
  public static void generateParentheses(List<String> strs, int n, int depth, String str, int index) {
    if (n == 1) strs.add(str);
    else {
      for (int i = 0; i <= depth; i++) {
        generateParentheses(
          strs,
          n - 1,
          depth + 1 - i,
          str.substring(0, index) + "()" + str.substring(index),
          index + 1
        );
        index++;
      }
    }
  }

  public static List<String> generateParenthesis(int n) {
    List<String> strs = new ArrayList<>();
    generateParentheses(strs, n, 1, "()", 1);
    return strs;
  }

  public static void main(String[] args) {
    assert generateParenthesis(3).equals(List.of("((()))", "(()())", "(())()", "()(())", "()()()"));
  }
}
