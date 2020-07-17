import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Suppose we have a class:
 *
 * public class Foo {
 *   public void first() { print("first"); }
 *   public void second() { print("second"); }
 *   public void third() { print("third"); }
 * }
 * The same instance of Foo will be passed to three different threads. Thread A will call first(), thread B will call second(), and thread C will call third(). Design a mechanism and modify the program to ensure that second() is executed after first(), and third() is executed after second().
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: "firstsecondthird"
 * Explanation: There are three threads being fired asynchronously. The input [1,2,3] means thread A calls first(), thread B calls second(), and thread C calls third(). "firstsecondthird" is the correct output.
 * Example 2:
 *
 * Input: [1,3,2]
 * Output: "firstsecondthird"
 * Explanation: The input [1,3,2] means thread A calls first(), thread B calls third(), and thread C calls second(). "firstsecondthird" is the correct output.
 *
 *
 * Note:
 *
 * We do not know how the threads will be scheduled in the operating system, even though the numbers in the input seems to imply the ordering. The input format you see is mainly to ensure our tests' comprehensiveness.
 */
public class PrintInOrder1114 {


    public static void main(String[] args) throws IOException {
        Foo1 foo1 = new Foo1();
        Foo2 foo2 = new Foo2();
        Foo3 foo3 = new Foo3();
        Foo4 foo4 = new Foo4();
        Foo5 foo5 = new Foo5();
        Foo6 foo6 = new Foo6();
        Foo7 foo7 = new Foo7();
        Foo8 foo8 = new Foo8();
        Foo9 foo9 = new Foo9();

        Runnable first = ()-> System.out.println("first");
        Runnable second = ()-> System.out.println("second");
        Runnable third = ()-> System.out.println("third");

        final Foo1 foo = foo1;
        Thread thread1 = new Thread(()-> {
            try {
                foo.first(first);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(()->{
            try {
                foo.second(second);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread3 = new Thread(()->{
            try {
                foo.third(third);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread3.start();
        thread2.start();
        thread1.start();

        System.in.read();
    }


    static class Foo1 {

        private final CountDownLatch countDownLatch1 = new CountDownLatch(1);
        private final CountDownLatch countDownLatch2 = new CountDownLatch(1);

        public Foo1() {

        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            countDownLatch1.countDown();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            countDownLatch1.await();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            countDownLatch2.countDown();
        }

        public void third(Runnable printThird) throws InterruptedException {
            countDownLatch2.await();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    static class Foo2 {

        private Semaphore semaphore2 = new Semaphore(1);
        private Semaphore semaphore3 = new Semaphore(1);

        public Foo2() {
            try {
                semaphore2.acquire();
                semaphore3.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            semaphore2.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            semaphore2.acquire();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            semaphore3.release();
        }

        public void third(Runnable printThird) throws InterruptedException {
            semaphore3.acquire();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    static class Foo3 {
        private volatile int tag;

        public Foo3() {
            tag=1;
        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            while(tag!=1);
            printFirst.run();
            tag=2;
        }

        public void second(Runnable printSecond) throws InterruptedException {

            // printSecond.run() outputs "second". Do not change or remove this line.
            while(tag!=2);
            printSecond.run();
            tag=3;
        }

        public void third(Runnable printThird) throws InterruptedException {

            // printThird.run() outputs "third". Do not change or remove this line.
            while(tag!=3);
            printThird.run();
        }
    }

    static class Foo4 {

        AtomicInteger i = new AtomicInteger(1);   // will have values 1,2,3

        public Foo4() {}

        public synchronized void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            i.compareAndSet(1,2);
            notifyAll();

        }

        public synchronized void second(Runnable printSecond) throws InterruptedException {
            while (i.intValue() != 2)
                wait();

            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            i.compareAndSet(2,3);
            notifyAll();
        }


        public synchronized void third(Runnable printThird) throws InterruptedException {
            while (i.intValue() != 3)
                wait();

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }

    }

    static class Foo5 {
        private int state = 0;
        public Foo5() {

        }

        private synchronized void incState() {
            state += 1;
        }

        private synchronized boolean isState(int s) {
            return state == s;
        }

        public synchronized void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();

            incState();
            notifyAll();
        }

        public synchronized void second(Runnable printSecond) throws InterruptedException {
            if (!isState(1)) {
                wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            incState();
            notifyAll();
        }

        public synchronized void third(Runnable printThird) throws InterruptedException {
            while (!isState(2)) {
                wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    static class Foo6 {
        private final Lock lock = new ReentrantLock();
        private final Condition cond = lock.newCondition();
        int flag = 0;
        public Foo6() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            lock.lock();
            try {
                while ((flag & 0x1) != 0) {
                    cond.await();
                }
                // printFirst.run() outputs "first". Do not change or remove this line.
                printFirst.run();
                flag |= 0x1;
                cond.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            lock.lock();
            try {
                while ((flag & 0x1) != 1) {
                    cond.await();
                }
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                flag |= 0x2;
                cond.signalAll();
            } finally {
                lock.unlock();
            }

        }

        public void third(Runnable printThird) throws InterruptedException {
            lock.lock();
            try {
                while ((flag & 0x3) != 0x3) {
                    cond.await();
                }
                // printThird.run() outputs "third". Do not change or remove this line.
                printThird.run();
                flag |= 0x4;
                cond.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    static class Foo7 {

        private final Semaphore semaphore = new Semaphore(1);

        public Foo7(){

        }

        public void first(Runnable printFirst) throws InterruptedException {
            semaphore.acquireUninterruptibly(1);
            printFirst.run();
            semaphore.release(2);
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (semaphore.availablePermits() < 2) {

            }
            semaphore.acquireUninterruptibly(2);
            printSecond.run();
            semaphore.release(3);
        }

        public void third(Runnable printThird) throws InterruptedException {
            while(semaphore.availablePermits() < 3) {

            }
            semaphore.acquireUninterruptibly(3);
            printThird.run();
        }
    }

    static class Foo8 {

        private int x = 0;

        public Foo8() {

        }

        synchronized public void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            x = x + 1;
            notifyAll();
        }

        synchronized public void second(Runnable printSecond) throws InterruptedException {
            while (x < 1) {
                wait();
            }
            printSecond.run();
            x = x + 1;
            notifyAll();
        }

        synchronized public void third(Runnable printThird) throws InterruptedException {
            while (x < 2) {
                wait();
            }
            printThird.run();
        }
    }

    static class Foo9 {

        private enum State{
            FIRST,
            SECOND,
            THIRD
        };
        State currentState = State.FIRST;

        public Foo9(){

        }

        public void first(Runnable printFirst) throws InterruptedException {
            while (currentState != State.FIRST);
            printFirst.run();
            currentState = State.SECOND;
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (currentState != State.SECOND);
            printSecond.run();
            currentState = State.THIRD;
        }

        public void third(Runnable printThird) throws InterruptedException {
            while (currentState != State.THIRD);
            printThird.run();
            currentState = State.FIRST;
        }
    }
}
