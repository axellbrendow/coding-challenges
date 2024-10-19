package myjava.shared;

import java.util.*;

/**
 * Used to parse a string representation of Python arrays, strings and numbers into the Java equivalents.
 * Example:
 * Python: [1, 2, 3]
 * Java: List.of(1, 2, 3) which has the type List<Integer>
 */
public class PythonInterpreter {
  private static Object parse(String str, int[] index) {
    if (str.charAt(index[0]) == '[') {
      List<Object> arr = new ArrayList<Object>();
      do {
        index[0]++;
        arr.add(parse(str, index));
      } while (str.charAt(index[0]) != ']');
      index[0]++;
      return arr;
    } else if ('0' <= str.charAt(index[0]) && str.charAt(index[0]) <= '9') {
      final var sb = new StringBuilder();
      do {
        sb.append(str.charAt(index[0]++));
      } while ('0' <= str.charAt(index[0]) && str.charAt(index[0]) <= '9');
      return Integer.parseInt(sb.toString());
    } else if (str.charAt(index[0]) == '"') {
      index[0]++;
      final var sb = new StringBuilder();
      while (str.charAt(index[0]) != '"') sb.append(str.charAt(index[0]++));
      return sb.toString();
    } else {
      throw new IllegalStateException(
        String.format(
          "Input not recognized index:%d character:%s string:%s",
          index[0],
          str.charAt(index[0]),
          str
        )
      );
    }
  }

  public static Object parse(String str) {
    return parse(str, new int[]{0});
  }
}
