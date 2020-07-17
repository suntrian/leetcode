import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * There are two kinds of threads, oxygen and hydrogen. Your goal is to group these threads to form water molecules. There is a barrier where each thread has to wait until a complete molecule can be formed. Hydrogen and oxygen threads will be given releaseHydrogen and releaseOxygen methods respectively, which will allow them to pass the barrier. These threads should pass the barrier in groups of three, and they must be able to immediately bond with each other to form a water molecule. You must guarantee that all the threads from one molecule bond before any other threads from the next molecule do.
 *
 * In other words:
 *
 * If an oxygen thread arrives at the barrier when no hydrogen threads are present, it has to wait for two hydrogen threads.
 * If a hydrogen thread arrives at the barrier when no other threads are present, it has to wait for an oxygen thread and another hydrogen thread.
 * We don’t have to worry about matching the threads up explicitly; that is, the threads do not necessarily know which other threads they are paired up with. The key is just that threads pass the barrier in complete sets; thus, if we examine the sequence of threads that bond and divide them into groups of three, each group should contain one oxygen and two hydrogen threads.
 *
 * Write synchronization code for oxygen and hydrogen molecules that enforces these constraints.
 *
 *
 *
 * Example 1:
 *
 * Input: "HOH"
 * Output: "HHO"
 * Explanation: "HOH" and "OHH" are also valid answers.
 * Example 2:
 *
 * Input: "OOHHHH"
 * Output: "HHOHHO"
 * Explanation: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" and "OHHOHH" are also valid answers.
 *
 *
 * Constraints:
 *
 * Total length of input string will be 3n, where 1 ≤ n ≤ 20.
 * Total number of H will be 2n in the input string.
 * Total number of O will be n in the input string.
 */
public class BuildingH2O1117 {

    public static void main(String[] args) {
        H2O_12 h2O = new H2O_12();

        List<Thread> threads = new ArrayList<>();
        Runnable printH = ()-> System.out.println("H");
        Runnable printO = ()-> System.out.println("O");

        Runnable callH = ()-> {
            try {
                h2O.hydrogen(printH);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable callO = ()-> {
            try {
                h2O.oxygen(printO);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };


        String HO = "HHHHHHOOO";
        for (char c : HO.toCharArray()) {
            if (c == 'H') {
                threads.add(new Thread(callH));
            } else if (c == 'O') {
                threads.add(new Thread(callO));
            }
        }

        for (Thread thread : threads) {
            thread.start();
        }



    }

    static class H2O1 {

        private  int count = 0;

        public H2O1() {

        }

        public synchronized void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            while (count==2) {
                wait();
            }

            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            count++;
            notifyAll();
        }

        public synchronized void oxygen(Runnable releaseOxygen) throws InterruptedException {
            while (count != 2) {
                wait();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            count = 0;
            notifyAll();
        }
    }

    /**
     * fail
     */
    static class H2O2 {

        Lock lock = new ReentrantLock();
        Condition hydrogenReleased = lock.newCondition();
        Condition oxygenReleased = lock.newCondition();
        int hydrogenCount = 0;

        public H2O2() {
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            lock.lock();
            while(hydrogenCount == 2) {
                oxygenReleased.await();
            }
            ++hydrogenCount;
            releaseHydrogen.run();
            hydrogenReleased.signal();
            lock.unlock();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {

            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            lock.lock();
            while(hydrogenCount < 2 ) {
                hydrogenReleased.await();
            }
            hydrogenCount = 0;
            releaseOxygen.run();
            oxygenReleased.signal();
            lock.unlock();
        }
    }

    static class H2O3 {

        CyclicBarrier cb = new CyclicBarrier(3);
        AtomicInteger counter = new AtomicInteger(0);

        public H2O3() {

        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            //System.out.println(counter);

            synchronized (counter) {
                while (counter.intValue() >= 2) {
                    counter.wait();
                }
                counter.incrementAndGet();
                releaseHydrogen.run();
                //System.out.println("H");
                counter.notifyAll();
            }
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {

            // System.out.println(Thread.currentThread().getName());
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            synchronized (counter) {
                while (counter.intValue() < 2) {
                    counter.wait();
                }
                counter.set(0);
                try {
                    cb.await();
                } catch (BrokenBarrierException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                releaseOxygen.run();
                //System.out.println("O");
                counter.notifyAll();

            }

        }
    }

    static class H2O4 {
        public H2O4() {

        }

        private final static int COUNT_H = 2;
        private final static int COUNT_O = 1;
        private final static int TOTAL_COUNT = COUNT_H + COUNT_O;

        private final Semaphore hSemaphore = new Semaphore(COUNT_H);
        private final Semaphore oSemaphore = new Semaphore(COUNT_O);
        private final CyclicBarrier preReleaseBarrier = new CyclicBarrier(TOTAL_COUNT);
        private final Phaser postReleasePhaser = new Phaser(TOTAL_COUNT) {
            protected boolean onAdvance(int phase, int parties) {
                // Release semaphores after molecule is printed
                hSemaphore.release(COUNT_H);
                oSemaphore.release(COUNT_O);
                // Don't terminate phaser
                return false;
            }
        };

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            try {
                hSemaphore.acquire(1);
                preReleaseBarrier.await();
                releaseHydrogen.run();
                postReleasePhaser.arrive();
            } catch (BrokenBarrierException ignored) {
            }
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            try {
                oSemaphore.acquire(1);
                preReleaseBarrier.await();
                releaseOxygen.run();
                postReleasePhaser.arrive();
            } catch (BrokenBarrierException ignored) {
            }
        }
    }

    static class H2O5 {
        public H2O5() {

        }

        private final Semaphore oSemaphore = new Semaphore(2);
        private final Semaphore hSemaphore = new Semaphore(2);

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            hSemaphore.acquire();
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            oSemaphore.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            oSemaphore.acquire(2);
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            hSemaphore.release(2);
        }
    }

    static class H2O6 {
        public H2O6() {

        }

        private Semaphore oxygen = new Semaphore(0);
        private Semaphore hydrogen = new Semaphore(0);
        private Semaphore oxygen2 = new Semaphore(1);
        private Semaphore hydrogen2 = new Semaphore(2);

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            hydrogen2.acquire();
            hydrogen.release();
            oxygen.acquire();
            releaseHydrogen.run();
            hydrogen2.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            oxygen2.acquire();
            hydrogen.acquire(2);
            releaseOxygen.run();
            oxygen.release(2);
            oxygen2.release();
        }
    }

    static class H2O7 {
        Semaphore hydroThread;
        Semaphore oxyThread;

        public H2O7() {
            hydroThread = new Semaphore(2);
            oxyThread = new Semaphore(1);
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            hydroThread.acquire();
            releaseHydrogen.run();
            if(hydroThread.availablePermits()==0)
                oxyThread.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {

            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            oxyThread.acquire();
            releaseOxygen.run();
            hydroThread.release(2);

        }
    }

    /**
     * fail
     */
    static class H2O8 {
        BlockingQueue<Runnable> h = new LinkedBlockingQueue<Runnable>();
        BlockingQueue<Runnable> o = new LinkedBlockingQueue<Runnable>();
        public H2O8() {}

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            h.put(releaseHydrogen);
            pull();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            o.put(releaseOxygen);
            pull();
        }

        private void pull(){
            if(h.size() >=2 && o.size()>=1){
                try {
                    h.take().run();
                    h.take().run();
                    o.take().run();
                } catch( Exception e){}
            }
        }
    }

    /**
     * fail
     */
    static class H2O9 {

        public H2O9() {

        }

        private static volatile int i = 0;

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            while (i>1) {
                Thread.currentThread().yield();
            }
            releaseHydrogen.run();
            ++i;
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            while (i<2) {
                Thread.currentThread().yield();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            i=0;
        }
    }

    static class H2O_10 {
        Lock lock;
        Condition condition;
        int num;

        public H2O_10() {
            lock = new ReentrantLock();
            condition = lock.newCondition();
            num = 1;
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            lock.lock();
            try {
                while (num % 3 == 0)
                    condition.await();
                releaseHydrogen.run();
                num++;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            lock.lock();
            try {
                while (num % 3 != 0)
                    condition.await();
                releaseOxygen.run();
                num++;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    static class H2O_11 {
        private final AtomicInteger h2o = new AtomicInteger();

        public H2O_11() {

        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            synchronized (h2o) {
                // if already release two Hydrogen, wait for Oxygen release
                while (h2o.get() == 2) {
                    h2o.wait();
                }
                h2o.addAndGet(1);
                // releaseHydrogen.run() outputs "H". Do not change or remove this line.
                releaseHydrogen.run();
                h2o.notifyAll();
            }
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            synchronized (h2o) {
                // if already release Oxygen, wait for Hydrogen release
                while (h2o.get() < 0) {
                    h2o.wait();
                }
                h2o.addAndGet(-2);
                // releaseOxygen.run() outputs "O". Do not change or remove this line.
                releaseOxygen.run();
                h2o.notifyAll();
            }
        }
    }

    static class H2O_12 {
        private volatile int count = 1;

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            while (count > 2) Thread.yield();
            releaseHydrogen.run();
            count++;
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            while (count <= 2) Thread.yield();
            releaseOxygen.run();
            count = 1;
        }
    }
}
