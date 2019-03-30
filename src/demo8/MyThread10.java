package demo8;
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
class ProducerConsumer 
{ 
    // Prints a string and waits for consume() 
    public void producer()throws InterruptedException 
    { 
        // synchronized block ensures only one thread running at a time. 
        synchronized(this) 
        { 
            System.out.println("producer thread running");             
            wait(); // release lock on shared resource
            // and waits till some other method invokes notify(). 
            System.out.println("Resumed"); 
        } 
    } 

    // Sleeps for some time and waits for a key press. After key 
    // is pressed, it notifies produce(). 
    public void consumer()throws InterruptedException 
    {        
        Thread.sleep(1000); // this makes the producer thread to run first. 
        Scanner s = new Scanner(System.in); 
        // synchronized block ensures only one thread running at a time. 
        synchronized(this) 
        { 
            System.out.println("Waiting for return key to Consume !!"); 
            s.nextLine(); 
            System.out.println("Return key pressed"); 

            // notifies the produce thread that it 
            // can wake up. 
            notify(); 

            // Sleep 
            Thread.sleep(2000); 
        } 
    } 
}

public class MyThread10 {

	public static void main(String[] args) {
		ProducerConsumer pc = new  ProducerConsumer();		
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
