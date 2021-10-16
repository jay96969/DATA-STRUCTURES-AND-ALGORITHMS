
public class Pair<A,B> {
	public A a;
    public B b;

    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }
   
   @Override
    public boolean equals(Object o) {
    	
        Pair<?,?> tuple = (Pair<?,?>) o;
        if (!a.equals(tuple.a)) return false;
        else return b.equals(tuple.b);
    }
    
    @Override
  public String toString()
  {
	
	    return this.a.toString();
	 	  
  }

}
