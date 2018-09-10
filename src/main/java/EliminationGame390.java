import java.util.*;

public class EliminationGame390 {

    /**
     * There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every
     * other number afterward until you reach the end of the list.
     *
     * Repeat the previous step again, but this time from right to left, remove the right most number and every other
     * number from the remaining numbers.
     *
     * We keep repeating the steps again, alternating left to right and right to left, until a single number remains.
     *
     * Find the last number that remains starting with a list of length n.
     *
     * Example:
     *
     * Input:
     * n = 9,
     * 1 2 3 4 5 6 7 8 9
     * 2 4 6 8
     * 2 6
     * 6
     *
     * Output:
     * 6
     */
    static int dir = 0; //0-LR,1-RL

    public static int lastRemaining(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <=n; i++) list.add(i);
        while (list.size()>1) {
            Iterator<Integer> itor = list.iterator();
            int i = 0;
            while (itor.hasNext()) {
                itor.next();
                if (i%2==0) itor.remove();
                i++;
            }
            Collections.reverse(list);
        }
        return list.get(0);
    }

    public static int lastRemaining2(int n) {
        if (n < 4) return (n+2) >> 1;
        return (n / 2 & 1) == 0 ? 4 * lastRemaining2(n / 4) - 2 : 4 * lastRemaining2(n / 4);
        //todo;
        //return 0;
    }

    /**
     * each visit has a number at position 0 till the last visit
     * @param n
     * @return
     */
    public static int lastRemaining3(int n) {
        if (n==1) return 1;
        int head = 1, step = 1;
        int leftNumCount = n;
        boolean left2Right = true;
        while (leftNumCount > 1) {
            if (left2Right || leftNumCount%2==1 ) {
                head += step;
            }
            step *=2;
            left2Right = !left2Right;
            leftNumCount /= 2;
        }
        return head;
    }

    /**
     * why ????
     * @param n
     * @return
     */
    public static int lastRemaining4(int n) {
        return ((Integer.highestOneBit(n) - 1) & (n | 0x55555555)) + 1;
    }

    public static void main(String[] args) {
        int target = 535321252;

        long start = System.nanoTime();
        System.out.println(String.format("method1 start at: %s",  start));
        //System.out.println(lastRemaining(target));
        long end = System.nanoTime();
        System.out.println(String.format("method1 finish & method2 start at: %s, method1 cost: %s", end, end-start));
        System.out.println(lastRemaining2(target));
        start = System.nanoTime();
        System.out.println(String.format("method2 finish & method3 start at: %s, method2 cost: %s",  start, start - end));
        System.out.println(lastRemaining3(target));
        end = System.nanoTime();
        System.out.println(String.format("method3 finish at: %s, method3 cost: %s",  end, end - start));
        System.out.println(lastRemaining4(target));
        start = System.nanoTime();
        System.out.println(String.format("method4 finished at %s, method4 cost: %s", start, start-end));
    }
}
