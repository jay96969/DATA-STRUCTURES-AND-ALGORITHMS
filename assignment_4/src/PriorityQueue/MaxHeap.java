package PriorityQueue;

public class MaxHeap<T extends Comparable>implements PriorityQueueInterface<T> {

	public pair<T>[] contents;
    public int numItems;
    public int gt=1;
    
   
    public MaxHeap() {
        contents = (pair<T>[])new pair[10000];      
        numItems = 0;
    }
    
    
    private void siftUp(int i) {
        pair<T> toSift = contents[i];
        
        int child = i;
        while (child > 0) {
            int parent = (child+1)/2;
            
            // Check if we're done.
            if (toSift.y.compareTo(contents[parent-1].y) <= 0 )
            {
                break;
            }
            
            
            contents[child] = contents[parent-1];
            child = parent-1;
        }
        
        contents[child] = toSift;
    }
    @Override
    public void insert(T element) {
    	
        gt++;
        pair<T> ne=new pair<T>(element,gt);
        contents[numItems] = ne;
        
        siftUp(numItems);
        numItems++;
    }
    public pair<T> peek()
    {
    	return contents[0];
    }
    
    private void siftDown(int i) {
       pair<T> toSift = contents[i];
        
        
        int parent = i;
        int child = 2 * parent + 1;
        while (child < numItems) {
              int c=child;
              if (child < numItems - 1 )
              {
        	  if( contents[child].y.compareTo(contents[child + 1].y) <0)  
        	   {
        		   child = child + 1;
               }
        	   if(child < numItems - 1  &&
                        contents[child].y.compareTo(contents[child + 1].y)==0&&contents[child].x>contents[child+1].x) {
        		   		child++;																																															
        	   }
              }
        	  if(c==numItems-1&& contents[c].y.compareTo(toSift.y)==0&&toSift.x>contents[c].x)
        	  {
        		  contents[parent]=contents[c];
        		  parent=c;
        		  break;
        		  
        	  }
                    
        	   if ( contents[child].y.compareTo(toSift.y)==0&&toSift.x<contents[child].x)
        	   {  
        		   break;
        	   }
                     
        		   
            
            
            if (toSift.y.compareTo(contents[child].y) > 0) {
                break;
            }
            if (c < numItems - 1 &&
                    contents[c].y.compareTo(contents[c + 1].y)>0&&toSift.y.compareTo(contents[c + 1].y)== 0&&toSift.x>contents[c+1].x)  {
     
          		contents[parent] = contents[c];
            	contents[c]=contents[c+1];
                parent = c+1;
                child = 2 * parent + 1; 
     		   
     		   
     	   }
            else {
             contents[parent] = contents[child];
            parent = child;
            child = 2 * parent + 1;
            }
        }
        
        contents[parent] = toSift;
    }

    @Override
    public T extractMax() {
    	 
    	if(contents[0].y.compareTo(contents[1].y)==0||contents[0].y.compareTo(contents[2].y)==0) {
    		siftDown(0);
    		
    		
    	}
    
    	if (numItems==0) return null;
     	T toRemove   = contents[0].y;
     	contents[0] = contents[numItems - 1];
     numItems--;
     siftDown(0);
    
     return toRemove ;
    }
}