package myjava;

import java.util.*;

/*-
https://www.lintcode.com/problem/659/

Encode and Decode Strings

Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the
network and is decoded back to the original list of strings.

Please implement encode and decode

strs = ["abc,", "def\"]
str = "abc\,,def\\"

strs = ["", ""]
str = ","

strs = [""]
str = ""

strs = []
str = null
*/

public class EncodeAndDecodeStrings {
  public static String encode(List<String> strs) {
    if (strs.isEmpty()) return null;
    String encoded = "";
    for (String str : strs) {
      for (int i = 0; i < str.length(); i++) {
        if (str.charAt(i) == ',' || str.charAt(i) == '\\') encoded += "\\";
        encoded += str.charAt(i);
      }
      encoded += ",";
    }
    return encoded;
  }

  public static List<String> decode(String str) {
    if (str == null) return List.of();
    str = str.substring(0, str.length() - 1);
    if (str.isEmpty()) return List.of("");
    List<String> list = new ArrayList<>();
    String newStr = "";
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == ',') {
        list.add(newStr);
        newStr = "";
        continue;
      }
      if (str.charAt(i) == '\\') i++;
      newStr += str.charAt(i);
    }
    list.add(newStr);
    return list;
  }

  public static void main(String[] args) {
    assert encode(List.of()) == null;
    assert decode(null).equals(List.of());

    assert encode(List.of("")).equals(",");
    assert decode(",").equals(List.of(""));

    assert encode(List.of("", "")).equals(",,");
    assert decode(",,").equals(List.of("", ""));

    assert encode(List.of("a", "b")).equals("a,b,");
    assert decode("a,b,").equals(List.of("a", "b"));
  }
}
