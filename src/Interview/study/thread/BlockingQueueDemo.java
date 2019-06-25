package Interview.study.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 1.队列
 *
 * 2.阻塞队列
 *    当阻塞队列是空时，从队列中获取元素的操作将会被阻塞。（消费者）
 *    当阻塞队列是满的，往队列里添加元素的操作将会被阻塞。（生产者）
 *
 *    试图从空的阻塞队列中获取元素的线程将会被阻塞，直到其他的线程往空的队列里插入新的元素。
 *    试图往已满的阻塞队列中添加新元素的线程同样也会被阻塞，直到其他的线程从队列中移除一个或者多个元素或者完全情况队列后使队列重新变得空闲起来并后续新增
 *
 *
 *
 *   2.1 阻塞队列有没有好的一面
 *       好处是我们不需要关心什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切blockingqueue都一手包办好了。
 *       在concurrent包发布以前，在多线程环境下，我们每一个程序员都必须去自己控制这些细节，尤其还要兼顾效率和线程安全，而这些会给我们的程序带来不小的复杂度
 *
 *   2.2 不得不阻塞，你如何管理
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception{

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        System.out.println("=============================");
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
    }
}
