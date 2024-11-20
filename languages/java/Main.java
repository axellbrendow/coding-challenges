import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class Main {
  private static void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
    }
  }

  public static void main(String[] args) throws Exception {
    System.out.println();
    System.out.println(">>> ༼ つ ◕_◕ ༽つ concurrency, threads, locks");
    System.out.println();

    /*-
    Producer Consumer Problem
    
    ---
    
    https://stackoverflow.com/a/21633319/4267880 Is wait(1) in a non-blocking while(true)-loop more efficient than using wait() and notify()?
    https://stackoverflow.com/a/36311382/4267880 Why prefer wait/notify to while cycle?
    https://stackoverflow.com/a/3186336/4267880 Java: notify() vs. notifyAll() all over again
    https://stackoverflow.com/a/2087046/4267880 wait and notify in C/C++ shared memory
    
    Why should you use wait() and notifyAll() ?
    
    There are 2 alternatives, both are bad:
    
    1 - Create an infinite while loop in each thread checking for a condition. The problem with this approach
    is that it consumes a lot of energy and CPU time from other threads.
    
    2 - same as approach 1 but adding a Thread.sleep() inside the while loop. The problem with this approach
    is that you lose precision and add latency to your application. For real-time applications that's unacceptable.
    
    ---
    
    https://stackoverflow.com/a/13664082/4267880
    The synchronized keyword doesn't block only the method it is declared. It blocks other synchronized
    methods of the same object too. Remember, synchronized creates lock on the object itself "this".
    When a thread enters the WAIT state, it releases the lock it acquired to enter in the current scope.
    When a thread is waken up, it will fight again for the lock to enter in the current scope.
    */

    class ProducerConsumer {
      private static final int MAX_CAPACITY = 15;

      private Queue<Integer> queue = new LinkedList<>();

      // VOLATILE. The 2 producer threads need to see the same value for this variable.
      private volatile int value = 0;

      public synchronized void consume() {
        while (queue.isEmpty()) {
          try {
            wait();
          } catch (InterruptedException e) {
          }
        }

        System.out.printf(
          "thread: %d, consumed: %4s, queue: %s\n", Thread.currentThread().getId(), queue.poll(), queue
        );
        notifyAll();
      }

      public synchronized void produce() {
        while (queue.size() == MAX_CAPACITY) {
          try {
            wait();
          } catch (InterruptedException e) {
          }
        }

        queue.offer(++value);
        System.out.printf(
          "thread: %d, produced: %4s, queue: %s\n", Thread.currentThread().getId(), value, queue
        );
        notifyAll();
      }
    }

    final var producerConsumer = new ProducerConsumer();
    final var consumerThread1 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        producerConsumer.consume();
        sleep(1);
      }
    });
    final var consumerThread2 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        producerConsumer.consume();
        sleep(1);
      }
    });
    final var producerThread1 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        producerConsumer.produce();
        sleep(1);
      }
    });
    final var producerThread2 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        producerConsumer.produce();
        sleep(1);
      }
    });
    consumerThread1.start();
    consumerThread2.start();
    producerThread1.start();
    producerThread2.start();
    consumerThread1.join();
    consumerThread2.join();
    producerThread1.join();
    producerThread2.join();
  }
}
