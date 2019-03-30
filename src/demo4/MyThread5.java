package demo4;

import java.util.Scanner;

class Processor extends Thread{
	//private boolean running = true;
	private volatile boolean running = true;
	public void run() {
		while(running) {
			System.out.println("Hello");
		}
	}
	
	public void shutDown() {
		running = false;
	}
}

public class MyThread5 {

	public static void main(String[] args) {
		Processor proc1 = new Processor();
		proc1.start();
		System.out.println("Press return key to stop....");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		proc1.shutDown();
	}

}
