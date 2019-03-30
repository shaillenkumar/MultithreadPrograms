package demo8;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
/**
 * 
 * @author shailen
 * 
 * This is a classical queuing problem where one thread is producing data and other is 
 * consuming it. This avoids repeated polling  wastes CPU cycles.
 * 
 * Interthread Communication-wait(), notify() and notifyAll() must be used within a synchronized block only
 * 
 * wait()-It tells the calling thread to give up the lock and go to sleep until 
   				   some other thread enters the same monitor and calls notify().
 *  notify()-	It wakes up one single thread that called wait() on the same object. 
  			It should be noted that calling notify() does not actually give up a lock on a resource.
 *  notifyAll()-It wakes up all the threads that called wait() on the same object.
 */
class ProducerConsumer1 
{ 
	LinkedList<Integer> list = new LinkedList<Integer>();
	final int LIMIT = 10;
	Object lock = new Object();

	// Prints a string and waits for consume() 
	public void producer()throws InterruptedException 
	{ 
		int value = 0;
		while(true) {
			synchronized(this) {
				while(list.size() == LIMIT) {
					lock.wait(); // go to wait and wait for consume if list size is MAX
				}
				
				list.add(value++); 
				System.out.print("Producer Added Value->	" +value + ":::");
				this.wait(); // wait to consume
			    //lock.wait();
			}
		}        
	} 

	// Sleeps for some time and waits for a key press. After key 
	// is pressed, it notifies produce(). 
	public void consumer()throws InterruptedException 
	{        
		Random random = new Random();// this makes the producer thread to run first. 
		while(true) {
			synchronized(this) {
				while(list.size()==0) {
					this.wait();
					//lock.wait();
				}
				System.out.println("List Size->	" +list.size());
				int value = list.removeFirst();
				System.out.println("Consumed Value->  "+value );
				
				this.notify();
				//lock.notify();
			}
			Thread.sleep(random.nextInt(1000));
		}
		
	} 
}

public class MyThread11{

	public static void main(String[] args) {
		ProducerConsumer1 pc = new  ProducerConsumer1();		
		// Create a thread object that calls pc.produce() 
		Thread t1 = new Thread(new Runnable() 
		{ 
			@Override
			public void run() 
			{ 
				try
				{ 
					pc.producer(); 
				} 
				catch(InterruptedException e) { } 
			} 
		});

		// Create another thread object that calls pc.consume() 
		Thread t2 = new Thread(new Runnable() 
		{ 
			@Override
			public void run() 
			{ 
				try
				{ 
					pc.consumer(); 
				} 
				catch(InterruptedException e) 
				{  } 
			} 
		}); 

		// Start both threads 
		t1.start(); 
		t2.start(); 

		// t1 finishes before t2 
		try {
			t1.join(); 
			t2.join();

			System.out.println("Main Thread Exits!"); 
		}catch(Exception e) {}
	}
}
