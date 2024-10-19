package myjava;

public class CheckIfWordCanBePlacedInCrossword {
  private static boolean canPlaceWord(
    String word, int wordIndex, char[][] board, int nlines, int ncols, int i, int j, int[] direction
  ) {
    boolean outOfBounds = !(0 <= i && i < nlines && 0 <= j && j < ncols);
    if (wordIndex == word.length()) return outOfBounds || board[i][j] == '#';
    if (outOfBounds) return false;
    if (board[i][j] != ' ' && board[i][j] != word.charAt(wordIndex)) return false;
    return canPlaceWord(
      word, wordIndex + 1, board, nlines, ncols, i + direction[0], j + direction[1], direction
    );
  }

  private static boolean canPlaceWord(String word, char[][] board, int nlines, int ncols, int i, int j) {
    return ((j + 1 >= ncols || board[i][j + 1] == '#') &&
      canPlaceWord(word, 0, board, nlines, ncols, i, j, new int[]{0, -1}) ||
      (j - 1 < 0 || board[i][j - 1] == '#') &&
        canPlaceWord(word, 0, board, nlines, ncols, i, j, new int[]{0, 1}) ||
      (i + 1 >= nlines || board[i + 1][j] == '#') &&
        canPlaceWord(word, 0, board, nlines, ncols, i, j, new int[]{-1, 0}) ||
      (i - 1 < 0 || board[i - 1][j] == '#') &&
        canPlaceWord(word, 0, board, nlines, ncols, i, j, new int[]{1, 0}));
  }

  public static boolean placeWordInCrossword(char[][] board, String word) {
    int nlines = board.length, ncols = board[0].length;
    for (int i = 0; i < nlines; i++) {
      for (int j = 0; j < ncols; j++) {
        if (board[i][j] == '#') continue;
        if (
          (board[i][j] == ' ' || board[i][j] == word.charAt(0)) &&
            canPlaceWord(word, board, nlines, ncols, i, j)
        )
          return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    assert placeWordInCrossword(
      new char[][]{
        {'#', ' ', '#'},
        {' ', ' ', '#'},
        {'#', 'c', ' '}
      },
      "abc"
    ) == true;

    assert placeWordInCrossword(
      new char[][]{
        {' ', '#', 'a'},
        {' ', '#', 'c'},
        {' ', '#', 'a'}
      },
      "ac"
    ) == false;

    assert placeWordInCrossword(
      new char[][]{
        {' ', ' '},
        {' ', ' '}
      },
      "a"
    ) == false;
  }
}
