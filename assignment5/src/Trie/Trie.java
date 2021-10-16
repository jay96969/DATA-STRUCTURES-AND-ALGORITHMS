package Trie;

public class Trie<T> implements TrieInterface<T> {

	TrieNode<T> root = new TrieNode<T>();
	TrieNode<T> root0 = new TrieNode<T>();
	int depth=0;
	
	
	public int dep() {
		int i=0;
		return i;
	}
	
    public void printTrie(TrieNode trieNode) {
    	for(int i = 31; i <127; i++) 
    	{
    		if (trieNode.arr[i]==null)
    		{
    			
    		}
    		else if(trieNode.arr[i].isEnd)
    		{
    			System.out.println(trieNode.arr[i].getValue());
    			 printTrie(trieNode.arr[i]);
    		}
    		else printTrie(trieNode.arr[i]);
    	}
    	
    	
    	
    }
    public  boolean isEmpty(TrieNode<T> root) 
    { 
        for (int i = 31; i <127; i++) 
        {   if (root.arr[i]!=null) 
                {return false;} }
        return true; 
    } 
    public  int count(TrieNode<T> r) 
    {   int k=0;
        for (int i = 31; i <127; i++) 
        {   if (r.arr[i]!=null) 
                {k++;} }
        return k; 
    } 

    @Override
    public boolean delete(String word) {
   
    	
    	 int level; 
    	 TrieNode<T> p = root;
    	 p.parent=root0;
    	 root0.parent=null;
    	 p.parent.son=0;
    	
    	
         for(level = 0; level < word.length(); level++){
             char c= word.charAt(level);
             int index=c;
             
             if(p.arr[index]!=null){
                TrieNode<T> temp=p;
                 p.son=index;
            	 p = p.arr[index];
            	 p.parent=temp;   }
             else{
                 return false;
             }
         }
  
         if(!p.isEnd)
         {
        	 return false;
         }
         if(p.isEnd && !isEmpty(p))
         {   p.isEnd=false;
           
             return true;
             }
        
         
         TrieNode<T> temp2=p.parent.parent;
          p.parent.arr[p.parent.son]=null;
       
        	 p=temp2;
        
        	 while(p!=root0)
        	 { 
        	   if(count(p.arr[p.son])==0 && !p.arr[p.son].isEnd)
        	   {  TrieNode<T> temp1=p.parent;
        		   p.arr[p.son]=null;
        		   p=temp1;
        	   }
        	  
        	   else 
        	   {  break;}
        	 }  
         return true;
    	
    }

    @Override
    public TrieNode<T> search(String word) {
    	int level; 
    	TrieNode<T> p = root;
         for(level = 0; level < word.length(); level++){
         
        	 for (level = 0; level < word.length(); level++) 
             { 
                int index = word.charAt(level); 
            
                if (p.arr[index] == null) 
                     return null; 
            
                 p = p.arr[index]; 
             } 
         }
  
         
         if(p.isEnd=true)
         {   return p;}
         else return null;
     }
    

    @Override
    public TrieNode<T> startsWith(String prefix) {
    	TrieNode<T> p = root;
        for(int i=0; i<prefix.length(); i++){
            char c= prefix.charAt(i);
            int index = c;
            if(p.arr[index]!=null){
                p = p.arr[index];
            }else{
                return null;
            }
        }
 
        if(p==root)
            return null;
 
        return p;
    }
    

    @Override
    public boolean insert(String word, Object value) {
    	int level;
    	TrieNode<T> p = root;
    	if (depth<word.length())
    	{ depth=word.length();}
        for(level = 0; level < word.length(); level++){
            char c = word.charAt(level);
            int index = c;
            if(p.arr[index]==null){
                TrieNode<T> temp = new TrieNode<T>();
                p.arr[index]=temp;
                p = temp;
            }else{
                p=p.arr[index];
            }
        }
        p.value=(T) value;
        p.isEnd=true;
        return true;
    }

    @Override
    public void printLevel(int level) {
    	String result=printlevell(root,level);
    	result=result.replaceFirst(",","");
    	String[] toke=result.split(",");
        String[] arrr=new String[toke.length];
        for ( int k=0;k<toke.length;k++)
            { arrr[k]=toke[k];}
        
          
        for(int i=0;i<arrr.length;i++)
     		   { for(int j=i+1;j<arrr.length;j++)
     		   { if (arrr[i].compareTo(arrr[j])>0)
     		     {   String temp=arrr[i];
     		          arrr[i]=arrr[j];
     		          arrr[j]=temp;
     		      
     		   }}}
         String resu="  ";
         for ( int k=0;k<toke.length;k++)
            { resu+=","+arrr[k];} 
         resu=resu.replace("  ,","");
	   	System.out.println("Level "+level+": "+resu);
    }
    @Override
    public void print() {
    	int i=1;
    	int k=0;
    	System.out.println("-------------");
    	System.out.println("Printing Trie");
    	while(i<depth+1)
    	{
    		printLevel(i);
    		if(printlevell(root,i).equals(""))
    		{k=2;
    		}
    		i++;
    		if(printlevell(root,i).equals("")&&k==2) {
    			return;
    		}
    		
    	}
    	System.out.println("Level "+i+": ");
    	System.out.println("-------------");
    	

    }
 
    public String printlevell(TrieNode<T> node,int level)
    {  String stri="";    
	   if (level==1)  {
		   for(int j=31;j<127;j++)
		   {   if(node.arr[j]!=null&&node!=null)
			   {   char a=(char) j;
			 
			     if(!Character.toString(a).equals(" "))  {  stri+=","+a; }  
			   
			      }
		   }
		  return stri;}
	   
	   else
	   {	   level--;
		   for(int j=31;j<127;j++)
	 {    if(node.arr[j]!=null)
		   stri+=printlevell(node.arr[j],level);
    
    }   }
	 return stri;  
	  
    } 
    
    
  
  

}