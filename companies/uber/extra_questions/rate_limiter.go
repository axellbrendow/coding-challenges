package main

import (
	"sync"
	"time"
)

/*
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

const maxRequestsPerSecond = 100
const cleanupInterval = 1000 * 2

type RateLimiter struct {
	mutex          sync.Mutex
	stopTicker     chan bool
	numRequestsMap map[string]map[int64]int
}

func newRateLimiter() *RateLimiter {
	rateLimiter := RateLimiter{}
	rateLimiter.stopTicker = make(chan bool)
	rateLimiter.numRequestsMap = make(map[string]map[int64]int)
	ticker := time.NewTicker(time.Millisecond * cleanupInterval)
	go func() {
		for {
			select {
			case <-ticker.C:
				rateLimiter.mutex.Lock()
				var currentTimeSeconds int64 = time.Now().Unix()
				for _, clientNumRequestsMap := range rateLimiter.numRequestsMap {
					for timeSeconds := range clientNumRequestsMap {
						if timeSeconds < currentTimeSeconds {
							delete(clientNumRequestsMap, timeSeconds)
						}
					}
				}
			case <-rateLimiter.stopTicker:
				ticker.Stop()
			}
		}
	}()
	return &rateLimiter
}

func (rateLimiter *RateLimiter) allow(clientId string) bool {
	rateLimiter.mutex.Lock()
	defer rateLimiter.mutex.Unlock()
	var currentTimeSeconds int64 = time.Now().Unix()
	if _, hasKey := rateLimiter.numRequestsMap[clientId]; !hasKey {
		rateLimiter.numRequestsMap[clientId] = make(map[int64]int)
	}
	numRequests := rateLimiter.numRequestsMap[clientId][currentTimeSeconds] + 1
	rateLimiter.numRequestsMap[clientId][currentTimeSeconds] = numRequests
	return numRequests <= maxRequestsPerSecond
}

func main() {
	rateLimiter := newRateLimiter()
	for i := 0; i < maxRequestsPerSecond; i++ {
		if rateLimiter.allow("client1") != true {
			panic("")
		}
	}
	if rateLimiter.allow("client1") != false {
		panic("")
	}

	if rateLimiter.allow("clearClients") != true {
		panic("")
	}
	time.Sleep(time.Millisecond * (cleanupInterval + 100))
	if len(rateLimiter.numRequestsMap["clearClients"]) != 0 {
		panic("")
	}

	rateLimiter.stopTicker <- true // Good practice to stop the ticker
}
