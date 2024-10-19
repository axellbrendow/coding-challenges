package myjava;

import java.util.*;
import java.util.stream.*;

/*-
https://algo.monster/problems/palindrome_partitioning
https://leetcode.com/problems/palindrome-partitioning/description/

LeetCode only constraints:
  1 <= s.length <= 16
  s contains only lowercase English letters.
    *** REALLY IMPORTANT CONSTRAINT ***, enables creating an array of 26 elements (all english letters)

Partitioning A String Into Palindromes

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

s = ""
output = []

s = "a"
output = [["a"]]

s = "ab"
output = [["a", "b"]]

s = "aa"
output = [["a", "a"], ["aa"]]

s = "aaa"
output = [["a", "a", "a"], ["aa", "a"], ["a", "aa"], ["aaa"]]

"aaa" index = 0, prefix = "", substrs = []
"a|aa" index = 1, prefix = "", substrs = ["a"]
"a|a|a" index = 2, prefix = "", substrs = ["a", "a"] -> returns [["a", "a", "a"]]
"a|aa" index = 1, prefix = "", substrs = ["a"]
"a|aa" index = 2, prefix = "a", substrs = ["a"] -> returns [["a", "aa"]]

when placing a divisor, prefix + curr_char must be a palindrome

s = "aba"
output = [["a", "b", "a"], ["aba"]]

s = "aab"
output = [["a", "a", "b"], ["aa", "b"]]
*/

public class PalindromePartitioningV1 {
  private static boolean isPalindrome(String str) {
    return str.equals(new StringBuilder(str).reverse().toString());
  }

  public static List<List<String>> partitionToPalindromesRec(
    String str, int index, String prefix, List<String> substrs
  ) {
    final var division = prefix + str.charAt(index);

    if (index >= str.length() - 1) {
      if (!isPalindrome(division)) return List.of();
      final List<String> partition = new ArrayList<>(substrs);
      partition.add(division);
      return List.of(partition);
    }

    List<List<String>> partitionsWithDivisor = List.of();

    // placing a divisor in the next index
    if (isPalindrome(division)) {
      substrs.add(division);
      partitionsWithDivisor = partitionToPalindromesRec(str, index + 1, "", substrs);
      substrs.remove(substrs.size() - 1);
    }

    List<List<String>> partitionsWithoutDivisor = partitionToPalindromesRec(
      str, index + 1, division, substrs
    );

    return Stream.concat(partitionsWithDivisor.stream(), partitionsWithoutDivisor.stream()).collect(
      Collectors.toList()
    );
  }

  public static List<List<String>> partitionToPalindromes(String str) {
    if (str.isEmpty()) return List.of();
    return partitionToPalindromesRec(str, 0, "", new ArrayList<String>());
  }

  public static void main(String[] args) {
    assert partitionToPalindromes("").equals(
      List.of()
    );
    assert partitionToPalindromes("a").equals(
      List.of(
        List.of("a")
      )
    );
    assert partitionToPalindromes("aa").equals(
      List.of(
        List.of("a", "a"),
        List.of("aa")
      )
    );
    assert partitionToPalindromes("aaa").equals(
      List.of(
        List.of("a", "a", "a"),
        List.of("a", "aa"),
        List.of("aa", "a"),
        List.of("aaa")
      )
    );
    assert partitionToPalindromes("aba").equals(
      List.of(
        List.of("a", "b", "a"),
        List.of("aba")
      )
    );
    assert partitionToPalindromes("aab").equals(
      List.of(
        List.of("a", "a", "b"),
        List.of("aa", "b")
      )
    );
  }
}
