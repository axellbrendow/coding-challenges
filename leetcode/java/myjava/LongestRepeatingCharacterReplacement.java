package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/longest-repeating-character-replacement/description/

You are given a string s and an integer k. You can choose any character of the string and change it to any
other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above
operations.

1 <= s.length <= 10^5
s consists of only uppercase English letters.
0 <= k <= s.length

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.

s = "A"
k = 0
output = 1

s = "A"
k = 1
output = 1

s = "AB"
k = 0
output = 1

s = "AB"
k = 1
output = 2

s = "ABABAB"
k = 2
output = 4

s = "ABABAB"
k = 3
output = 6

s = "ABB"
k = 3
output = 3

---

s = "AABJSDJDQUBDWQUDINQNWQI"
k = 6
output = 3

k = 6
output = 3
count = {A: 1}
s = "AABJSDJDQUBDWQUDINQNWQI"
     i (increase sliding window while (window size - frequency of most repeating character in the window) is at most k)
     j

k = 6
output = 3
count = {A: 2}
s = "AABJSDJDQUBDWQUDINQNWQI"
     i
      j

k = 6
output = 3
count = {A: 2, B: 1}
s = "AABJSDJDQUBDWQUDINQNWQI"
     i
       j

k = 6
output = 3
count = {A: 2, B: 1, J: 1}
s = "AABJSDJDQUBDWQUDINQNWQI"
     i
        j

k = 6
output = 3
count = {A: 2, B: 1, J: 1, S: 1}
s = "AABJSDJDQUBDWQUDINQNWQI"
     i
         j

k = 6
output = 3
count = {A: 2, B: 1, J: 1, S: 1, D: 1}
s = "AABJSDJDQUBDWQUDINQNWQI"
     i
          j

k = 6
output = 3
count = {A: 2, B: 1, J: 2, S: 1, D: 1}
s = "AABJSDJDQUBDWQUDINQNWQI"
     i
           j

k = 6
output = 3
count = {A: 2, B: 1, J: 2, S: 1, D: 2}
s = "AABJSDJDQUBDWQUDINQNWQI"
     i
            j

k = 6
output = 3
count = {A: 2, B: 1, J: 2, S: 1, D: 2, Q: 1}
s = "AABJSDJDQUBDWQUDINQNWQI"
     i (reduce sliding window while (window size - frequency of most repeating character in the window) is greater than k)
             j

k = 6
output = 3
count = {A: 1, B: 1, J: 2, S: 1, D: 2, Q: 1}
s = "AABJSDJDQUBDWQUDINQNWQI"
      i (valid window, can replace all letters with J)
             j

k = 6
output = 3
count = {A: 1, B: 1, J: 2, S: 1, D: 2, Q: 1, U: 1}
s = "AABJSDJDQUBDWQUDINQNWQI"
      i (increase sliding window while (window size - frequency of most repeating character in the window) is at most k)
              j
*/

public class LongestRepeatingCharacterReplacement {
  public static int characterReplacement(String s, int k) {
    var freq = new int[26];
    int i = 0, j = 0, len = s.length(), longestStrWithSameLetter = 1;
    while (j < len) {
      freq[s.charAt(j) - 'A']++;
      int maxFreq = Arrays.stream(freq).max().getAsInt(); // O(26)
      if (j - i + 1 - maxFreq <= k) {
        longestStrWithSameLetter = Math.max(longestStrWithSameLetter, j - i + 1);
      } else {
        freq[s.charAt(i) - 'A']--;
        i++;
      }
      j++;
    }
    return longestStrWithSameLetter;
  }

  public static void main(String[] args) {
    assert characterReplacement("A", 0) == 1;
    assert characterReplacement("A", 1) == 1;
    assert characterReplacement("AB", 0) == 1;
    assert characterReplacement("AB", 1) == 2;
    assert characterReplacement("ABABAB", 2) == 5;
    assert characterReplacement("ABABAB", 3) == 6;
    // "DJDQUBDWQ"
    assert characterReplacement("AABJSDJDQUBDWQUDINQNWQI", 6) == 9;
  }
}
