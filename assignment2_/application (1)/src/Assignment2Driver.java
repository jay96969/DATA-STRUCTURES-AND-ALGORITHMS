import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Assignment2Driver {
    public int catalogSize;
    public int numBuyers;
    public int numSellers;
    public int sellerSleepTime;
	public int buyerSleepTime;
    public Queue<Item> inventory;
    
    public static void main(String[] args) throws InterruptedException {
	
	Assignment2Driver a_driver = new Assignment2Driver();
	BufferedReader reader;
        int itemcount = 0;
	try {
	    reader = new BufferedReader(new FileReader(args[0]));
            String line = reader.readLine();
	    ArrayList<Node<Item>> list = new ArrayList<Node<Item>>();
	    String[] tokens = line.split(" ");
	    a_driver.catalogSize = Integer.parseInt(tokens[0]);
	    a_driver.numBuyers = Integer.parseInt(tokens[1]);
	    a_driver.numSellers = Integer.parseInt(tokens[2]);
	    a_driver.buyerSleepTime = Integer.parseInt(tokens[3]);
	    a_driver.sellerSleepTime = Integer.parseInt(tokens[4]);
	    
	    while (line != null) {
		line = reader.readLine();
		if (line != null) {
                    itemcount++;
		    tokens = line.split(" ");
		    Item item = new Item(tokens[1], Double.parseDouble(tokens[2]));
		    Node<Item> node = new Node<Item>(Integer.parseInt(tokens[0]), item);
		    list.add(node);
		}
	    }
            a_driver.inventory = new Queue<Item>(list.size());
            for (int t = 0; t < list.size(); t++) {
            	a_driver.inventory.enqueue(list.get(t));
    			
    		}
            
            //TODO Add all elements of the ArrayList named "list" to inventory queue
            // ...
	    
            reader.close();
	} catch (IOException e) {
            e.printStackTrace();
	}
	
	Seller[] sellers = new Seller[a_driver.numSellers];
	Buyer[] buyers = new Buyer[a_driver.numBuyers];
	PriorityQueue<Node<Item>> queue = new PriorityQueue<Node<Item>>(a_driver.catalogSize);
	Lock lock = new ReentrantLock();
	Condition full = lock.newCondition();
	Condition empty = lock.newCondition();
        int iteration = itemcount/a_driver.numBuyers; // No. of purchases each buyer make   
         Thread[] s=new Thread[a_driver.numSellers];
          int i=0;
          while (i<a_driver.numSellers)
          {  s[i]=new Thread(new Seller(a_driver.sellerSleepTime,a_driver.catalogSize,lock,full,empty,queue,a_driver.inventory));
             
              i++;
          }
          for (int p=0;p<s.length;p++)
          {  s[p].start(); }
          Thread[] s1=new Thread[a_driver.numBuyers];
          int j=0;
          while (j<a_driver.numBuyers)
          {  s1[j]=new Thread(new Buyer(a_driver.buyerSleepTime,a_driver.catalogSize,lock,full,empty,queue,iteration));
             
              j++;
          }
          for (int k=0;k<s1.length;k++)
          {  s1[k].start(); }
        
        
        
	// TODO Create multiple Buyer and Seller Threads and start them.
        // ...
	
    }
}
