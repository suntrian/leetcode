package hundred1;

public class _134_GasStation {

    /**
     * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
     *
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next
     * station (i+1). You begin the journey with an empty tank at one of the gas stations.
     *
     * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction,
     * otherwise return -1.
     *
     * Note:
     *
     * If there exists a solution, it is guaranteed to be unique.
     * Both input arrays are non-empty and have the same length.
     * Each element in the input arrays is a non-negative integer.
     * Example 1:
     *
     * Input:
     * gas  = [1,2,3,4,5]
     * cost = [3,4,5,1,2]
     *
     * Output: 3
     *
     * Explanation:
     * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
     * Travel to station 4. Your tank = 4 - 1 + 5 = 8
     * Travel to station 0. Your tank = 8 - 2 + 1 = 7
     * Travel to station 1. Your tank = 7 - 3 + 2 = 6
     * Travel to station 2. Your tank = 6 - 4 + 3 = 5
     * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
     * Therefore, return 3 as the starting index.
     * Example 2:
     *
     * Input:
     * gas  = [2,3,4]
     * cost = [3,4,3]
     *
     * Output: -1
     *
     * Explanation:
     * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
     * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
     * Travel to station 0. Your tank = 4 - 3 + 2 = 3
     * Travel to station 1. Your tank = 3 - 3 + 3 = 3
     * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
     * Therefore, you can't travel around the circuit once no matter where you start.
     */

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int size = gas.length;
        int[] used = new int[size];   //start from i used gas;
        for (int i = 0; i < size*2; i++) {
            for (int j = 0; j < size; j++) {
                if (used[j] < 0 ) continue;
                if (j>i) break;
                used[j] += (gas[i%size] - cost[i%size]);
            }
        }
        for (int i = 0; i < size; i++){
            if (used[i]>=0) {
                return i;
            }
        }
        return -1;
    }

    public static int canCompleteCircuit2(int[] gas, int[] cost) {
        int size = gas.length;
        int start = -1;
        int curCosted = 0;
        int totalCosted = 0;
        for (int i = 0; i < size; i++) {
            int inc = (gas[i] - cost[i]);
            curCosted += inc;
            totalCosted += inc;
            if (curCosted < 0) {
                start = i;
                curCosted = 0;
            }
        }
       return totalCosted<0?-1:start+1;
    }

    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        //System.out.println(canCompleteCircuit(gas, cost));
        int[] gas2 = {3,3,4};
        int[] cost2 = {3,4,4};
        System.out.println(canCompleteCircuit(gas2, cost2));
    }
}
