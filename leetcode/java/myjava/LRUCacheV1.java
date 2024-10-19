package myjava;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/*-
https://leetcode.com/problems/lru-cache/description/

Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists.
  Otherwise, add the key-value pair to the cache. If the number of keys exceeds the
  capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.
*/

/*-
Clarifying questions:
Is there a time or space complexity constraint for get() or put() ?
Which of these functions will be called more often ? Should I optimize for one of them ?
  (Answer: At most 2 * 10^5 calls will be made to get and put.)
*/

/*-
capacity = 1
get(5) return -1
put(0, 1)
get(1) return -1
get(0) return 1
put(0, 2)
get(0) return 2
put(1, 3)
get(0) return -1
get(1) return 3

get(0) -> hashmap to the node of the value associated with the key
put(0, 2) ->
  - when the capacity is full and I'm inserting a new key, I should be able to have fast
    access to the least recently used key and I also need to put the new key as the most recently used.
  - if the key already exists in the LRUCache, then it should be update to be the most recently used.
*/

public class LRUCacheV1 {
  private int capacity;
  private Map<Integer, DoublyLinkedList.Node> nodeMap = new HashMap<>();
  private DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

  /*-
  Clarifying questions:
  - Is capacity a byte/short/integer/long/BigInteger ?
  - What are the constraints of capacity ? 1 <= capacity <= Integer.MAX_VALUE ? (Answer: 1 <= capacity <= 3000)
  */
  public LRUCacheV1(int capacity) {
    this.capacity = capacity;
  }

  /*-
  Clarifying questions:
  - Is the type of `key` a primitive ? Is it a string ? (Answer: integer)
  - What should be returned if the key doesn't exist ? (Answer: -1)
  - So are values integers ? (Answer: yes)
  - Will a key be mapped to the value -1 causing confusion ? (Answer: no, val >= 0)
  */
  public int get(int key) {
    final DoublyLinkedList.Node keyNode = nodeMap.get(key);
    if (keyNode == null) return -1;
    doublyLinkedList.remove(keyNode);
    final DoublyLinkedList.Node newNode = doublyLinkedList.offerFirst(keyNode.key, keyNode.val);
    nodeMap.put(key, newNode);
    return keyNode.val;
  }

  /*-
  Clarifying questions:
  - Is the type of `value` a primitive ? Is it a string ? (Answer: integer)
  */
  public void put(int key, int value) {
    if (!nodeMap.containsKey(key)) {
      if (nodeMap.size() == capacity) {
        final DoublyLinkedList.Node removedNode = doublyLinkedList.pollLast();
        nodeMap.remove(removedNode.key);
      }
    } else {
      final DoublyLinkedList.Node node = nodeMap.get(key);
      doublyLinkedList.remove(node);
      nodeMap.remove(node.key);
    }

    final DoublyLinkedList.Node newNode = doublyLinkedList.offerFirst(key, value);
    nodeMap.put(newNode.key, newNode);
  }

  public static void main(String[] args) {
    LRUCacheV1 lRUCache;

    lRUCache = new LRUCacheV1(2);
    lRUCache.put(1, 1); // cache is {1=1}
    lRUCache.put(2, 2); // cache is {1=1, 2=2}
    assert lRUCache.get(1) == 1;
    lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    assert lRUCache.get(2) == -1;
    lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    assert lRUCache.get(1) == -1;
    assert lRUCache.get(3) == 3;
    assert lRUCache.get(4) == 4;

    lRUCache = new LRUCacheV1(1);
    lRUCache.put(2, 1); // cache is {2=1}
    assert lRUCache.get(2) == 1;
    lRUCache.put(3, 2); // cache is {3=2}
    assert lRUCache.get(2) == -1;
    assert lRUCache.get(3) == 2;

    lRUCache = new LRUCacheV1(2);
    lRUCache.put(2, 1); // cache is {2=1}
    lRUCache.put(2, 2); // cache is {2=2}
    assert lRUCache.get(2) == 2;
    lRUCache.put(1, 1); // cache is {2=2, 1=1}
    lRUCache.put(4, 1); // cache is {1=1, 4=1}
    assert lRUCache.get(2) == -1;

    lRUCache = new LRUCacheV1(10);
    lRUCache.put(10, 13);
    lRUCache.put(3, 17);
    lRUCache.put(6, 11);
    lRUCache.put(10, 5);
    lRUCache.put(9, 10);
    assert lRUCache.get(13) == -1;
    lRUCache.put(2, 19);
    assert lRUCache.get(2) == 19;
    assert lRUCache.get(3) == 17;
    lRUCache.put(5, 25);
    assert lRUCache.get(8) == -1;
    lRUCache.put(9, 22);
    lRUCache.put(5, 5);
    lRUCache.put(1, 30);
    assert lRUCache.get(11) == -1;
    lRUCache.put(9, 12);
    assert lRUCache.get(7) == -1;
    assert lRUCache.get(5) == 5;
    assert lRUCache.get(8) == -1;
    assert lRUCache.get(9) == 12;
    lRUCache.put(4, 30);
    lRUCache.put(9, 3);
    assert lRUCache.get(9) == 3;
    assert lRUCache.get(10) == 5;
    assert lRUCache.get(10) == 5;
    lRUCache.put(6, 14);
    lRUCache.put(3, 1);
    assert lRUCache.get(3) == 1;
    lRUCache.put(10, 11);
    assert lRUCache.get(8) == -1;
    lRUCache.put(2, 14);
    assert lRUCache.get(1) == 30;
    assert lRUCache.get(5) == 5;
    assert lRUCache.get(4) == 30;
    lRUCache.put(11, 4);
    lRUCache.put(12, 24);
    lRUCache.put(5, 18);
    assert lRUCache.get(13) == -1;
    lRUCache.put(7, 23);
    assert lRUCache.get(8) == -1;
    assert lRUCache.get(12) == 24;
    lRUCache.put(3, 27);
    lRUCache.put(2, 12);
    assert lRUCache.get(5) == 18;
    lRUCache.put(2, 9);
    lRUCache.put(13, 4);
    lRUCache.put(8, 18);
    lRUCache.put(1, 7);
    assert lRUCache.get(6) == -1;
    lRUCache.put(9, 29);
    lRUCache.put(8, 21);
    assert lRUCache.get(5) == 18;
    lRUCache.put(6, 30);
    lRUCache.put(1, 12);
    assert lRUCache.get(10) == -1;
    lRUCache.put(4, 15);
    lRUCache.put(7, 22);
    lRUCache.put(11, 26);
    lRUCache.put(8, 17);
    lRUCache.put(9, 29);
    assert lRUCache.get(5) == 18;
    lRUCache.put(3, 4);
    lRUCache.put(11, 30);
    assert lRUCache.get(12) == -1;
    lRUCache.put(4, 29);
    assert lRUCache.get(3) == 4;
    assert lRUCache.get(9) == 29;
    assert lRUCache.get(6) == 30;
    lRUCache.put(3, 4);
    assert lRUCache.get(1) == 12;
    assert lRUCache.get(10) == -1;
    lRUCache.put(3, 29);
    lRUCache.put(10, 28);
    lRUCache.put(1, 20);
    lRUCache.put(11, 13);
    assert lRUCache.get(3) == 29;
    lRUCache.put(3, 12);
    lRUCache.put(3, 8);
    lRUCache.put(10, 9);
    lRUCache.put(3, 26);
    assert lRUCache.get(8) == 17;
    assert lRUCache.get(7) == 22;
    assert lRUCache.get(5) == 18;
    lRUCache.put(13, 17);
    lRUCache.put(2, 27);
    lRUCache.put(11, 15);
    assert lRUCache.get(12) == -1;
    lRUCache.put(9, 19);
    lRUCache.put(2, 15);
    lRUCache.put(3, 16);
    assert lRUCache.get(1) == 20;
    lRUCache.put(12, 17);
    lRUCache.put(9, 1);
    lRUCache.put(6, 19);
    assert lRUCache.get(4) == -1;
    assert lRUCache.get(5) == 18;
    assert lRUCache.get(5) == 18;
    lRUCache.put(8, 1);
    lRUCache.put(11, 7);
    lRUCache.put(5, 2);
    lRUCache.put(9, 28);
    assert lRUCache.get(1) == 20;
    lRUCache.put(2, 2);
    lRUCache.put(7, 4);
    lRUCache.put(4, 22);
    lRUCache.put(7, 24);
    lRUCache.put(9, 26);
    lRUCache.put(13, 28);
    lRUCache.put(11, 26);
  }
}

class DoublyLinkedList {
  private Node head = new Node();
  private Node lastNode = null;

  public Node offerFirst(int key, int val) {
    final var newNode = new Node(key, val, head, head.next);
    if (head.next != null) head.next.prev = newNode;
    head.next = newNode;
    if (lastNode == null) lastNode = newNode;
    else if (lastNode.next != null) lastNode = lastNode.next;
    return newNode;
  }

  public void remove(final Node node) {
    if (node == null || node == head) return;
    if (node == lastNode) lastNode = node.prev;
    node.prev.next = node.next;
    if (node.next != null) node.next.prev = node.prev;
  }

  public Node pollLast() {
    final Node removedNode = lastNode;
    remove(lastNode);
    return removedNode;
  }

  @Override
  public String toString() {
    return head.toString();
  }

  // A static nested class is just a nested class that doesn't require an instance of its enclosing class.
  public static class Node {
    public int key;
    public int val;
    public Node prev;
    public Node next;

    public Node() {
    }

    public Node(int key, int val, Node prev, Node next) {
      this.key = key;
      this.val = val;
      this.prev = prev;
      this.next = next;
    }

    @Override
    public String toString() {
      return String.format("k=%s v=%s -> %s", key, val, next);
    }
  }
}
