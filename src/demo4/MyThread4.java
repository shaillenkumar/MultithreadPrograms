package demo4;
/**
 * @author shailen
 * 
 * With 2 or More Threads there may be a situation when multiple threads try to 
 * access the same resource and finally they can produce unforeseen result 
 * due to concurrency issues. For example, if multiple threads try to write within a 
 * same file then they may corrupt the data because one of the threads can override data 
 * So there is a need to synchronize the action of multiple threads and make sure that only one thread
 *  can access the resource at a given point in time. 
 */
class PrintMe{
	public void printCount() {
	      try {
	         for(int i = 5; i > 0; i--) {
	            System.out.println("Counter   --->   "  + i );
	         }
	      } catch (Exception e) {
	         System.out.println("Thread  interrupted.");
	      }
	   }
}


class Runner extends Thread{
	String threadName;
	PrintMe printMe;
	Runner(String threadName, PrintMe printMe){
		this.threadName = threadName;
		this.printMe = printMe;
	}
	public void run() {
		printMe.printCount();
		System.out.println("Thread " +  threadName + " exiting.");
	}
}

public class MyThread4 {

	public static void main(String[] args) {
		PrintMe printMe = new PrintMe();
		Runner runner1 = new Runner("Thread-1",printMe);
		runner1.start();
		
		Runner runner2 = new Runner("Thread-2",printMe);
		runner2.start();

	}
}
