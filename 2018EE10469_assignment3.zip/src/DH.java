
public class DH<K,T> implements MyHashTable_<K,T> {

	int hashtablesize;
	HashEntry<K,T>[] table;
	
	@SuppressWarnings("unchecked")
	public DH(int k)
	{  this.hashtablesize=k;
	 table=(HashEntry<K,T>[]) new HashEntry[this.hashtablesize];
	 for (int i = 0; i < hashtablesize; i++)
         table[i] = null;
	}
	
	
	
	public int insert(K key, T obj) {
		String st=key.toString();
		long h1=Hash.djb2(st,hashtablesize);		
		long h=h1;int i=1;
		
	     while (this.table[(int)h]!=null)
			{   h=(h1+i*(Hash.sdbm(st,hashtablesize)))%hashtablesize;
			    i++;
			}
	     table[(int)h] = new HashEntry<K, T>(key, obj); 
		 return i;
				
	}

	
	public int update(K key, T obj) {
		String st=key.toString();
		long h1=Hash.djb2(st,hashtablesize);
		
		long h=h1;int i=1;
		
	     while (!this.table[(int)h].key.equals(key))
			{   h=(h1+i*(Hash.sdbm(st,hashtablesize)))%hashtablesize;
			    if(table[(int)h]==null) {return 0;}
			    i++;
			}
	     if(this.table[(int)h].key.equals(key))
	        table[(int)h] = new HashEntry<K, T>(key, obj); 
			return i;
	}

	
	public int delete(K key) {
		
		String st=key.toString();
		long h1=Hash.djb2(st,hashtablesize);
		
		long h=h1;int i=1;
		
	     while (table[(int)h]!=null)
			{  if (this.table[(int)h].key.equals(key)) { 
               
				return i;}
			else
	    	 h=(h1+i*(Hash.sdbm(st,hashtablesize)))%hashtablesize;    
			    i++;
			}
	    
			return 0;

		

	}
	
	
	
	public boolean contains(K key) {
		String st=key.toString();
		long h1=Hash.djb2(st,hashtablesize);
		
		long h=h1;int i=1;
		
	    while (this.table[(int)h]!=null)
			{   if (table[(int)h].key.equals(key))
				return true ;
				else
	    	    h=(h1+i*(Hash.sdbm(st,hashtablesize)))%hashtablesize;
			    i++;
			     
			}
		return false;
	}

	
	public T get(K key) throws NotFoundException {
		String st=key.toString();
		long h1=Hash.djb2(st,hashtablesize);
	
		long h=h1;long i=1;
		if (this.table[(int)h]==null) return null; 
		//T res = null;
	    while (this.table[(int)h]!=null )
			{  if (table[(int)h].key.equals(key))
				{  return table[(int)h].value;
				//res= table[(int)h].value;
			    }
				//else{
				 h=(h1+i*(Hash.sdbm(st,hashtablesize)))%hashtablesize;
				 i++;
			   
			}
	return null;
	}

	
	public String address(K key) throws NotFoundException {
		String st=key.toString();
		long h1=Hash.djb2(st,hashtablesize);		
		long h=h1;int i=1;
		
	    while (this.table[(int)h]!=null)
			{   if (table[(int)h].key.equals(key))
				return String.valueOf(h); 
				else
	    	    h=(h1+i*(Hash.sdbm(st,hashtablesize)))%hashtablesize;
			    i++;
			     
			} 
	    return "E";
	}
	public K AssignTemp(K key)
	{ String st=key.toString();
	long h1=Hash.djb2(st,hashtablesize);
	
	long h=h1;long i=1;
	if (this.table[(int)h]==null) return null; 
	
    while (this.table[(int)h]!=null && !table[(int)h].key.equals(key))
		{  
			 h=(h1+i*(Hash.sdbm(st,hashtablesize)))%hashtablesize;
			 i++;
		   
		}
   return table[(int)h].key;

}
}