package Interview.study.thread;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {// ABA问题的解决 AtomicStampedReference

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);
    public static void main(String[] args) {
        System.out.println("===================以下是ABA问题的产生==================");
        // 线程1
        new Thread(() ->{
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);

        },"t1").start();


        // 线程2
        new Thread(()->{
            //暂停1秒t2线程，保证线程1完成了一次ABA操作
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(atomicReference.compareAndSet(100, 2019)+"\t"+atomicReference.get());
        },"t2").start();


        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("===================以下是ABA问题的解决==================");
        // 线程3
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第1次版本号"+stamp);
            //暂停1秒t3线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

            //ABA操作
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t第2次版本号"+atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t第3次版本号"+atomicStampedReference.getStamp());
        },"t3").start();
        // 线程4
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第1次版本号"+stamp);
            //暂停4秒t4线程,保证上面的t3线程完成一次ABA操作
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
           boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t修改成功否： "+result+"\t当前最新版本号： "+atomicStampedReference.getStamp() );
            System.out.println(Thread.currentThread().getName()+"\t当前最新值： "+atomicStampedReference.getReference());
        },"t4").start();

    }
}
