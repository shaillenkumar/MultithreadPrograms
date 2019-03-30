package demo2;
/**
 * 
 * @author shailen
 *
 */
class Runner implements Runnable{
	@Override
	public void run() {		
		for(int i=0;i<=10;i++) {
			System.out.println("i = "+i);			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}	
}

public class MyThread2 {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runner());
		t1.start();
		
		Thread t2 = new Thread(new Runner());
		t2.start();
		

	}

}
