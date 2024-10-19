package myjava;

/*-
https://leetcode.com/problems/container-with-most-water/description/

You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints
of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

---

greather than or equal

height = [1,8,6,2,5,4,8,3,7]
output = 49

height = [1,5,4,8,8,6,2,3,7]
output = 7 * 5 = 35
4 * 7 = 28

height = [0,0,0]
output = 0

height = [1,1,1]
output = 2
*/

public class ContainerWithMostWaterV1 {
  public static int maxArea(int[] height) {
    int biggestArea = Integer.MIN_VALUE;
    int greatest = Integer.MIN_VALUE;
    for (int i = 0; i < height.length; i++) {
      if (height[i] <= greatest) continue;
      greatest = height[i];
      for (int j = i + 1; j < height.length; j++) {
        if (height[j] >= greatest) {
          int area = (j - i) * Math.min(height[i], height[j]);
          if (area > biggestArea) biggestArea = area;
        }
      }
    }

    greatest = Integer.MIN_VALUE;
    for (int i = height.length - 1; i >= 0; i--) {
      if (height[i] <= greatest) continue;
      greatest = height[i];
      for (int j = i - 1; j >= 0; j--) {
        if (height[j] >= greatest) {
          int area = (i - j) * Math.min(height[i], height[j]);
          if (area > biggestArea) biggestArea = area;
        }
      }
    }

    return biggestArea;
  }

  public static void main(String[] args) {
    assert maxArea(new int[]{0,0,0}) == 0;
    assert maxArea(new int[]{1,1,1}) == 2;
    assert maxArea(new int[]{1,8,6,2,5,4,8,3,7}) == 49;
    assert maxArea(new int[]{1,5,4,8,8,6,2,3,7}) == 35;
  }
}
