package demo3;
/**
 * 
 * @author shailen
 *
 */
public class MyThread3 {

	public static void main(String[] args) {
		
		
		
		
		Thread t1 = new Thread(new Runnable() {
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
		});
		
		t1.start();

	}

}
