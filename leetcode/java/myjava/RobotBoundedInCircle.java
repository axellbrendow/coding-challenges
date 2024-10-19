package myjava;

/*-
https://leetcode.com/problems/robot-bounded-in-circle/description/
https://leetcode.com/problems/robot-bounded-in-circle/description/comments/1568654
*/

public class RobotBoundedInCircle {
  public static boolean isRobotBounded(String instructions) {
    int x = 0, y = 0;
    int[][] directions = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; // north, west, south, east
    int direction = 0; // north
    for (int j = 0; j < instructions.length(); j++) {
      char instr = instructions.charAt(j);
      if (instr == 'G') {
        x += directions[direction][0];
        y += directions[direction][1];
      } else if (instr == 'L') {
        direction = direction + 1 <= 3 ? direction + 1 : 0;
      } else if (instr == 'R') {
        direction = direction - 1 >= 0 ? direction - 1 : 3;
      }
    }
    return direction != 0 || x == 0 && y == 0;
  }

  public static void main(String[] args) {
    assert isRobotBounded("GLGLGGLGL") == false;
  }
}
