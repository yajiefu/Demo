package Interview.study.thread;

import java.util.concurrent.TimeUnit;


class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }
    @Override
    public void run() {

        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t自己持有"+lockA+"\t 尝试获得"+lockB);
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t自己持有"+lockB+"\t 尝试获得"+lockA);
            }

        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA,lockB),"ThreadAAA" ).start();
        new Thread(new HoldLockThread(lockB,lockA),"ThreadBBB" ).start();


        /**
         *Linux  ps -ef|grep xxxxx   ls -l
         * Window 的java运行程序也有类似ps的查看进程的命令，但是目前我们需查看的只是java
         *    Jps = java ps     jps -l
         */
    }
}
