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

public class CodingInterviewV2 {
  // time: O(len(word) * len(titles))
  // space: O(len(word))
  private static boolean canConstruct(String word, String[] titles, int i, Boolean[] dp) {
    if (i > word.length()) return false;
    if (i == word.length()) return true;
    if (dp[i] != null) return dp[i];
    for (String title : titles)
      if (word.startsWith(title, i) && canConstruct(word, titles, i + title.length(), dp))
        return dp[i] = true;
    return dp[i] = false;
  }

  private static boolean canConstruct(String word, String[] titles) {
    return canConstruct(word, titles, 0, new Boolean[word.length()]);
  }

  public static void main(String[] args) {
    assert canConstruct("wdcaatca", new String[]{"ca", "at", "wd"});
    assert !canConstruct("cat", new String[]{"ca", "at"});
    assert canConstruct("caat", new String[]{"ca", "caat"});
  }
}
