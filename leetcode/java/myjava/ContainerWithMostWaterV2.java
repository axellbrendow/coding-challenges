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

output = 49
          0 1 2 3 4 5 6 7 8
height = [1,6,9,2,5,4,8,8,3]
          i               j  area = 1 * 8 = 8
            i             j  area = 3 * 7 = 21
            i           j    area = 6 * 6 = 36
              i         j    area = 8 * 5 = 40 <--
              i       j      area = 8 * 4 = 32
              i     j        area = 4 * 3 = 12
              i   j          area = 4 * 2 = 8
              i j            area = 2 * 1 = 2

height = [1,5,4,8,8,6,2,3,7]
output = 7 * 5 = 35
4 * 7 = 28

height = [0,0,0]
output = 0

height = [1,1,1]
output = 2
*/

public class ContainerWithMostWaterV2 {
  public static int maxArea(int[] height) {
    int biggestArea = Integer.MIN_VALUE;
    int i = 0, j = height.length - 1;
    while (i < j) {
      biggestArea = Math.max(biggestArea, (j - i) * Math.min(height[i], height[j]));
      if (height[i] < height[j]) i++;
      else j--;
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
