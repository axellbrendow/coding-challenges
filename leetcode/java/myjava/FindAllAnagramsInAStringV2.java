package myjava;

import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/*-
https://leetcode.com/problems/find-all-anagrams-in-a-string/description/

Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return
the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically
using all the original letters exactly once.

Contraints:
1 <= s.length, p.length <= 3 * 10^4
s and p consist of lowercase English letters.
*/

public class FindAllAnagramsInAStringV2 {
  public static List<Integer> findAnagrams(String s, String p) {
    if (s.length() < p.length()) return List.of();
    List<Integer> result = new ArrayList<>();
    int[] sCount = new int[26], pCount = new int[26];
    for (char c : p.toCharArray()) {
      pCount[c - 'a']++;
    }
    for (int i = 0; i < p.length(); i++) {
      sCount[s.charAt(i) - 'a']++;
    }
    if (Arrays.equals(sCount, pCount)) result.add(0);
    int i = 1, j = 1 + p.length() - 1;
    while (j < s.length()) {
      sCount[s.charAt(i - 1) - 'a']--;
      sCount[s.charAt(j) - 'a']++;
      if (Arrays.equals(sCount, pCount)) result.add(i);
      i++;
      j++;
    }
    return result;
  }

  public static void main(String[] args) throws IOException {
    assert findAnagrams("a", "aab").equals(List.of());
    assert findAnagrams("bawbba", "aab").equals(List.of());
    assert findAnagrams("abcaacb", "abc").equals(List.of(0, 1, 4));
    assert findAnagrams("abcabcabcabcabc", "abc").equals(IntStream.rangeClosed(0, 12).boxed().toList());

    try (
      final var br = Files.newBufferedReader(Paths.get("leetcode/java/myjava/FindAllAnagramsInAStringV1.in"))
    ) {
      String s = br.readLine();
      String p = br.readLine();
      assert findAnagrams(s, p).equals(IntStream.rangeClosed(0, 10062).boxed().toList());
    }
  }
}
