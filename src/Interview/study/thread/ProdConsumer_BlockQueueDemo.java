package Interview.study.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者消费者模式---blockingQueue
 */
class MyResoure{
    private volatile boolean FLAG = true; //默认开启，进行进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResoure(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    // 生产
    public void myProd() throws Exception{
        String data = null;
        boolean retValue;
        while (FLAG){// FLAG==true,就生产
            data = atomicInteger.getAndIncrement()+"";
            // 当阻塞队列满时，队列会阻塞生产线程一段时间，超过限时后生产线程会退出
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 大老板叫停，表示FLAG为false，生产动作停止");
    }

    // 生产
    public void myConsumer() throws Exception{
        String result = null;
        while (FLAG){
            result = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过两秒钟没有取到蛋糕，消费退出");

                System.out.println();
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t消费队列"+result+"成功");
        }
        System.out.println(Thread.currentThread().getName()+"\t 大老板叫停，表示FLAG为false，生产动作停止");
    }

    public void stop() throws Exception{
        this.FLAG = false;
    }
}
/**
 * volatile/CAS/atomicInteger/BlockQueue/线程交换
 */
public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) throws Exception{
        MyResoure myResoure = new MyResoure(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
            try {
                myResoure.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Prod").start();


        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
            System.out.println();
            System.out.println();
            try {
                myResoure.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        // 暂停一会线程
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("5秒钟时间到，大老板main线程叫停，活动结束");
        myResoure.stop();
    }
}
