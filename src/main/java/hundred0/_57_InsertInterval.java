package hundred0;

import util.Unsolved;

import java.util.ArrayList;
import java.util.List;

@Unsolved
public class _57_InsertInterval {

    /**
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
     *
     * You may assume that the intervals were initially sorted according to their start times.
     *
     * Example 1:
     *
     * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
     * Output: [[1,5],[6,9]]
     * Example 2:
     *
     * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * Output: [[1,2],[3,10],[12,16]]
     * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
     */

    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public static List<Interval> insert(List<Interval> intervals, final Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        if (intervals.isEmpty()) {
            result.add(newInterval);
            return result;
        }
        for (int i = 0; i < intervals.size(); i++) {
            //todo;
        }
        return result;
    }

    public static void main(String[] args) {
        Interval a1 = new Interval(1,5);
        //Interval a2 = new Interval(6, 9);
        List<Interval> in1 = new ArrayList<>();
        in1.add(a1);
        //in1.add(a2);

        Interval in2 = new Interval(2,3);
        insert(in1, in2);

    }
}
