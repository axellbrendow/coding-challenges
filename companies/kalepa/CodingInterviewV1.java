package kalepa;

/*-
You get a set of titles with some strings on them and a word. Write a function that determines whether the
word can be constructed using the titles. Any title can be reused multiple times.

Examples:
titles = {"ca", "at"}
word = "cat"
output: False

titles = {"ca", "at"}
word = "caat"
output: True

titles = {"ca", "at"}
word = "atcaca"
output: True

titles = {"ca", "at", "wd"}
word = "wdcaatca"
output: True

titles = {"ca", "caat"}
word = "caat"
output: True

titles = {"ca", "caca"}
word = "cacaca"
output: True
*/

public class CodingInterviewV1 {
  // worst case happens when it's not possible to build the word from the list of titles
  // time: O(len(titles)^len(word))
  // space: O(len(word))
  private static boolean canConstruct(String word, String[] titles, int i) {
    if (i > word.length()) return false;
    if (i == word.length()) return true;
    for (String title : titles)
      if (word.startsWith(title, i) && canConstruct(word, titles, i + title.length()))
        return true;
    return false;
  }

  public static void main(String[] args) {
    assert canConstruct("wdcaatca", new String[]{"ca", "at", "wd"}, 0);
    assert !canConstruct("cat", new String[]{"ca", "at"}, 0);
    assert canConstruct("caat", new String[]{"ca", "caat"}, 0);
  }
}
