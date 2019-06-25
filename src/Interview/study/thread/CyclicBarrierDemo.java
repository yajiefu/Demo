package Interview.study.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *  CyclicBarrier
 *  字面意思：可循环（Cyclic）使用的屏障（Barrier）。
 *  它要做的事情是：让一组线程达到一个屏障（也可以叫同步点）时被阻塞，
 *           直到最后一个线程达到屏障时，屏障才会开门，所有屏障拦截的线程才会继续干活，
 *           线程进入屏障通过CyclicBarrier的await（）方法。
 *
 * 用来控制多个线程互相等待，只有当多个线程都到达时，这些线程才会继续执行。
 * 和 CountdownLatch 相似，都是通过维护计数器来实现的。
 *
 *
 * 线程执行 await() 方法之后计数器会减 1，并进行等待，直到计数器为 0，所有调用 await() 方法而在等待的线程才能继续执行。
 *
 * CyclicBarrier 和 CountdownLatch 的一个区别是，CyclicBarrier 的计数器通过调用 reset() 方法可以循环使用，所以它才叫做循环屏障。
 * CyclicBarrier 有两个构造函数：其中 parties 指示计数器的初始值；barrierAction 在所有线程都到达屏障的时候会执行一次。
 * public CyclicBarrier(int parties, Runnable barrierAction)
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // public CyclicBarrier(int parties, Runnable barrierAction)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{
            System.out.println("*******************召唤神龙");
        });

        for (int i = 1; i <=7 ; i++) {
            final int tempInt = 7;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集到第"+tempInt+"个龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
