package demo10;

import java.util.Random;
import java.util.concurrent.*;

public class MyThread13 {

	public static void main(String[] args) {
		System.out.println("Starting.");
        Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				Random random = new Random();
				for(int i=0; i<1E8; i++)
					
			    /* 
			     // When you uncomment this block Remove the InterruptedException try-catch 
			     // surrounding Thread.sleep() and Thread.sleep
			     if(Thread.currentThread().isInterrupted()) {
			  
			    		System.out.println("Interrupted!!");
			    		break;
			    }
			   */
				try {
					Thread.sleep(1);
					
				}catch(InterruptedException e) {
					System.out.println("Interrupted !");
					break;
				}
				Math.sin(random.nextDouble());				
			}        		
        });
        t1.start();
        try {
			Thread.sleep(500);
			t1.interrupt();
	        t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Finished!!!!!");
        
	}

}
