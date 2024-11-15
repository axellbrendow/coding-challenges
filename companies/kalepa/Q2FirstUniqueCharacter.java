package kalepa;

import java.util.*;

/*-
A unique character is one that appears only once in a string. Given a string consisting of lowercase English
letters only, return the index of the first occurrence of a unique character in the string using 1-based
indexing. If the string does not contain any unique character, return -1.

Example
s = "statistics"

The unique characters are [a, c] among which a occurs first. Using 1-based indexing, it is at index 3.

Function Description
Complete the function getUniqueCharacter in the editor below.

getUniqueCharacter has the following parameter(s):
string s: a string

Returns
int: either the 1-based index or -1

Constraints
1 <= length of s <= 10^5
The string s consists of lowercase English letters only.

Sample Case 0
s = "hackthegame"
output = 3

Explanation
The unique characters are [c, k, t, g, m] out of which the characters c occurs first, at index 3.

Sample Case 1
s = "falafal"
output = -1

Explanation
All the characters present occur at least twice in the given string. There are no unique characters.
*/

public class Q2FirstUniqueCharacter {
  private static int getUniqueCharacter(String s) {
    Map<Character, Integer> count = new HashMap<>();
    for (int i = 0; i < s.length(); i++) count.put(s.charAt(i), 1 + count.getOrDefault(s.charAt(i), 0));
    for (int i = 0; i < s.length(); i++) if (count.get(s.charAt(i)) == 1) return i + 1;
    return -1;
  }

  public static void main(String[] args) {
    assert getUniqueCharacter("falafal") == -1;
    assert getUniqueCharacter("statistics") == 3;
    assert getUniqueCharacter("hackthegame") == 3;
  }
}
