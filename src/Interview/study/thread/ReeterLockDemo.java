package Interview.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoked sendSMS()");
        sendEmail();
    }
    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t #########invoked sendEmail()");
    }

    //===============================================================================

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t invoked get()");
            set();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void set() {

        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t #################invoked set()");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
/**可重入锁（递归锁）
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码。
 * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
 *
 * 也即是说，线程可以进入任何一个它已经拥有的锁锁同步着的代码块。
 *
 * 1.案例1：synchronized是个典型的可重入锁
 * t1	 invoked sendSMS()                t1线程在外层方法获取锁的时候
 * t1	 #########invoked sendEmail()     t1线程在进入内层方法会自动获取锁
 * t2	 invoked sendSMS()
 * t2	 #########invoked sendEmail()
 *
 * sendSMS()外层     endEmail()内层
 *
 *
 * 2.案例2：ReentrantLock是个典型的可重入锁
 */
public class ReeterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()-> {
            try {
                phone.sendSMS();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()-> {
            try {
                phone.sendSMS();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"t2").start();



        //===========================================
        // 暂停一会线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");
        t3.start();
        t4.start();
    }
}
