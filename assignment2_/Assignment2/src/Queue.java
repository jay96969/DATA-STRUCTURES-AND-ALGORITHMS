public class Queue<V> implements QueueInterface<V>{

    //TODO Complete the Queue implementation
    private Node<V>[] queue;
    private int capacity, currentSize=0;
	
    @SuppressWarnings("unchecked")
	public Queue(int capacity) { 
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
    
    	if (!this.isFull())
    {	
          queue[currentSize]= node;
    	  currentSize++;
    
    }else return;}

    public Node<V> dequeue() {
    	if(!this.isEmpty()) {  
    	Node<V> temp=queue[0];
    	 for(int i=0;i<currentSize-1;i++)
    	 {  queue[i]=queue[i+1];
    	
}
        currentSize--;
        return temp;
    	}
    	else return null;
}
    	

	public void add(int a, V e) {
     
		Node<V> nod=new Node<V>(a,e);		
    	this.enqueue(nod);
		
	}
	public Node<V> removeMin() {
		
		return this.dequeue() ;
		
		
	}
    
    
}