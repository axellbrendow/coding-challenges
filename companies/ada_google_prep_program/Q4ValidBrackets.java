package ada_google_prep_program;

import java.util.*;

/*-
You are given a string s containing characters from a set of three types of brackets: '(', ')', '{', '}', '[', ']'.
Additionally, the string s contains alphabetic characters. Write an algorithm to determine if the string is
brackets-balanced. A string is bracket-balanced if all brackets are closed in the correct order and the correct
type and there are no orphan brackets.

Example 1
Input: s = "a{b(c)d}e"
Output: True
Explanation: Both brackets open and close in the right sequence.

Example 2
Input: s = "a{bc)d}e[fgh{i}]j"
Output: False
Explanation: There is a closing parenthesis without a corresponding opening one.
*/

public class Q4ValidBrackets {
  private static boolean validBrackets(String str) {
    Deque<Character> stack = new LinkedList<>();
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == '(' || str.charAt(i) == '{' || str.charAt(i) == '[') stack.push(str.charAt(i));
      else if (str.charAt(i) == ')') {
        if (stack.isEmpty() || stack.peek() != '(') return false;
        stack.pop();
      } else if (str.charAt(i) == ']') {
        if (stack.isEmpty() || stack.peek() != '[') return false;
        stack.pop();
      } else if (str.charAt(i) == '}') {
        if (stack.isEmpty() || stack.peek() != '{') return false;
        stack.pop();
      }
    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    assert validBrackets("") == true;
    assert validBrackets("(") == false;
    assert validBrackets("()") == true;
    assert validBrackets(")") == false;
    assert validBrackets("{{}") == false;
    assert validBrackets("a{b(c)d}e") == true;
    assert validBrackets("a{bc)d}e[fgh{i}]j") == false;
  }
}
