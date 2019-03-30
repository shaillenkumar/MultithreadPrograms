package demo7;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author shailen
 *
 */

class Processor implements Runnable{
    private int id;
    
    Processor(int id){
    	 	this.id = id;
    }
    
	@Override
	public void run() {
		System.out.println("Starting Thread: "+id);	
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		
		System.out.println("Completed Thread: "+id);	
	}
	
}
public class MyThread9 {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		 for(int i = 5; i > 0; i--) {
	            executor.submit(new Processor(i));
	      }
		 System.out.println("All Tasks Submitted.");	
		 executor.shutdown();
		 
		 try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {}
		 System.out.println("All Tasks Completed.");	

	}

}
