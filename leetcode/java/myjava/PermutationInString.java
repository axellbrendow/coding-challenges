package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/permutation-in-string/description/

Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.

----

s1.length > s2.length return false

s1 = "ba"
s2 = "abc"
output = true

s1 = "ba"
s2 = "a"
output = false

s1 = "c"
s2 = "abc"
output = true

s1 = "w"
s2 = "hdasjhdshja"
output = false

s1 = "jsad"
s2 = "hdasjhdshja"
output = true

---

s1 = "jsad"
s2 = "hsdasjhdshja"
output = true

s1Map = { j: 1, s: 1, a: 1, d: 1 }
map = {}
s1 = "jsad"
s2 = "hsdasjhdshja"
      i
      j

s1Map = { j: 1, s: 1, a: 1, d: 1 }
map = {}
s1 = "jsad"
s2 = "hsdasjhdshja"
      i
      j (h is not part of s1, move i = j + 1)

s1Map = { j: 1, s: 1, a: 1, d: 1 }
map = { s: 1 }
s1 = "jsad"
s2 = "hsdasjhdshja"
       i
       j ('s' is part of s1 and the count of 's' is < the desired count)

s1Map = { j: 1, s: 1, a: 1, d: 1 }
map = { s: 1, d: 1 }
s1 = "jsad"
s2 = "hsdasjhdshja"
       i
        j

s1Map = { j: 1, s: 1, a: 1, d: 1 }
map = { s: 1, d: 1, a: 1 }
s1 = "jsad"
s2 = "hsdasjhdshja"
       i
         j

s1Map = { j: 1, s: 1, a: 1, d: 1 }
map = { s: 1, d: 1, a: 1 }
s1 = "jsad"
s2 = "hsdasjhdshja"
       i
          j ('s' is part of s1 but the count of 's' is >= the desired count)
            (move i while count of 's' is >= the desired count)

s1Map = { j: 1, s: 1, a: 1, d: 1 }
map = { s: 0, d: 1, a: 1 }
s1 = "jsad"
s2 = "hsdasjhdshja"
        i (decrease the count of 's')
          j (now I can increase the count of 's')

s1Map = { j: 1, s: 1, a: 1, d: 1 }
map = { s: 1, d: 1, a: 1, j: 1 }
s1 = "jsad"
s2 = "hsdasjhdshja"
        i
           j (return true because current sliding window size == s1.length)
*/

public class PermutationInString {
  public static boolean checkInclusion(String s1, String s2) {
    if (s1.length() > s2.length()) return false;
    final var s1Map = new int[26];
    for (int i = 0; i < s1.length(); i++) s1Map[s1.charAt(i) - 'a']++;
    final var windowMap = new int[26];
    int i = 0, j = 0;
    while (j < s2.length()) {
      char currChar = s2.charAt(j);
      if (s1Map[currChar - 'a'] == 0) {
        i = j + 1;
        Arrays.fill(windowMap, 0);
        j++;
        continue;
      }
      while (windowMap[currChar - 'a'] == s1Map[currChar - 'a']) {
        windowMap[s2.charAt(i) - 'a']--;
        i++;
      }
      windowMap[currChar - 'a']++;
      if (j - i + 1 == s1.length()) return true;
      j++;
    }
    return false;
  }

  public static void main(String[] args) {
    assert checkInclusion("ba", "abc") == true;
    assert checkInclusion("ba", "a") == false;
    assert checkInclusion("c", "abc") == true;
    assert checkInclusion("w", "hdasjhdshja") == false;
    assert checkInclusion("jsad", "hdasjhdshja") == true;
    assert checkInclusion("jsad", "hsdasjhdshja") == true;
    assert checkInclusion("ky", "ainwkckifykxlribaypk") == true;
  }
}
