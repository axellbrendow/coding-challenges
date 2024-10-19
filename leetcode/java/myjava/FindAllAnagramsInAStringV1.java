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

s = ""
p = "abc"
output = []

s = "a"
p = "abc"
output = []

s = "abe"
p = "abc"
output = []

s = "abc"
p = "abc"
output = [0]

s = "abca"
p = "abc"
output = [0, 1]

---

s = "abcaacb"
p = "abc"
expectedOutput = [0, 1, 4]

p = "abc"
pMap = { a: 1, b: 1, c: 1 }
windowCount = { a: 1 } // 'a' is added to the window, curr window size == 1
s = "abcaacb"
     i
     j

// end of iteration, j++

p = "abc"
pMap = { a: 1, b: 1, c: 1 }
windowCount = { a: 1, b: 1 } // 'b' is added to the window
s = "abcaacb"
     i
      j

// end of iteration, j++

p = "abc"
pMap = { a: 1, b: 1, c: 1 }
windowCount = { a: 1, b: 1, c: 1 }
s = "abcaacb"
     i (sliding window size == p.length() solution found)
       j

// end of iteration, j++

p = "abc"
pMap = { a: 1, b: 1, c: 1 }
windowCount = { a: 1, b: 1, c: 1 }
s = "abcaacb"
     i (decrease sliding window while the character at j already has the desired count)
        j
      i (i changes)
        j

p = "abc"
pMap = { a: 1, b: 1, c: 1 }
windowCount = { a: 1, b: 1, c: 1 }
s = "abcaacb"
      i (sliding window size == p.length() solution found)
        j

p = "abc"
pMap = { a: 1, b: 1, c: 1 }
windowCount = { a: 1, b: 0, c: 1 }
s = "abcaacb"
       i
         j (decrease sliding window while the character at j already has the desired count)

p = "abc"
pMap = { a: 1, b: 1, c: 1 }
windowCount = { a: 1, b: 0, c: 0 }
s = "abcaacb"
        i
         j (decrease sliding window while the character at j already has the desired count)

p = "abc"
pMap = { a: 1, b: 1, c: 1 }
windowCount = { a: 1, b: 0, c: 0 }
s = "abcaacb"
         i
         j

p = "abc"
pMap = { a: 1, b: 1, c: 1 }
windowCount = { a: 1, b: 0, c: 1 }
s = "abcaacb"
         i
          j

p = "abc"
pMap = { a: 1, b: 1, c: 1 }
windowCount = { a: 1, b: 1, c: 1 }
s = "abcaacb"
         i (sliding window size == p.length() solution found)
           j
---

s = "bawbba"
p = "aab"
output = [0, 1, 4]

map = { b: 1 }
p = "aab"
s = "bawbba"
     i
     j

map = { b: 1, a: 1 }
p = "aab"
s = "bawbba"
     i
      j

map = {}
p = "aab"
s = "bawbba"
     i
       j

map = { b: 1 }
p = "aab"
s = "bawbba"
        i
        j

map = { b: 2 }
p = "aab"
s = "bawbba"
        i
         j

map = { b: 1 }
p = "aab"
s = "bawbba"
         i
         j

map = { b: 1, a: 1 }
p = "aab"
s = "bawbba"
         i
          j

map = { b: 1, a: 1 }
p = "aab"
s = "bawbba"
         i
           j (exit)
*/

public class FindAllAnagramsInAStringV1 {
  public static List<Integer> findAnagrams(String s, String p) {
    List<Integer> result = new ArrayList<>();
    Map<Character, Long> windowCount = new HashMap<>();
    Map<Character, Long> pMap = p.chars()
      .mapToObj(c -> (char) c)
      .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

    int i = 0, j = 0;
    while (j < s.length()) {
      Long desiredCount = pMap.get(s.charAt(j));
      if (desiredCount == null) {
        i = j + 1;
        j++;
        windowCount = new HashMap<>();
        continue;
      }

      // Decrease sliding window while the character at j already has the desired count
      while (Objects.equals(windowCount.get(s.charAt(j)), desiredCount)) {
        windowCount.put(s.charAt(i), windowCount.get(s.charAt(i)) - 1);
        i++;
      }

      // Add s[j] to the window
      windowCount.put(s.charAt(j), windowCount.getOrDefault(s.charAt(j), 0L) + 1);
      if (j - i + 1 == p.length()) result.add(i);
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
