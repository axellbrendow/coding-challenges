package myjava;

import java.util.*;

/*-
1 {
    2 {
        7
    }
    3
    4 {
        5
        6
    }
}
1#3,3,2,4#2,5,6#0#1,5
*/

public class SerializeAndDeserializeNAryTree {
  public String serialize(DirectedGraphNode root) {
    if (root == null) return null;
    StringBuilder strBuilder = new StringBuilder("" + root.label);
    Queue<DirectedGraphNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      DirectedGraphNode node = queue.poll();
      strBuilder.append('#');
      strBuilder.append(node.neighbors.size());
      for (DirectedGraphNode child : node.neighbors) {
        strBuilder.append(',');
        strBuilder.append(child.label);
        queue.offer(child);
      }
    }
    return strBuilder.toString();
  }

  public DirectedGraphNode deserialize(String data) {
    if (data == null) return null;
    String[] childrenInfo = data.split("#");
    int childrenInfoIndex = 1;
    DirectedGraphNode root = new DirectedGraphNode(Integer.parseInt(childrenInfo[0]));
    Queue<DirectedGraphNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      DirectedGraphNode node = queue.poll();
      String[] nodeChildrenInfo = childrenInfo[childrenInfoIndex++].split(",");
      int numChildren = Integer.parseInt(nodeChildrenInfo[0]);
      for (int i = 1; i <= numChildren; i++) {
        int childLabel = Integer.parseInt(nodeChildrenInfo[i]);
        final var childNode = new DirectedGraphNode(childLabel);
        node.neighbors.add(childNode);
        queue.offer(childNode);
      }
    }
    return root;
  }

  public static void main(String[] args) {
    //
  }
}

class DirectedGraphNode {
  int label;
  ArrayList<DirectedGraphNode> neighbors = new ArrayList<>();

  public DirectedGraphNode(int x) {
    this.label = x;
  }
}
