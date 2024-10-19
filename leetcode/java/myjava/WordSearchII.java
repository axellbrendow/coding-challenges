package myjava;

import java.util.*;

/*-
Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are
horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

Example 1:
Input: board = [
["o","a","a","n"],
["e","t","a","e"],
["i","h","k","r"],
["i","f","l","v"]
], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]

Example 2:
Input: board = [
["a","b"],
["c","d"]
], words = ["abcb"]
Output: []

Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] is a lowercase English letter.
1 <= words.length <= 3 * 10^4
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
All the strings of words are unique.

{
  a: {
    a: {
      b: {
        $: null
      }
    }
  }
}
*/

public class WordSearchII {
  private static class Trie {
    private Node trie = new Node();

    public Trie(String[] strs) {
      for (String str : strs) add(str);
    }

    public void add(String str) {
      Node node = trie;
      for (char c : str.toCharArray()) {
        Node charNode = node.charMap.get(c);
        if (charNode == null) node.charMap.put(c, charNode = new Node());
        node = charNode;
      }
    }

    public boolean search(String str) {
      if (str.isEmpty()) return false;
      Node node = trie;
      for (char c : str.toCharArray()) {
        node = node.charMap.get(c);
        if (node == null) return false;
      }
      return true;
    }

    private static class Node {
      Map<Character, Node> charMap = new HashMap<>();
    }
  }

  public static void findWords(
    char[][] board,
    Set<String> notFoundSet,
    Set<String> foundWords,
    Trie trie,
    boolean[][] visited,
    int numLines,
    int numCols,
    int i,
    int j,
    String prefix
  ) {
    if (!(0 <= i && i < numLines && 0 <= j && j < numCols) || visited[i][j]) return;
    prefix += board[i][j];
    if (notFoundSet.contains(prefix)) {
      notFoundSet.remove(prefix);
      foundWords.add(prefix);
    }
    if (notFoundSet.isEmpty() || !trie.search(prefix)) return;
    visited[i][j] = true;
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    for (int[] direction : directions) {
      if (notFoundSet.isEmpty()) return;
      findWords(
        board,
        notFoundSet,
        foundWords,
        trie,
        visited,
        numLines,
        numCols,
        i + direction[0],
        j + direction[1],
        prefix
      );
    }
    visited[i][j] = false;
  }

  public static List<String> findWords(char[][] board, String[] words) {
    Trie trie = new Trie(words);
    int numLines = board.length, numCols = board[0].length;
    Set<String> notFoundSet = new HashSet<>(Arrays.stream(words).toList());
    Set<String> foundWords = new HashSet<>();
    for (int i = 0; i < numLines; i++) {
      if (notFoundSet.isEmpty()) break;
      for (int j = 0; j < numCols; j++) {
        if (notFoundSet.isEmpty()) break;
        findWords(
          board,
          notFoundSet,
          foundWords,
          trie,
          new boolean[numLines][numCols],
          numLines,
          numCols,
          i,
          j,
          ""
        );
      }
    }
    return foundWords.stream().toList();
  }

  public static void main(String[] args) {
    assert findWords(
      new char[][]{
        new char[]{'o', 'a', 'a', 'n'},
        new char[]{'e', 't', 'a', 'e'},
        new char[]{'i', 'h', 'k', 'r'},
        new char[]{'i', 'f', 'l', 'v'},
      },
      new String[]{"oath", "pea", "eat", "rain"}
    ).containsAll(List.of("eat", "oath"));

    assert findWords(
      new char[][]{
        new char[]{'a', 'a', 'x', 'x'},
        new char[]{'a', 'a', 'y', 'y'}
      },
      new String[]{"aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa"}
    ).equals(List.of("aaaa"));
  }
}
