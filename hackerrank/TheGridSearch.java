import java.util.*;

/*-
https://www.hackerrank.com/challenges/the-grid-search/problem

Given an array of strings of digits, try to find the occurrence of a given pattern of digits. In the grid and
pattern arrays, each string represents a row in the grid. For example, consider the following grid:

1234567890  
0987654321  
1111111111  
1111111111  
2222222222  

The pattern array is:

876543  
111111  
111111

The pattern begins at the second row and the third column of the grid and continues in the following two rows.
The pattern is said to be present in the grid. The return value should be YES or NO, depending on whether the
pattern is found. In this case, return YES.
*/

public class TheGridSearch {
  public static String gridSearch(List<String> G, List<String> P) {
    for (int i = 0; i <= G.size() - P.size(); i++) {
      int index = G.get(i).indexOf(P.get(0));
      while (index != -1) {
        boolean match = true;
        for (int j = 1; j < P.size(); j++) {
          if (!G.get(i + j).substring(index, index + P.get(0).length()).equals(P.get(j))) {
            match = false;
            break;
          }
        }
        if (match) return "YES";
        index = G.get(i).indexOf(P.get(0), index + 1);
      }
    }
    return "NO";
  }

  public static void main(String[] args) {
    assert gridSearch(
      List.of("1"),
      List.of("1")
    ).equals("YES");

    assert gridSearch(
      List.of("1"),
      List.of(
        "1",
        "1"
      )
    ).equals("NO");

    assert gridSearch(
      List.of(
        "1234567890",
        "0987654321",
        "1111111111",
        "1111111111",
        "2222222222"
      ),
      List.of(
        "1234567890",
        "0987654321",
        "1111111111",
        "1111111111",
        "2222222222"
      )
    ).equals("YES");

    assert gridSearch(
      List.of(
        "123412",
        "561212",
        "123634",
        "781288"
      ),
      List.of(
        "12",
        "34"
      )
    ).equals("YES");

    assert gridSearch(
      List.of(
        "400453592126560",
        "114213133098692",
        "474386082879648",
        "522356951189169",
        "887109450487496",
        "252802633388782",
        "502771484966748",
        "075975207693780",
        "511799789562806",
        "404007454272504",
        "549043809916080",
        "962410809534811",
        "445893523733475",
        "768705303214174",
        "650629270887160"
      ),
      List.of(
        "99",
        "99"
      )
    ).equals("NO");

    assert gridSearch(
      List.of(
        "7283455864",
        "6731158619",
        "8988242643",
        "3830589324",
        "2229505813",
        "5633845374",
        "6473530293",
        "7053106601",
        "0834282956",
        "4607924137"
      ),
      List.of(
        "9505",
        "3845",
        "3530"
      )
    ).equals("YES");
  }
}
