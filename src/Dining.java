import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Arrays;


public class Dining {
	
	private static final int NO_OF_PHILOSOPHER = 5;
    private static final int SIMULATION_MILLIS = 1000*60*1;
    public static CountDownLatch latch = new CountDownLatch(NO_OF_PHILOSOPHER); 
    
	public static void main(String args[]) throws InterruptedException {
        ExecutorService executorService = null;
	
        //Define philosophers array
        Philosopher[] philosophers = new Philosopher[NO_OF_PHILOSOPHER];

        //As many forks as Philosophers
        ReentrantLock[] sticks = new ReentrantLock[NO_OF_PHILOSOPHER];
        fill(sticks);
    
        executorService = Executors.newFixedThreadPool(NO_OF_PHILOSOPHER);
        

        for (int i = 0; i < NO_OF_PHILOSOPHER; i++) {
        	philosophers[i] = new Philosopher(i, sticks[i], sticks[(i + 1)% NO_OF_PHILOSOPHER],latch);
        	executorService.execute(philosophers[i]);
        }
        
        
    //Main thread sleeps till time of simulation
    Thread.sleep(SIMULATION_MILLIS);
    for (Philosopher philosopher : philosophers) {
        philosopher.setFinished();
    }
    latch.await();
    //all philosophers are done eating...
    System.out.println("---Dinner is finished, let's display how philosophers have been fed :");
    for (Philosopher philosopher : philosophers) {
        System.out.println("Philosopher [" + philosopher.getId()
                + "] => Number of Turns to Eat = "
                + philosopher.getEatCount());
    }
    

}

	private static void fill(ReentrantLock[] s) {
		// TODO Auto-generated method stub
		for (int i = 0, len = s.length; i < len; i++)
	        s[i] = new ReentrantLock();
		}
	}