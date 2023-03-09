import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Buyer<V> extends BuyerBase<V> {
    public Buyer (int sleepTime, int catalogSize, Lock lock, Condition full, Condition empty, PriorityQueue<V> catalog, int iteration) {
               
                this.lock=lock;
                this.empty=empty;
                this.full=full;
                this.catalog=catalog;
                setSleepTime(sleepTime);
                setIteration(iteration);
                
    	
    	//TODO Complete the Buyer Constructor method
        // ...
    }
    public void buy() throws InterruptedException {
    	this.lock.lock();
    	 try {  
	       while(this.catalog.isEmpty())
	       {
              this.empty.await();   }
	    	   
	    	
	    	if (!this.catalog.isEmpty())
	    	   
	    	{  Node<V> n=this.catalog.dequeue();
	    	   
	       
	    	   //TODO Complete the try block for consume method
            // ...
	    System.out.print("Consumed "); // DO NOT REMOVE (For Automated Testing)
            n.show(); // DO NOT REMOVE (For Automated Testing)
            // ...
            this.full.signalAll();
	}}
	 catch (Exception e) {
            e.printStackTrace();
	} finally {this.lock.unlock();
            //TODO Complete this block
	}
    }
}
 