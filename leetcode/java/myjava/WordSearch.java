package myjava;

/*-
https://leetcode.com/problems/word-search/description/

Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are
horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example 1:
Input: board = [
  ["A","B","C","E"],
  ["S","F","C","S"],
  ["A","D","E","E"]
], word = "ABCCED"
Output: true

Example 2:
Input: board = [
  ["A","B","C","E"],
  ["S","F","C","S"],
  ["A","D","E","E"]
], word = "SEE"
Output: true

Example 3:
Input: board = [
  ["A","B","C","E"],
  ["S","F","C","S"],
  ["A","D","E","E"]
], word = "ABCB"
Output: false

Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.

Follow up: Could you use search pruning to make your solution faster with a larger board?

---

word = "APPQ"
board =
Q P A
C P W
W A W

word = "APPA"
board =
Q *P *A *P
C *P W *P
W W W *A

word = "APPA"
board =
Q *P *A *P
P *P W *P
A W W *A

"ABCEFSADEESE"
[
    ["A","B","C","E"],
    ["S","F","E","S"],
    ["A","D","E","E"]
]
*/

public class WordSearch {
  public static boolean exist(
    char[][] board,
    String word,
    boolean[][] visited,
    int numLines,
    int numCols,
    int i,
    int j,
    int wordIndex
  ) {
    if (wordIndex >= word.length()) return true;
    if (
      !(0 <= i && i < numLines && 0 <= j && j < numCols) || visited[i][j] || board[i][j] != word.charAt(
        wordIndex
      )
    ) return false;
    visited[i][j] = true;
    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    for (int[] direction : directions)
      if (
        exist(
          board,
          word,
          visited,
          numLines,
          numCols,
          i + direction[0],
          j + direction[1],
          wordIndex + 1
        )
      ) return true;
    visited[i][j] = false;
    return false;
  }

  public static boolean exist(char[][] board, String word) {
    int numLines = board.length, numCols = board[0].length;
    for (int i = 0; i < numLines; i++) {
      for (int j = 0; j < numCols; j++) {
        if (
          board[i][j] == word.charAt(0) && exist(
            board, word, new boolean[numLines][numCols], numLines, numCols, i, j, 0
          )
        ) return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    assert exist(
      new char[][]{
        new char[]{'A', 'B', 'C', 'E'},
        new char[]{'S', 'F', 'C', 'S'},
        new char[]{'A', 'D', 'E', 'E'},
      },
      "ABCCED"
    ) == true;

    assert exist(
      new char[][]{
        new char[]{'A', 'B', 'C', 'E'},
        new char[]{'S', 'F', 'C', 'S'},
        new char[]{'A', 'D', 'E', 'E'},
      },
      "SEE"
    ) == true;

    assert exist(
      new char[][]{
        new char[]{'A', 'B', 'C', 'E'},
        new char[]{'S', 'F', 'C', 'S'},
        new char[]{'A', 'D', 'E', 'E'},
      },
      "ABCB"
    ) == false;

    assert exist(
      new char[][]{
        new char[]{'A', 'B', 'C', 'E'},
        new char[]{'S', 'F', 'E', 'S'},
        new char[]{'A', 'D', 'E', 'E'},
      },
      "ABCEFSADEESE"
    ) == true;
  }
}
