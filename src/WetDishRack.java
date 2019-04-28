package dishWashS;
import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class WetDishRack {
	// add variables
	int rackSize;
	int dish_id;
	private int[] rack;
	private int in, out;
	public Semaphore mutex = new Semaphore(1);
    public Semaphore full_rack = new Semaphore(0);
    public Semaphore empty_rack;
    //ArrayList<Integer>rack;

	WetDishRack(int rackSize) {
	    // add correct code here 
		this.rackSize = rackSize;
		this.empty_rack = new Semaphore(rackSize);
		this.rack = new int[rackSize];
	//	this.rack = new ArrayList<Integer>();

	}
	
	public void addDish(int dish_id)  throws InterruptedException {
		// add correct code here
		//event = acquireForEvent ();
		this.dish_id = dish_id;
		empty_rack.acquire();
		mutex.acquire();
		rack[in]=this.dish_id; // add widget to buffer
		in = (in + 1)%rackSize; 
	//	rack.add(dish_id);
		mutex.release();
		full_rack.release();

	}
	
	public int removeDish() throws InterruptedException {
		// replace with correct code here
		
		full_rack.acquire();
		mutex.acquire();
		int next = rack[out];
		out = (out + 1) % rackSize; //circular buffer
	//	rack.remove(new Integer(this.dish_id));
		mutex.release();
		empty_rack.release();
		
		return next; 

	}
	
}



