package RedBlack;

import Util.RBNodeInterface;

import java.util.ArrayList;
import java.util.List;

public class RedBlackNode<T extends Comparable, E> implements RBNodeInterface<E> {
	ArrayList<E> list = new ArrayList<E>();  
	
      T key;          
      E val;         
      RedBlackNode<T,E> left, right;  
       boolean color;    
       int size;        
      public RedBlackNode(T key, E val, boolean color, int size) {
          this.key = key;
          this.list.add( val);
          this.color = color;
          this.size = size;
      }
      
    
 
    @Override
    public E getValue() {
        return list.get(0);
    }

    @Override
    public List<E> getValues() {
        return list;
    }
}
