package myjava;

import java.util.*;
import java.util.stream.*;

class MedianFinder {
  private Queue<Integer> smallestValues = new PriorityQueue<>(Comparator.reverseOrder());
  private Queue<Integer> biggestValues = new PriorityQueue<>(Comparator.naturalOrder());

  public void addNum(int num) {
    if (!this.biggestValues.isEmpty() && num > this.biggestValues.peek()) {
      this.biggestValues.offer(num);
      if (this.biggestValues.size() > this.smallestValues.size())
        this.smallestValues.offer(this.biggestValues.poll());
    } else {
      this.smallestValues.offer(num);
      if (this.smallestValues.size() - this.biggestValues.size() == 2)
        this.biggestValues.offer(this.smallestValues.poll());
    }
  }

  public double findMedian() {
    if (this.smallestValues.size() == this.biggestValues.size())
      return (this.smallestValues.peek() + this.biggestValues.peek()) / 2.0;
    return this.smallestValues.peek();
  }
}

public class FindMedianFromDataStream {
  public static void main(String[] args) {
    MedianFinder medianFinder;

    medianFinder = new MedianFinder();
    medianFinder.addNum(1);
    medianFinder.addNum(2);
    assert medianFinder.findMedian() == (1 + 2) / 2.0;
    medianFinder.addNum(3);
    assert medianFinder.findMedian() == 2.0;
  }
}
