public class Tuple<A,B> {

    public A a;
    public B b;

    public Tuple(A a, B b) {
        this.a = a;
        this.b = b;
    }
   
   @Override
    public boolean equals(Object o) {
    	
        Tuple<?,?> tuple = (Tuple<?,?>) o;
        if (!a.equals(tuple.a)) return false;
        else return b.equals(tuple.b);
    }
    
    @Override
  public String toString()
  {
	
	  A s1=this.a;B s2=this.b;
	  String s="";
	  s+=s1.toString();
	  s+=s2.toString();
	  
	  return s;
	 	  
  }
    public int compareTo(String c)
    { return a.toString().compareTo(c);
        
}
}