import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class Philosopher implements Runnable {
	private int id;
	private ReentrantLock leftChopStick;
	private ReentrantLock rightChopStick;
	private int eatCounter = 0;
	
	private AtomicBoolean dinnerFinished = new AtomicBoolean(false);
	
	//To randomize eat/Think time
    private Random randomGenerator = new Random();
	private CountDownLatch latch;
	
	
	public Philosopher(int id, ReentrantLock leftChopStick, ReentrantLock rightChopStick, CountDownLatch latch) {
        this.id = id;
        this.leftChopStick = leftChopStick;
        this.rightChopStick = rightChopStick;
        this.latch = latch;
    }
	public int getId(){
	  	return(this.id);
	}
	    
	public int getEatCount() {
	   	return this.eatCounter;
	}
	
	public boolean isFinished() {
		return this.dinnerFinished.get();
	}
	
	public void setFinished() {
		this.dinnerFinished.set(true);
	}

    @Override
    public void run() {

        while ( !isFinished()) {
            try {
                think();
                if (pickupLeftChopStick() && pickupRightChopStick()) {
                    eat();
                }
                putDownChopSticks();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        latch.countDown();
    }
    
    
	private boolean pickupRightChopStick() {
		boolean value = false;
		try {
			rightChopStick.tryLock(10, TimeUnit.MILLISECONDS);
			//System.out.println(String.format("Philosopher [%s] picked up Right ChopStick ...", this.id));
			value = true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	private void think() {
		try {
			System.out.println(String.format("Philosopher [%s] is thinking ...", this.id));
			Thread.sleep(randomGenerator.nextInt(1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void eat() {
		try {
			this.eatCounter++;
			System.out.println(String.format("Philosopher [%s] is eating ...", this.id));
			Thread.sleep(randomGenerator.nextInt(1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void putDownChopSticks() {
		if(leftChopStick.isHeldByCurrentThread()) {
			leftChopStick.unlock();
			//System.out.println(String.format("Philosopher [%s] released Left ChopStick ...", this.id));
		}
		if(rightChopStick.isHeldByCurrentThread()) {
			rightChopStick.unlock();
			//System.out.println(String.format("Philosopher [%s] released right ChopStick ...", this.id));
		}
		
	}

	private boolean pickupLeftChopStick() {
		boolean value = false;
		try {
			leftChopStick.tryLock(10, TimeUnit.MILLISECONDS);
			//System.out.println(String.format("Philosopher [%s] picked up Left ChopStick ...", this.id));
			value = true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

}
