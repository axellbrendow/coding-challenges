package myjava;

import java.util.*;

/*-
https://leetcode.com/problems/design-twitter

Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able
to see the 10 most recent tweets in the user's news feed.

Implement the Twitter class:

Twitter() Initializes your twitter object.

void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to
this function will be made with a unique tweetId.

List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item
in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered
from most recent to least recent.

void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID
followeeId.

void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with
ID followeeId.

Example 1:

Input
["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
[[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
Output
[null, null, [5], null, null, [6, 5], null, [5]]

Explanation
Twitter twitter = new Twitter();
twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
twitter.follow(1, 2);    // User 1 follows user 2.
twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
  Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.unfollow(1, 2);  // User 1 unfollows user 2.
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1
  is no longer following user 2.

Constraints:

1 <= userId, followerId, followeeId <= 500
0 <= tweetId <= 10^4
All the tweets have unique IDs.
At most 3 * 10^4 calls will be made to postTweet, getNewsFeed, follow, and unfollow.
*/

record Tweet(int time, int tweetId) {
  public static final Comparator<Tweet> comparator = (t1, t2) -> t1.time() - t2.time();
}

class User {
  public Queue<Tweet> tweets = new PriorityQueue<>(Tweet.comparator);
  public Set<Integer> followerIds = new HashSet<>();
  public Set<Integer> followeeIds = new HashSet<>();
  public Queue<Tweet> newsFeed = new PriorityQueue<>(Tweet.comparator);
}

class Twitter {
  private Map<Integer, User> users = new HashMap<>();
  private int time = 0;

  public Twitter() {
  }

  public void postTweet(int userId, int tweetId) {
    User user = users.get(userId);
    if (user == null) users.put(userId, user = new User());
    Tweet tweet = new Tweet(this.time++, tweetId);
    user.tweets.offer(tweet);
    if (user.tweets.size() > 10) user.tweets.poll();

    user.newsFeed.offer(tweet);
    if (user.newsFeed.size() > 10) user.newsFeed.poll();

    user.followerIds.forEach(followerId -> {
      User follower = users.get(followerId);
      follower.newsFeed.offer(tweet);
      if (follower.newsFeed.size() > 10) follower.newsFeed.poll();
    });
  }

  public List<Integer> getNewsFeed(int userId) {
    User user = users.get(userId);
    if (user == null) return List.of();
    Queue<Tweet> newsFeedCopy = new PriorityQueue<>(Tweet.comparator);
    newsFeedCopy.addAll(user.newsFeed);
    List<Integer> list = new ArrayList<>();
    while (!newsFeedCopy.isEmpty()) list.add(newsFeedCopy.poll().tweetId());
    Collections.reverse(list);
    return list;
  }

  public void follow(int followerId, int followeeId) {
    User follower = users.get(followerId);
    User followee = users.get(followeeId);
    if (follower == null) users.put(followerId, follower = new User());
    if (followee == null) users.put(followeeId, followee = new User());
    if (follower.followeeIds.contains(followeeId)) return;
    followee.followerIds.add(followerId);
    follower.followeeIds.add(followeeId);
    follower.newsFeed.addAll(followee.tweets);
    while (follower.newsFeed.size() > 10) follower.newsFeed.poll();
  }

  public void unfollow(int followerId, int followeeId) {
    User followee = users.get(followeeId);
    User follower = users.get(followerId);
    if (follower == null || followee == null || !follower.followeeIds.contains(followeeId)) return;
    followee.followerIds.remove(followerId);
    follower.followeeIds.remove(followeeId);
    follower.newsFeed = new PriorityQueue<>(Tweet.comparator);
    follower.newsFeed.addAll(follower.tweets);
    follower.followeeIds.forEach(newFolloweeId -> {
      follower.newsFeed.addAll(users.get(newFolloweeId).tweets);
      while (follower.newsFeed.size() > 10) follower.newsFeed.poll();
    });
  }
}

public class DesignTwitter {
  public static void main(String[] args) {
    Twitter twitter;

    twitter = new Twitter();
    twitter.postTweet(1, 5);
    assert twitter.getNewsFeed(1).equals(List.of(5));
    twitter.follow(1, 2);
    twitter.postTweet(2, 6);
    assert twitter.getNewsFeed(1).equals(List.of(6, 5));
    twitter.unfollow(1, 2);
    assert twitter.getNewsFeed(1).equals(List.of(5));

    twitter = new Twitter();
    twitter.postTweet(1, 1);
    assert twitter.getNewsFeed(1).equals(List.of(1));
    twitter.follow(2, 1);
    assert twitter.getNewsFeed(2).equals(List.of(1));
    twitter.unfollow(2, 1);
    assert twitter.getNewsFeed(2).isEmpty();

    twitter = new Twitter();
    assert twitter.getNewsFeed(1).isEmpty();

    twitter = new Twitter();
    twitter.postTweet(1, 5);
    twitter.postTweet(1, 3);
    assert twitter.getNewsFeed(1).equals(List.of(3, 5));

    twitter = new Twitter();
    twitter.postTweet(2, 5);
    twitter.follow(1, 2);
    twitter.follow(1, 2);
    assert twitter.getNewsFeed(1).equals(List.of(5));

    twitter = new Twitter();
    twitter.unfollow(1, 2);
  }
}
