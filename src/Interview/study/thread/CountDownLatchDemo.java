package Interview.study.thread;


import Interview.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch:
 *
 * 让一些线程阻塞直到另一些线程完成一系列操作后才被唤醒。
 * CountDownLatch主要有两个方法:
 * 1.当一个或多个线程调用await方法时，调用线程会被阻塞。
 * 2.当其他线程调用countDown方法会将计数器减1（调用countDown方法的线程不会阻塞），
 *   当计数器的值变为0时，因调用await方法被阻塞的线程会被唤醒，继续执行。
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException{
        CountDownLatch countDownLatch = new CountDownLatch(6);
        // 六个线程
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国被灭");
                countDownLatch.countDown();
            },CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }
        // 主线程
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t *************************秦国一统天下");
    }

    public static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        // 六个同学线程
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 上完自习，离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        // 主线程班长
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t *************************班长最后关门走人");
    }
}
