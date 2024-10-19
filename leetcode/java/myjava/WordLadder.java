package myjava;

import java.util.*;
import java.util.stream.*;

/*-
https://leetcode.com/problems/word-ladder/description/

A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of
words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord

Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest
transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

Example 1:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

Example 2:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

Constraints:

1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
*/

public class WordLadder {
  private static boolean oneDiff(String str1, String str2) {
    int diff = 0;
    for (int i = 0; i < str1.length(); i++) {
      if (str1.charAt(i) != str2.charAt(i)) diff++;
      if (diff > 1) return false;
    }
    return true;
  }

  private static Map<String, List<String>> buildGraph(List<String> wordList) {
    Map<String, List<String>> graph = new HashMap<>();
    for (int i = 0; i < wordList.size(); i++) {
      String wordI = wordList.get(i);
      for (int j = i + 1; j < wordList.size(); j++) {
        String wordJ = wordList.get(j);
        if (oneDiff(wordI, wordJ)) {
          graph.computeIfAbsent(wordI, k -> new ArrayList<>()).add(wordJ);
          graph.computeIfAbsent(wordJ, k -> new ArrayList<>()).add(wordI);
        }
      }
    }
    return graph;
  }

  public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
    if (!wordList.contains(beginWord)) wordList.add(beginWord);
    Map<String, List<String>> graph = buildGraph(wordList);
    if (!graph.containsKey(endWord) || graph.get(beginWord) == null) return 0;
    Set<String> visited = new HashSet<>();
    int level = 0;
    Queue<String> queue = new LinkedList<>();
    queue.offer(endWord);
    while (!queue.isEmpty()) {
      level++;
      int levelSize = queue.size();
      for (int i = 0; i < levelSize; i++) {
        String word = queue.poll();
        if (visited.contains(word)) continue;
        visited.add(word);
        if (beginWord.equals(word)) return level;
        for (String child : graph.get(word)) {
          queue.offer(child);
        }
      }
    }
    return 0;
  }

  public static void main(String[] args) {
    assert ladderLength(
      "a",
      "c",
      new ArrayList<>(List.of("a", "b", "c"))
    ) == 2;

    assert ladderLength(
      "hit",
      "cog",
      new ArrayList<>(List.of("hot", "dot", "dog", "lot", "log", "cog"))
    ) == 5;

    assert ladderLength(
      "hit",
      "cog",
      new ArrayList<>(List.of("hot", "dot", "dog", "lot", "log"))
    ) == 0;

    assert ladderLength(
      "lost",
      "miss",
      new ArrayList<>(List.of("most", "mist", "miss", "lost", "fist", "fish"))
    ) == 4;
  }
}
