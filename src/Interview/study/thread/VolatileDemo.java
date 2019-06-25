package Interview.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {
    volatile  int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    // 请注意，此时number前面是加了volatile关键字修饰的，volatile不保证原子性
    public  void addPlusPlus(){
        number++;
    }

    // 保证原子性
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomic(){
        // +1
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1.验证volatile的可见性
 *   1.1假如int number = 0; number变量之前没有加volatile关键字，没有可见性
 *   1.2添加了volatile,可以解决可见性
 * 2.验证volatile不保证原子性
 *   2.1 原子性指的是什么意思
 *      不可分割，完整性，即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割，需要整体完整
 *      要么同时成功，要么同时失败
 *   2.2 volatile不保证原则性的案例演示
 *   2.3 why
 *      看字节码：在putfield写回主内存时可能线程的调度被挂起了，刚好没有收到最新值的通知，有纳秒级的时间差，一写就出现了写覆盖。导致值永远小于2w
 *   2.4 怎么解决原子性
 *      * 加synchronized，但是杀鸡用牛刀了。
 *      * 使用我们JUC下AtomicInteger
 *
 */
public class VolatileDemo {

    // main是一切方法的运行入口
    public static void main(String[] args) {
//        seeOkByVolatile();
        MyData myData = new MyData();
        // 20个线程
        for (int i = 1; i <= 20 ; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000 ; j++) {
                    myData.addPlusPlus(); // 不保证原子性
                    myData.addMyAtomic();// 保证原子性
                }
            },String.valueOf(i)).start();
        }

        // 需要等待上面20个线程都全部计算完成后，再用main线程取得最终的结果值看是多少
        while(Thread.activeCount() > 2){
            // 一个线程是main线程，如果线程数大于2，说明上面20个线程里还有线程没有完成
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t int type, finally number value: "+myData.number);
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger type, finally number value: "+ myData.atomicInteger);

    }

    // volatile可以保证可见性，及时通知其他线程，主内存的值已经被修改
    private static void seeOkByVolatile() {
        // 资源类
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            // 暂停一会儿线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t updated number value: "+myData.number);
        }, "AAA").start();

        // 第2个线程就是我们的main线程
        while (myData.number == 0) {
            // main线程就一直在这里等待，直到number不等于0
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over,main get number value: " + myData.number);
    }

}
