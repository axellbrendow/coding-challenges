package myjava;

import java.util.*;

/*-
https://www.lintcode.com/problem/920/

Given an array of meeting time intervals consisting of start and end times [(s1,e1),(s2,e2),...] (si < ei),
determine if a person could attend all meetings.

Example1
Input: intervals = [(0,30),(5,10),(15,20)]
Output: false
Explanation: 
(0,30), (5,10) and (0,30),(15,20) will conflict

Example2
Input: intervals = [(5,8),(9,15)]
Output: true
Explanation: 
Two times will not conflict 
*/

public class MeetingRooms {
  public static boolean canAttendMeetings(List<Interval> intervals) {
    intervals.sort((interval1, interval2) -> interval1.start - interval2.start);
    for (int i = 0; i < intervals.size() - 1; i++)
      if (!(intervals.get(i).end <= intervals.get(i + 1).start)) return false;
    return true;
  }

  public static void main(String[] args) {
    assert canAttendMeetings(new ArrayList<>()) == true;
    assert canAttendMeetings(new ArrayList<>(List.of(new Interval(5, 8), new Interval(9, 15)))) == true;
    assert canAttendMeetings(
      new ArrayList<>(List.of(new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)))
    ) == false;
  }
}

class Interval {
  public int start, end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
}
