
import java.util.Iterator;


public class LinkedList<T> implements LinkedList_<T>
{
	Node<T> head ;
	int size=0;
	public Node<T> add(T value)
	{    if (head==null) {
		head= new Node<T>();
		head.value=value;
          return head;  }
		Node<T> curr=head;
		int i =1;
		while (curr.after!= null)
		{curr=curr.after;
		i++  ;}
		curr.after= new Node<T>();
		curr.after.value=value;
		size=i;
		return curr;
	}
	 public  int count() 
	 {
	   if (head==null)
	   { return 0;
		}
	   else 
	   { Node<T> curr=head;
	     int i=0;
	   while (curr.after!=null)
		{curr=curr.after;i++;}
	   return i+1;
	   }
	 }


	public Iterator<Node<T>> positions() {
		return new Iter();
	}
	class Iter implements Iterator<Node<T>>  
	 {
	     Node<T> current = head; 
         
	     public boolean hasNext() {
            if (current==null)
            { return false ;
         }
            else 
            return true;
	     }
        
         public Node<T> next() {
        	 Node<T> temp = current;
        	 current=current.after;
        			 
             return temp ;
                 	
             
		


		
		}}}
	


