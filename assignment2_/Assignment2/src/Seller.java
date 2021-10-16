import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Seller<V> extends SellerBase<V> {
	
    public Seller (int sleepTime, int catalogSize, Lock lock, Condition full, Condition empty, PriorityQueue<V> catalog, Queue<V> inventory) {
       
    	 this.lock=lock;
         this.empty=empty;
         this.full=full;
         this.catalog=catalog;
         this.inventory=inventory;
         setSleepTime(sleepTime);
        
    	
    	//TODO Complete the constructor method by initializing the attibutes
        // ...
    }
    
    public void sell() throws InterruptedException {
    	this.lock.lock();
    	try { 
	     while (this.catalog.isFull())
	     {  full.await(); }
	      if (this.inventory.size()!=0)
	      {  Node<V> nodd = this.inventory.dequeue();
	       this.catalog.enqueue(nodd); 
	       
	       this.empty.signalAll();
	
	     
	      }
            //TODO Complete the try block for produce method
            // ...
	} catch(Exception e) {
            e.printStackTrace();
	} finally {  this.lock.unlock();
            //TODO Complete this block
	}		
    }
}
