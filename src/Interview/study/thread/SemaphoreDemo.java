package Interview.study.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *  Semaphore:
 *  Semaphore 类似于操作系统中的信号量，可以控制对互斥资源的访问线程数。
 *  Semaphore semaphore = new Semaphore(3);//模拟三个停车位
 *  semaphore.acquire();  抢到资源
 *  semaphore.release();   释放资源
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟三个停车位
        for (int i = 1; i <=6 ; i++) {//模拟6部车
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                    // 暂停一会线程
                    try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName()+"\t 停车3秒离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
