package hundred11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * Suppose you are given the following code:
 *
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // constructor
 *   public void zero(printNumber) { ... }  // only output 0's
 *   public void even(printNumber) { ... }  // only output even numbers
 *   public void odd(printNumber) { ... }   // only output odd numbers
 * }
 * The same instance of ZeroEvenOdd will be passed to three different threads:
 *
 * Thread A will call zero() which should only output 0's.
 * Thread B will call even() which should only ouput even numbers.
 * Thread C will call odd() which should only output odd numbers.
 * Each of the threads is given a printNumber method to output an integer. Modify the given program to output the series 010203040506... where the length of the series must be 2n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: "0102"
 * Explanation: There are three threads being fired asynchronously. One of them calls zero(), the other calls even(), and the last one calls odd(). "0102" is the correct output.
 * Example 2:
 *
 * Input: n = 5
 * Output: "0102030405"
 */
public class _1116_PrintZeroEvenOdd {

    public static void main(String[] args) {

        IntConsumer print = System.out::println;

        int n = 10;

        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd1(n);

        List<Thread> threads = new ArrayList<>();
        Thread zero = new Thread(()->{
            try {
                zeroEvenOdd.zero(print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threads.add(zero);

        for (int i = 0; i < n; i++) {
            if (i%2 == 0) {
                Thread thread = new Thread(()->{
                    try {
                        zeroEvenOdd.even(print);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                threads.add(thread);
            } else {
                Thread thread = new Thread(()->{
                    try {
                        zeroEvenOdd.odd(print);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                threads.add(thread);
            }

        }
        Collections.shuffle(threads);

        for (Thread thread : threads) {
            thread.start();
        }

    }

    static interface ZeroEvenOdd {
        // printNumber.accept(x) outputs "x", where x is an integer.
        void zero(IntConsumer printNumber) throws InterruptedException ;

        void even(IntConsumer printNumber) throws InterruptedException ;

        void odd(IntConsumer printNumber) throws InterruptedException ;
    }

    static class ZeroEvenOdd1 implements ZeroEvenOdd {

        private volatile int count = 0;
        Semaphore zeroHit = new Semaphore(1);
        Semaphore evenHit = new Semaphore(1);
        Semaphore oddHit = new Semaphore(1);

        private int n;

        public ZeroEvenOdd1(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {

            evenHit.acquire();
            oddHit.acquire();

            printNumber.accept(0);
            zeroHit.release();
        }

        public void even(IntConsumer printNumber) throws InterruptedException {

            oddHit.acquire();
            zeroHit.acquire();

            printNumber.accept(++count);
            evenHit.release();
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            evenHit.acquire();
            zeroHit.acquire();

            printNumber.accept(++count);
            oddHit.release();
        }
    }
}
