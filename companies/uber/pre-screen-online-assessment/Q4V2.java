import java.util.*;

/*-
String func(int[][] queries)

queries = [
  [1, x], // 1 means place one obstacle in the x coordinate
  [2, x, size] // 2 means check if it is possible to build something in the range (x - (size - 1), x + (size - 1))
]

-10^9 <= x <= 10^9

For operation 2, given x, I need to know the biggest number less than x and the smallest number greater than x and
check if these numbers are outside the range (x - (size - 1), x + (size - 1))

It's possible to achieve this with a TreeMap which has ordered keys
Or I can create an array and insert elements on it with binary search
*/

public class Q4V2 {
  public static String func(int[][] queries) {
    String output = "";
    // lower, floor, ceiling, higher methods
    NavigableSet<Integer> integers = new TreeSet<>();
    for (int i = 0; i < queries.length; i++) {
      if (queries[i][0] == 1) {
        integers.add(queries[i][1]);
      } else {
        int x = queries[i][1];
        int size = queries[i][2];
        Integer lowerThanX = integers.lower(x); // log(n)
        Integer higherThanX = integers.higher(x); // log(n)
        output += (!integers.contains(x) // uses TreeMap's containsKey internally so it's log(n)
            && (lowerThanX == null || lowerThanX <= x - size)
            && (higherThanX == null || higherThanX >= x + size)) ? "1" : "0";
      }
    }
    return output;
  }

  public static void main(String[] args) {
    int[][] queries;

    queries = new int[][] {};
    assert func(queries).equals("");

    queries = new int[][] {
        new int[] { 2, 0, 2 }
    };
    assert func(queries).equals("1");

    queries = new int[][] {
        new int[] { 1, 0 },
        new int[] { 2, 0, 2 }
    };
    assert func(queries).equals("0");

    queries = new int[][] {
        new int[] { 1, 1 },
        new int[] { 2, 0, 2 }
    };
    assert func(queries).equals("0");

    queries = new int[][] {
        new int[] { 1, -1 },
        new int[] { 2, 0, 2 }
    };
    assert func(queries).equals("0");

    queries = new int[][] {
        new int[] { 1, -2 },
        new int[] { 2, 0, 2 }
    };
    assert func(queries).equals("1");
  }
}
