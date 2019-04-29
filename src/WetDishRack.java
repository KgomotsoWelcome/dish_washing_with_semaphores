package dishWashS;
import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class WetDishRack {
	// add variables
	private int in;
	private int out;
	private int dish_id;
	private int rackSize;
	private int[] rack;
	private Semaphore mutex;
    private Semaphore full_rack;
    private Semaphore empty_rack;

	WetDishRack(int rackSize) {
	    // add correct code here 
		this.rackSize = rackSize;
		this.rack = new int[rackSize];
		this.mutex = new Semaphore(1);
		this.full_rack = new Semaphore(0);
		this.empty_rack = new Semaphore(rackSize);
	}
	
	public void addDish(int dish_id)  throws InterruptedException {
		// add correct code here
		this.dish_id = dish_id;
		empty_rack.acquire();
		mutex.acquire();
		rack[in]=this.dish_id; // add widget to buffer
		in = (in + 1)%rackSize; 
		mutex.release();
		full_rack.release();

	}
	
	public int removeDish() throws InterruptedException {
		// replace with correct code here
		full_rack.acquire();
		mutex.acquire();
		int next = rack[out];
		out = (out + 1) % rackSize; 
		mutex.release();
		empty_rack.release();
		
		return next; 

	}
	
}



