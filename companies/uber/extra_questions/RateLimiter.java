package uber.extra_questions;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/*-
https://leetcode.com/discuss/interview-question/system-design/124558/Uber-or-Rate-Limiter

Whenever you expose a web service / api endpoint, you need to implement a rate limiter to prevent abuse of
the service (DOS attacks).

Implement a RateLimiter Class with an isAllow method. Every request comes in with a unique clientID, deny a
request if that client has made more than 100 requests in the past second.

---

clientId  numRequests second
1         57          1

map[clientId][second] += 1

Important clarifying questions:
- Do I need to handle multiple threads accessing the same RateLimiter instance ? (very important question)
- Is it ok if I allow a little bit more requests than the 100 limit ?
*/

public class RateLimiter {
  private static final long MAX_REQUESTS_PER_SECOND = 100;
  private static final long CLEANUP_INTERVAL = 1000 * 2;

  private Timer timer = new Timer();

  private ConcurrentMap<String, ConcurrentMap<Long, AtomicInteger>> numRequestsMap =
    new ConcurrentHashMap<>();

  private long currentTimeSeconds() {
    return System.currentTimeMillis() / 1000;
  }

  public RateLimiter() {
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        long currentTimeSeconds = currentTimeSeconds();
        numRequestsMap.forEach((clientId, clientNumRequestsMap) -> {
          clientNumRequestsMap.keySet().forEach(timeSeconds -> {
            if (timeSeconds < currentTimeSeconds) clientNumRequestsMap.remove(timeSeconds);
          });
          ;
        });
      }
    }, 0, CLEANUP_INTERVAL);
  }

  public boolean allow(String clientId) {
    long currentTimeSeconds = currentTimeSeconds();
    ConcurrentMap<Long, AtomicInteger> emptyClientRequests = new ConcurrentHashMap<>();
    ConcurrentMap<Long, AtomicInteger> clientRequests = numRequestsMap.putIfAbsent(
      clientId, emptyClientRequests
    );
    if (clientRequests == null) clientRequests = emptyClientRequests;

    AtomicInteger zero = new AtomicInteger(0);
    AtomicInteger atomicNumRequests = clientRequests.putIfAbsent(currentTimeSeconds, zero);
    if (atomicNumRequests == null) atomicNumRequests = zero;
    return atomicNumRequests.incrementAndGet() <= MAX_REQUESTS_PER_SECOND;
  }

  public static void main(String[] args) throws Exception {
    final var rateLimiter = new RateLimiter();
    for (int i = 0; i < MAX_REQUESTS_PER_SECOND; i++) {
      assert rateLimiter.allow("client1") == true;
    }
    assert rateLimiter.allow("client1") == false;

    assert rateLimiter.allow("clearClients") == true;
    Thread.sleep(CLEANUP_INTERVAL + 100); // The timer thread is different from the main thread
    assert rateLimiter.numRequestsMap.get("clearClients").isEmpty() == true;

    // The programs never ends if I don't cancel the timer because it has its own thread
    rateLimiter.timer.cancel();
  }
}
