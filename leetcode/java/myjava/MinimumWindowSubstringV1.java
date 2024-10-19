package myjava;

import java.util.*;
import java.util.stream.*;

/*-
https://leetcode.com/problems/minimum-window-substring/description/

Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that
every character in t (including duplicates) is included in the window. If there is no such substring, return
the empty string "".

The testcases will be generated such that the answer is unique.

---

Clarifying questions:
Can I consider that s and t consist of english lowercase letters ?
What about duplicated characters ?
Can I consider that there will always be a solution ?
<= s.length = m <= 
<= t.length = n <= 

---

Constraints:
m == s.length
n == t.length
1 <= m, n <= 10^5
s and t consist of uppercase and lowercase English letters.

An m * n solution would give TLE:
10^5 * 10^5 = 10^10 = 10 000 000 000 operations which is greater than 10 million or 20 million

---

s = "abddabda"
t = "abda"

s = "abddabda"
     i   j
      i  j
      i     j (while sliding window is valid, set min and increase i by 1)
         i  j
          i j (while sliding window is invalid, increase j by 1)
*/

public class MinimumWindowSubstringV1 {
  public static boolean validWindow(Map<Character, Long> windowCharCount, Map<Character, Long> tCharCount) {
    for (Map.Entry<Character, Long> entry : tCharCount.entrySet()) {
      if (
        !windowCharCount.containsKey(entry.getKey())
        || windowCharCount.get(entry.getKey()) < entry.getValue()
      ) {
        return false;
      }
    }
    return true;
  }

  public static String minWindow(String s, String t) {
    Map<Character, Long> tCharCount = t.chars()
      .mapToObj(c -> (char) c)
      .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

    Map<Character, Long> windowCharCount = new HashMap<>();
    windowCharCount.put(s.charAt(0), 1L);
    
    int i = 0, j = 0, minWindowI = 0, minWindowJ = s.length();
    while (j < s.length()) {
      if (validWindow(windowCharCount, tCharCount)) {
        if (j - i + 1 < minWindowJ - minWindowI + 1) {
          minWindowI = i;
          minWindowJ = j;
        }
        windowCharCount.compute(s.charAt(i), (c, oldValue) -> oldValue - 1);
        i++;
      } else {
        j++;
        if (j < s.length()) {
          windowCharCount.compute(s.charAt(j), (c, oldValue) -> oldValue == null ? 1 : oldValue + 1);
          // put and getOrDefault can replace the above too
          // windowCharCount.put(s.charAt(j), windowCharCount.getOrDefault(s.charAt(j), 0L) + 1);
        }
      }
    }
    return minWindowJ == s.length() ? "" : s.substring(minWindowI, minWindowJ + 1);
  }

  public static void main(String[] args) {
    assert minWindow("a", "a").equals("a");
    assert minWindow("a", "aa").equals("");
    assert minWindow("abddabda", "abda").equals("abda");
  }
}
