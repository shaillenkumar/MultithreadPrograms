package demo5;
/**
 * 
 * @author shailen
 *
 */


public class MyThread7 {
	private int count = 0;
	
	public synchronized void doIncrement() {
	//public void doIncrement() {
		count++;
	}
     
	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for(int i=0;i<=10000;i++) {
					doIncrement();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for(int i=0;i<=10000;i++) {
					doIncrement();
				}
			}
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Value Of Count is "+count);
		
	}
		
	public static void main(String[] args) {
		MyThread6 app = new MyThread6();
		app.doWork();
		
	}

}
