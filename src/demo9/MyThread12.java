package demo9;


import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 
 * @author shailen
 *
 * Re-entrant locks functions similar to Synchronized block.
 *
 */
class Runner{
	private int count = 0;
	private Lock renLock = new ReentrantLock();
	private Condition cond = renLock.newCondition();
	
	
	private void increment() {
		for(int i=0; i<10000; i++) {
			count++;
		}
	}
	public void firstThread() throws InterruptedException{
		renLock.lock(); //Locks 
		
		System.out.println("Waiting .....");
		cond.await(); //similar to wait -> needs signal to resume
		
		System.out.println("Thread1 resuming....");
		try {
			increment();
			}finally {
				renLock.unlock();  //for gauranted unlock
			}
	}
	
	public void secondThread() throws InterruptedException{
		Thread.sleep(1000);
		renLock.lock();
		
		System.out.println("Press Return Key ...");
		new Scanner(System.in).nextLine();
		System.out.println("You pressed return key....");
		
		cond.signal();  // resume await() thread
		
		try {
			increment();
			}finally {
				renLock.unlock();
			}
	}
	
	public void finished() {
		System.out.println("Count: "+count);
	}		
}
public class MyThread12 {

    public static void main(String[] args) throws Exception {
        final Runner runner = new Runner();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    runner.firstThread();
                } catch (InterruptedException ignored) {
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    runner.secondThread();
                } catch (InterruptedException ignored) {
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        runner.finished();
    }

}
