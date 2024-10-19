package myjava;

import java.util.*;
import java.util.stream.*;

/*-
https://leetcode.com/problems/group-anagrams/description/

Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

Time Limit Exceeded

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

1 <= strs.length <= 10^4
0 <= strs[i].length <= 100

strs = []
output = []

strs = [""]
output = [[""]]

strs = ["eat"]
output = [["eat"]]

strs = ["eat", "ate"]
output = [["eat", "ate"]]

strs = ["eat", "ate", "bat"]
output = [["eat", "ate"], ["bat"]]

brute force:
for each string, compare it to the others and group if they are anagrams
n -> strs.length
l -> strs[i].length
time complexity: n * n * l
space complexity: O(n)
*/
public class GroupAnagramsV1 {
  private static boolean areAnagrams(String str1, String str2) {
    if (str1.length() != str2.length()) return false;
    final Map<Character, Long> charOccurrences = str1.chars()
      .mapToObj(charCode -> (char) charCode)
      .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    for (int i = 0; i < str2.length(); i++) {
      Long charCount = charOccurrences.get(str2.charAt(i));
      if (charCount == null || charCount == 0) return false;
      if (charCount - 1 == 0) {
        charOccurrences.remove(str2.charAt(i));
      } else {
        charOccurrences.put(str2.charAt(i), charCount - 1);
      }
    }
    return charOccurrences.size() == 0;
  }

  public static List<List<String>> groupAnagrams(String[] strs) {
    final List<List<String>> output = new ArrayList<>();
    final var processed = new boolean[strs.length];
    for (int i = 0; i < strs.length; i++) {
      if (processed[i]) continue;
      processed[i] = true;
      final List<String> anagrams = new ArrayList<>();
      anagrams.add(strs[i]);
      for (int j = i + 1; j < strs.length; j++) {
        if (processed[j]) continue;
        if (areAnagrams(strs[i], strs[j])) {
          processed[j] = true;
          anagrams.add(strs[j]);
        }
      }
      output.add(anagrams);
    }
    return output;
  }

  public static void main(String[] args) {
    assert groupAnagrams(new String[]{}).equals(List.of());
    assert groupAnagrams(new String[]{""}).equals(List.of(List.of("")));
    assert groupAnagrams(new String[]{"ate"}).equals(List.of(List.of("ate")));
    assert groupAnagrams(new String[]{"ate", "tea"}).equals(List.of(List.of("ate", "tea")));

    assert groupAnagrams(new String[]{"ate", "tea", "bat"}).equals(
      List.of(List.of("ate", "tea"), List.of("bat"))
    );

    // Code that works with "characters" longer than 2 bytes (2 UTF-16 values like emojis)
    "😃abc".codePoints() // stream of integers
      .mapToObj(codePoint -> new String(new int[]{codePoint}, /*offset*/ 0, /*count*/ 1))
      .forEach(System.out::println);
    /*-
    The code above prints:
    😃
    a
    b
    c
    */

    // Now using StringBuilder
    final var str = "😃🐶";
    final int firstCodePoint = str.codePointAt(0);
    final int firstCodePointCharCount = Character.charCount(firstCodePoint); // 2 chars needed
    final int secondCodePointIndex = firstCodePointCharCount;
    final int secondCodePoint = str.codePointAt(secondCodePointIndex);
    System.out.println(new StringBuilder().appendCodePoint(firstCodePoint));
    System.out.println(new StringBuilder().appendCodePoint(secondCodePoint));
    /*-
    The code above prints:
    😃
    🐶
    */
  }
}
