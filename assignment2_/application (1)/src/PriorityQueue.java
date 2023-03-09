public class PriorityQueue<V> implements QueueInterface<V>{

    private Node<V>[] queue;
    private int capacity, currentSize=0;
	

    
	public PriorityQueue(int capacity) 
    {  
    	this.capacity=capacity;   
    	queue=new Node[capacity];
    	
    }

    public int size() {
             return this.currentSize;
             
    }

    public boolean isEmpty() {
       return currentSize==0;
    }
	
    public boolean isFull() {
     return currentSize==capacity;
    }

    public void enqueue(Node<V> node) {
    	
    	if (!isFull())
    	{int i;
    	if (currentSize==0)
    	{ 	queue[0]=node;
    	    currentSize++;
    	    return;
    	}
    	currentSize++;
    	for(i=currentSize-2;i>=0;i--)
    	{    if(node.priority>=queue[i].priority)
    	{       queue[i+1]=queue[i];
    	} else
    	{ break;  }}
    	queue[i+1]=node;
    	
    
    }}
    	
    public void add(int a,V e)
    {  
    	
    	Node<V> nod=new Node<V>(a,e);
		
    	this.enqueue(nod);
    }
    


	// In case of priority queue, the dequeue() should 
    // always remove the element with minimum priority value
    public Node<V> dequeue() { 
    	
    	if(!this.isEmpty())return queue[--currentSize];
      	return null;
    
    }
    public void display () {
	if (this.isEmpty()) {
            System.out.println("Queue is empty");
	}
	for(int i=0; i<currentSize; i++) {
            queue[i+1].show();
	}
    }

	public Node<V> removeMin() {
		return queue[--currentSize] ;
		
	}

}
