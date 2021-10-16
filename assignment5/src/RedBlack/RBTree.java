package RedBlack;


public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E>  {
	
	RedBlackNode<T,E> root;
	public boolean RED=true;
	public boolean BLACK=false;
    public boolean isEmpty() {
        return root == null;
    }
	 public boolean isRed(RedBlackNode<T,E> node) {
	        if (node == null) return false;
	        return node.color == RED;
	    }

	    
	    public int size(RedBlackNode<T,E> x) {
	        if (x == null) return 0;
	        return x.size;
	    } 
	    public int size() {
	        return size(root);
	    }
	   
	    
	    private RedBlackNode<T,E> Insertnode(RedBlackNode<T,E> h, T key, E value)
	    { 
	        if (h == null) return new RedBlackNode<T,E>(key, value, RED, 1);

	        int cmp = key.compareTo(h.key);
	        if      (cmp < 0) h.left  = Insertnode(h.left,  key, value); 
	        else if (cmp > 0) h.right = Insertnode(h.right, key, value); 
	        else              h.list.add(value);

	       
	        if (isRed(h.right) && !isRed(h.left))
	        	{
	        	RedBlackNode<T,E> nod = h.right;
		        h.right = nod.left;
		        nod.left = h;
		        nod.color = nod.left.color;
		        nod.left.color = RED;
		        nod.size = h.size;
		        h.size = size(h.left) + size(h.right) + 1;
	        	h = nod;
	        	}
	        if (isRed(h.left)  &&  isRed(h.left.left))
	        	{
	        	RedBlackNode<T,E> nod = h.left;
		        h.left = nod.right;
		        nod.right = h;
		        nod.color = nod.right.color;
		        nod.right.color = RED;
		        nod.size = h.size;
		        h.size = size(h.left) + size(h.right) + 1;
	        	h = nod;
	        	}
	        if (isRed(h.left)  &&  isRed(h.right))  {
	        	 h.color = !h.color;
	 	        h.left.color = !h.left.color;
	 	        h.right.color = !h.right.color;
	        }
	        h.size = size(h.left) + size(h.right) + 1;

	        return h;
	    }
	  
	    

    public void insert(T key, E value) {
    	root = Insertnode(root, key, value);
        root.color = BLACK;

    }

    @Override
    public RedBlackNode<T, E> search(T key) {
    	RedBlackNode<T, E> x=root;
    	while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else     return x ;
        }
        return null;
    }
}