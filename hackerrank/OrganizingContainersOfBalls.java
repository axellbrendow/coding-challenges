import java.util.*;
import java.util.stream.*;

/*-
https://www.hackerrank.com/challenges/organizing-containers-of-balls/problem

https://stackoverflow.com/a/64087257/4267880

1 3 1
2 1 2
3 3 3

1 3 1
0 1 4
5 3 1

0 4 1
0 1 4
6 2 1

0 5 0
0 0 5
6 2 1

---

1 3 1
2 1 2
3 3 3

3 2 0
0 2 3
3 3 3

5 0 0
0 2 3
1 5 3

----

0 2 1
1 1 1
2 0 0

1 1 1
0 2 1
2 0 0

3 0 0
0 3 0
0 0 2

*/

public class OrganizingContainersOfBalls {
  public static String organizingContainers(List<List<Integer>> container) {
    int[] capacityPerContainer = new int[container.size()];
    int[] ballCountPerType = new int[container.size()];
    for (int i = 0; i < container.size(); i++) {
      for (int j = 0; j < container.size(); j++) {
        capacityPerContainer[i] += container.get(i).get(j);
        ballCountPerType[i] += container.get(j).get(i);
      }
    }
    Arrays.sort(capacityPerContainer);
    Arrays.sort(ballCountPerType);
    for (int i = 0; i < capacityPerContainer.length; i++) {
      if (capacityPerContainer[i] < ballCountPerType[i]) {
        return "Impossible";
      }
    }
    return "Possible";
  }

  public static void main(String[] args) {
    assert organizingContainers(
      List.of(
        List.of(1, 1),
        List.of(1, 1)
      )
    ).equals("Possible");

    assert organizingContainers(
      List.of(
        List.of(0, 1),
        List.of(1, 1)
      )
    ).equals("Possible");

    assert organizingContainers(
      List.of(
        List.of(0, 2),
        List.of(1, 1)
      )
    ).equals("Impossible");
  }
}
