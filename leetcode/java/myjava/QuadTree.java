package myjava;

/*-
https://leetcode.com/problems/construct-quad-tree/
*/

class QuadTree {
  private static boolean onlyOnesOrOnlyZeros(int line, int column, int squareSideSize, int[][] grid) {
    int firstCellValue = grid[line][column];
    for (int i = 0; i < squareSideSize; i++) {
      for (int j = 0; j < squareSideSize; j++) {
        if (grid[line + i][column + j] != firstCellValue) {
          return false;
        }
      }
    }
    return true;
  }

  private static Node quadTree(int line, int column, int squareSideSize, int[][] grid) {
    if (onlyOnesOrOnlyZeros(line, column, squareSideSize, grid)) {
      return new Node(grid[line][column] == 1, true);
    }
    return new Node(
      /*val*/ false,
      /*isLeaf*/ false,
      /*topLeft*/ quadTree(line, column, squareSideSize / 2, grid),
      /*topRight*/ quadTree(line, column + squareSideSize / 2, squareSideSize / 2, grid),
      /*bottomLeft*/ quadTree(line + squareSideSize / 2, column, squareSideSize / 2, grid),
      /*bottomRight*/ quadTree(
        line + squareSideSize / 2, column + squareSideSize / 2, squareSideSize / 2, grid
      )
    );
  }

  public static void main(String[] args) {
    int[][] grid;

    grid = new int[][]{
      new int[]{
        0, 0
      },
      new int[]{
        0, 0
      },
    };
    assert quadTree(0, 0, grid.length, grid).equals(
      new Node(/*val*/ false, /*isLeaf*/ true)
    );

    grid = new int[][]{
      new int[]{
        1, 1
      },
      new int[]{
        1, 1
      },
    };
    assert quadTree(0, 0, grid.length, grid).equals(
      new Node(/*val*/ true, /*isLeaf*/ true)
    );

    grid = new int[][]{
      new int[]{
        0, 1
      },
      new int[]{
        1, 0
      },
    };
    assert quadTree(0, 0, grid.length, grid).equals(
      new Node(
        /*val*/ false,
        /*isLeaf*/ false,
        /*topLeft*/ new Node(/*val*/ false, /*isLeaf*/ true),
        /*topRight*/ new Node(/*val*/ true, /*isLeaf*/ true),
        /*bottomLeft*/ new Node(/*val*/ true, /*isLeaf*/ true),
        /*bottomRight*/ new Node(/*val*/ false, /*isLeaf*/ true)
      )
    );
  }
}

class Node {
  public boolean val;
  public boolean isLeaf;
  public Node topLeft;
  public Node topRight;
  public Node bottomLeft;
  public Node bottomRight;

  public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
    this.val = val;
    this.isLeaf = isLeaf;
    this.topLeft = topLeft;
    this.topRight = topRight;
    this.bottomLeft = bottomLeft;
    this.bottomRight = bottomRight;
  }

  public Node(boolean val, boolean isLeaf) {
    this(
      val,
      isLeaf,
      /*topLeft*/ null,
      /*topRight*/ null,
      /*bottomLeft*/ null,
      /*bottomRight*/ null
    );
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Node other = (Node) obj;
    if (this.isLeaf) {
      return other.isLeaf && other.val == val;
    } else {
      return (!other.isLeaf &&
        topLeft.equals(other.topLeft) &&
        topRight.equals(other.topRight) &&
        bottomLeft.equals(other.bottomLeft) &&
        bottomRight.equals(other.bottomRight));
    }
  }
}
