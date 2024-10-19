package myjava;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class JavaStreams {
  public static void main(String[] args) {
    // IntStream.collect() accepts a `BiConsumer<R, R> combiner` as third argument. It's important to notice
    // that although List::addAll returns boolean, it is accepted as a function (BiConsumer) that returns "void".
    // Ref: https://stackoverflow.com/questions/37308294/why-does-a-java-method-reference-with-return-type-match-the-consumer-interface
    // Related: https://www.infoworld.com/article/2264379/get-started-with-method-references-in-java.html#:~:text=References%20to%20unbound%20non%2Dstatic%20methods
    IntStream.range(0, 3)
      .collect(ArrayList::new, List::add, List::addAll)
      .forEach(System.out::println);

    System.out.println("---");

    IntStream.range(0, 3)
      .boxed()
      // Stream.collect() also accepts a `BiConsumer<R, R> combiner` as third argument.
      .collect(ArrayList::new, List::add, List::addAll)
      .forEach(System.out::println);

    System.out.println("---");

    // Collector.of() accepts a `BinaryOperator<R> combiner` as third argument. That's why List::addAll
    // works with IntStream.collect() or Stream.collect() but doesn't work with collect(Collector.of()).
    IntStream.range(0, 3)
      .boxed()
      .collect(
        Collector.of(ArrayList::new, List::add, (l1, l2) -> {
          l1.addAll(l2);
          return l1;
        })
      )
      .forEach(System.out::println);
  }
}
