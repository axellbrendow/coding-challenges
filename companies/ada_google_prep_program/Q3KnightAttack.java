package ada_google_prep_program;

/*-
You are given:
1. A positive integer N, representing the size of an N x N chessboard.
2. An array position of size 2 representing the initial position of a knight in the board ([X, Y]).
3. An array opponents containing the position of opponent pieces in the board. Each entry in the opponents
array is an array of size 2 in the form [i, j] representing the coordinates of the opponent.
4. A positive integer K representing the maximum number of moves allowed for the knight.

A knight in chess can move in an "L" shape, i.e., two squares in one direction (horizontal or vertical) and
one square in a perpendicular direction, allowing up to 8 possible moves. As an example, the following image
shows the knight at position [3, 3], and the green squares show the possible moves it can reach from that
position ([2,1],[1,2],[1,4],[2,5],[4,5],[5,4],[5,2],[4,1]).

B: Black
W: White
K: Knight

   0   1   2   3   4   5   6
0  W   B   W   B   W   B   W
1  B   W  |B|  W  |B|  W   B
2  W  |B|  W   B   W  |B|  W
3  B   W   B  *K*  B   W   B
4  W  |B|  W   B   W  |B|  W
5  B   W  |B|  W  |B|  W   B
6  W   B   W   B   W   B   W

The goal is to compute the maximum number of opponent pieces that the knight can capture in at most K moves.
Note that:
- Capturing occurs if the knight lands on a position containing an opponent piece.
- The knight cannot capture the same opponent more than once.
- The knight must stay within the board boundaries.

Input:
- An integer N (size of the board).
- An array containing two positive integers [X, Y] (starting position of the knight).
- A list opponents consisting of arrays having two positive integers (locations of opponent pieces).
- An integer K (maximum number of moves).

Output:
Return the maximum number of opponent pieces that the knight can capture in at most K moves.

Contraints:
- N <= 8
- 0 <= X, Y < N (initial knight position)
- 0 <= i, j < N, for each [i, j] in the opponents array
- 1 <= K <= 10
- 1 <= opponents.length <= 15

Example 1:

Input:
N = 8
position = [3, 2]
opponents = [[5, 1], [4, 5], [5, 4]]
K = 1

Output: 1

B: Black
W: White
K: Knight
P: Pawn

   0   1   2   3   4   5   6   7
0  B   W   B   W   B   W   B   W
1  W   B   W   B   W   B   W   B
2  B   W   B   W   B   W   B   W
3  W   B  *K*  B   W   B   W   B
4  B   W   B   W   B  *P*  B   W
5  W  *P*  W   B  *P*  B   W   B
6  B   W   B   W   B   W   B   W
7  W   B   W   B   W   B   W   B

Explanation:

The knight starts at position [3, 2] and has 1 move. Possible moves are: [4, 4], [5, 3], [5, 1], [4, 0], [2, 0],
[1, 1], [1, 3], and [2, 4]. The knight can reach one opponent within 1 move: [5, 1].
*/

public class Q3KnightAttack {
  private static int[][] DIRECTIONS = new int[][]{
    {-1, 2}, // RIGHT RIGHT UP
    {1, 2}, // RIGHT RIGHT DOWN
    {2, 1}, // DOWN DOWN RIGHT
    {2, -1}, // DOWN DOWN LEFT
    {1, -2}, // LEFT LEFT DOWN
    {-1, -2}, // LEFT LEFT UP
    {-2, -1}, // UP UP LEFT
    {-2, 1}, // UP UP RIGHT
  };

  private static int knightAttack(
    int boardSize, int[][] board, boolean[][] visited, int maxMoves, int numMoves, int i, int j
  ) {
    if (!(0 <= i && i < boardSize && 0 <= j && j < boardSize)) return 0;
    if (numMoves > maxMoves) return 0;
    if (visited[i][j]) return 0;
    visited[i][j] = true;
    int maxOpponents = 0;
    for (int[] direction : DIRECTIONS) {
      int opponents = knightAttack(
        boardSize, board, visited, maxMoves, numMoves + 1, i + direction[0], j + direction[1]
      );
      maxOpponents = Math.max(maxOpponents, board[i][j] + opponents);
    }
    visited[i][j] = false;
    return maxOpponents;
  }

  private static int knightAttack(int boardSize, int[] start, int[][] opponents, int maxMoves) {
    boolean[][] visited = new boolean[boardSize][boardSize];
    int[][] board = new int[boardSize][boardSize];
    for (int[] opponent : opponents)
      if (opponent[0] < boardSize && opponent[1] < boardSize)
        board[opponent[0]][opponent[1]] = 1;

    return knightAttack(boardSize, board, visited, maxMoves, 0, start[0], start[1]);
  }

  public static void main(String[] args) {
    /*-
       0   1   2   3   4   5   6   7
    0  B   W   B   W   B   W   B   W
    1  W   B   W   B   W   B   W   B
    2  B   W   B   W   B   W   B   W
    3  W   B  *K*  B   W   B   W   B
    4  B   W   B   W   B  *P*  B   W
    5  W  *P*  W   B  *P*  B   W   B
    6  B   W   B   W   B   W   B   W
    7  W   B   W   B   W   B   W   B
    */
    assert knightAttack(8, new int[]{3, 2}, new int[][]{{5, 1}, {4, 5}, {5, 4}}, 1) == 1;

    /*-
       0   1   2   3   4   5   6
    0  W   B   W   B   W   B   W
    1  B   W  |B|  W  |B|  W   B
    2  W  |B|  W   B   W  |B|  W
    3  B   W   B  *K*  B   W   B
    4  W  |B|  W   B   W  |B|  W
    5  B   W  |B|  W  |B|  W   B
    6  W   B   W   B   W   B   W
    */
    assert knightAttack(
      8, new int[]{3, 3}, new int[][]{{2, 1}, {1, 2}, {1, 4}, {2, 5}, {4, 5}, {5, 4}, {5, 2}, {4, 1}}, 9
    ) == 5;
  }
}
