package PriorityQueue;

public class MaxHeap<T extends Comparable>implements PriorityQueueInterface<T> {

	public pair<T>[] contents;
    public int numItems;
    public int gt=1;
    
   
    public MaxHeap() {
        contents = (pair<T>[])new pair[100000000];      
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
        while (child < numItems)
        {
              
        	   if(child < numItems - 1&&contents[child].y.compareTo(contents[child + 1].y)==0&&contents[child].y.compareTo(toSift.y)>0)
        	   {
        		   if(contents[child].x>contents[child+1].x)
        		   {
        			   child=child+1;
        		   }
        		   contents[parent] = contents[child];
                   parent = child;
                   child = 2 * parent + 1;
        	   }
        	   
        	   else  if(child < numItems - 1&&contents[child].y.compareTo(contents[child + 1].y)==0&&contents[child].y.compareTo(toSift.y)==0)
        	    {
        	    	 if(contents[child].x>contents[child+1].x)
          		   {
          			   child=child+1;
          		   }
        	    	 if (contents[child].x<toSift.x)
        	    	 {
        	    		 contents[parent] = contents[child];
                         parent = child;
                         child = 2 * parent + 1;
        	    	 }
        	    	 else if(contents[child].x>toSift.x)
        	    		 {break;}
        	    }
        	   else	  if(child < numItems - 1&&contents[child].y.compareTo(contents[child + 1].y)<0)
              	    {  child=child+1;
        	
              	    if(contents[child].y.compareTo(toSift.y)==0)
        	    		  {
        	    			  if (contents[child].x<toSift.x)
        	        	    	 {
        	        	    		 contents[parent] = contents[child];
        	                         parent = child;
        	                         child = 2 * parent + 1;
        	        	    	 }
        	    			  else break;
        	    		  }
              	    else  if(contents[child].y.compareTo(toSift.y)>0)
        	    		  {
        	    			  contents[parent] = contents[child];
 	                         parent = child;
 	                         child = 2 * parent + 1;
        	    			  
              	          }
              	    else	  if(contents[child].y.compareTo(toSift.y)<0)
                          {
	                        break;
                           }
        	     	 
              	    }
        	   else  if(child < numItems - 1&&contents[child].y.compareTo(contents[child + 1].y)>0)
        	    {  
  	    		  if(contents[child].y.compareTo(toSift.y)==0)
  	    		  {
  	    			  if (contents[child].x<toSift.x)
  	        	    	 {
  	        	    		 contents[parent] = contents[child];
  	                         parent = child;
  	                         child = 2 * parent + 1;
  	        	    	 }
  	    			  else break;
  	    		  }
  	    		  else  if(contents[child].y.compareTo(toSift.y)>0)
  	    		  {
  	    			   contents[parent] = contents[child];
                        parent = child;
                        child = 2 * parent + 1;
  	    			  
        	          }
  	    		  else if(contents[child].y.compareTo(toSift.y)<0)
                    {
                      break;
                     }
  	     	 
        	    }
        	    
        	   else	   if(child==numItems-1)
        	   {
        		   if(contents[child].y.compareTo(toSift.y)==0)
   	    		  {
   	    			  if (contents[child].x<toSift.x)
   	        	    	 {
   	        	    		 contents[parent] = contents[child];
   	                         parent = child;
   	                         child = 2 * parent + 1;
   	                         break;
   	        	    	 }
   	    			  else break;
   	    		  }
   	    		  if(contents[child].y.compareTo(toSift.y)>0)
   	    		  {
   	    			   contents[parent] = contents[child];
                         parent = child;
                         child = 2 * parent + 1;
                         break;
   	    			  
         	          }
   	    		  if(contents[child].y.compareTo(toSift.y)<0)
                     {
                       break;
                      }
        	   }
        	   else break;
                  
        }
        
        
        contents[parent] = toSift;
    }

    @Override
    public T extractMax() {
   	if (numItems==0) return null;
    	
     T toRemove   = contents[0].y;
     contents[0] = contents[numItems - 1];
     numItems--;
     siftDown(0);
    
     return toRemove ;
    }
   
}