package Interview.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1. CAS是什么   ===> compareAndSet
 *    比较并交换
 */
public class CASDemo {
    public static void main(String[] args) {
        // new一个在堆里也就是在主物理内存里，初始值为5
        AtomicInteger atomicInteger = new AtomicInteger(5);

        // main do thing.......

        // 期望值设定为5，如果主物理内存里的真实值和期望值一样，就update为2019
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t current data: " + atomicInteger.get());
        // 期望值设定为5，这时已经修改成了2019，真实值与期望值不一样，false，本次修改失败。
        System.out.println(atomicInteger.compareAndSet(5, 2020) + "\t current data: " + atomicInteger.get());

        atomicInteger.getAndIncrement();
    }
}
