package Interview.study.thread;

import java.util.concurrent.*;

/**
 * 第4种获得/使用java多线程的方法：线程池
 * 1.继承Thread类
 * 2.实现Runnable接口
 * 3.实现Callable接口
 * //
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                 TimeUnit.SECONDS,
                 new LinkedBlockingDeque<Runnable>(3),
                 Executors.defaultThreadFactory(),
                 new ThreadPoolExecutor.DiscardPolicy());


        // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程。
        try {
            for (int i = 1; i <= 11; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    // 一般我们不用已经提供的
    public static void threadPoolInit() {
        // 1.一池5个处理线程
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 2.一池1个处理线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 3.一池N个处理线程
//        ExecutorService threadPool = Executors.newCachedThreadPool();
        // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程。
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
