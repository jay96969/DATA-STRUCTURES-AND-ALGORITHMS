
public class SCBST<K,T> implements MyHashTable_<K,T>  {
	public BSTNode<K,T> _root;
	
	
	
    public SCBST()
    {  _root=null;
    	}
  
     private int compare(K k1, K k2){
    	
             return k1.toString().compareTo(k2.toString());
            		
        }
     
	
	public int insert(K key, T obj) {
		int i=1;
        if(_root == null){
            _root = new BSTNode<K, T>(key, obj);return i;
        }
        else
        {
        BSTNode<K,T> prev=null, curr = _root;
        boolean lastChildLeft = false;
        while(curr != null  ){  
            int comparison = compare(key,curr.key());
            prev = curr ;
    
            	if(comparison <0){
                curr = curr._left;i++;
                lastChildLeft = true;
            }
            else{
                curr = curr._right;i++;
                lastChildLeft = false;
            }
        }
    
                if(lastChildLeft){
            prev._left = new BSTNode<K, T>(key, obj);
        }else {
            prev._right = new BSTNode<K, T>(key, obj);
        }  
        return i;}
	}

	
	public int update(K key, T obj) {
		BSTNode<K,T> node = _root;
        int i=1;
        while (node != null && !node._key.equals(key)){
            int comparison = compare(key, node.key());

     
          	if(comparison < 0){
                node = node._left;i++;
            }else {
                node = node._right;i++;
            }
        }
        if(node==null)
        {  return 0;}
        else {node._value=obj;return i;}
	}

	
	public int delete(K key) {
		 BSTNode<K,T> curr = _root;
         int i=1;
         
          while(curr!=null&& !curr._key.equals(key) ){
            int comparison = compare(key, curr.key());
         
             if(comparison < 0){
            	BSTNode<K, T> temp = curr;
                curr = curr._left;
           if(curr!=null) {  curr._parent=temp;
                curr.isLeft();}
                i++;
            }
           
             else {
            	 BSTNode<K, T> temp = curr;
                 curr = curr._right;i++;
                 if(curr!=null) {      curr._parent=temp;
                 curr.isRight();}
                 }
             } 
          if(curr==null)
          { return 0;}
          else { return (i+removeNode(curr));}}


	
	public boolean contains(K key) {
		if(_root == null){
            return false;
        }

        BSTNode<K,T> node = _root;

        while (node != null&& !node._key.equals(key)){
            int comparison = compare(key, node.key());

        
            	if(comparison < 0){
                node = node._left;
            }else {
                node = node._right;
            }
        }
        if(node==null) { 
        return false;
	}
        else return true; }

	
	public T get(K key) throws NotFoundException {
		//  if(_root != null){
            BSTNode<K,T> node = _root;

            while (node != null&&!node._key.equals(key)){
                int comparison = compare(key, node.key());

        
                	if(comparison < 0){
                    node = node._left;
                }else {
                    node = node._right;
                }
            }
		  
            if(node==null) { 
                return null;
        	}
                else return node._value; }

	
	public String address(K key) throws NotFoundException {
		String x="";
		//if(_root != null){
            BSTNode<K,T> node = _root;

            while (node != null&&!node._key.equals(key)){
                int comparison = compare(key, node.key());

             
                	if(comparison < 0){
                    node = node._left;x+="L";
                }else {
                    node = node._right;x+="R";
                }
            }  
		 if( node ==null) { 
             return "no";
     	}
             else return x; }

	
	private int removeNode(BSTNode<K,T> curr){
		int j=0;
		if(curr._left == null && curr._right == null){
			if(curr == _root){
                _root = null;
            }else{
                if(curr.isLeft()) curr._parent._left = null;
                else curr._parent._right = null;
            }
        }
        else if(curr._left == null && curr._right != null){
            curr._key = curr._right._key;
            curr._value = curr._right._value;
            curr._left = curr._right._left;
            curr._right = curr._right._right;
            j++;
            
        }
        else if(curr._left != null && curr._right == null){
            curr._key = curr._left._key;
            curr._value = curr._left._value;
            curr._right = curr._left._right;
            curr._left = curr._left._left;
            j++;
        }
        else { // both left & right exist
        	BSTNode<K, T> temp1 = curr;
            BSTNode<K,T> x = curr._right;j++;
            x._parent=temp1;
            x.isRight();
            // find right-most node of left sub-tree
            while (x._left != null){ 
                BSTNode<K, T> temp = x;
            	x = x._left;j++;
            	x._parent=temp;
                x.isLeft();
            }
            // move that to current
            curr._key = x._key;
            curr._value = x._value;
            // delete duplicate data
         //   removeNode(x);
            if(x._left == null && x._right == null){
            	if(x == _root){
                    _root = null;
                }else{
                    if(x.isLeft()) x._parent._left = null;
                    else x._parent._right = null;
                }
            }
            else if(x._left == null && x._right != null){
                x._key = x._right._key;
                x._value = x._right._value;
                x._left = x._right._left;
                x._right = x._right._right;
                j++;
            }
        }
       
		return j;
    }

}
