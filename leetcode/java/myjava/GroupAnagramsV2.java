package myjava;

import java.util.*;
import java.util.stream.*;

/*-
https://leetcode.com/problems/group-anagrams/description/

Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically
using all the original letters exactly once.

1 <= strs.length <= 10^4
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
  *** REALLY IMPORTANT CONSTRAINT ***, enables creating an array of 26 elements to count letters

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

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
*/
public class GroupAnagramsV2 {
  private static String sortStr(String str) {
    int[] codePoints = str.codePoints().sorted().toArray();
    return new String(codePoints, 0, codePoints.length);
  }

  public static List<List<String>> groupAnagrams(String[] strs) {
    return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(str -> sortStr(str))).values());
  }

  public static void main(String[] args) {
    assert groupAnagrams(new String[]{}).equals(List.of());
    assert groupAnagrams(new String[]{""}).equals(List.of(List.of("")));
    assert groupAnagrams(new String[]{"ate"}).equals(List.of(List.of("ate")));
    assert groupAnagrams(new String[]{"ate", "tea"}).equals(List.of(List.of("ate", "tea")));

    assert groupAnagrams(new String[]{"ate", "tea", "bat"}).equals(
      List.of(List.of("ate", "tea"), List.of("bat"))
    );
  }
}
