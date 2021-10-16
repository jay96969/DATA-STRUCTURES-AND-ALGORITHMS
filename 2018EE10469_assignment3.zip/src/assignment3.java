import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class assignment3<K,T> {
	
	
	
	
 	 
	public static void doublehash(String x,String z) throws FileNotFoundException, IOException, NotFoundException
		{  DH<Tuple<String,String>,Student> dh=new DH<Tuple<String,String>,Student>(Integer.parseInt(z));
   
		  try ( BufferedReader b1=new BufferedReader(new FileReader(x)))
		  {   String line ;
		      line=b1.readLine();
		      while (line!=null)
		          {
			        String[] tokens= line.split(" ");
			        if (tokens[0].equals("insert"))
			           {   Tuple<String,String> key= new Tuple<String,String>(tokens[1],tokens[2]);
			               Student stud= new Student();
			               stud.fname=tokens[1];stud.lname=tokens[2];stud.hostel=tokens[3];stud.department=tokens[4];stud.cgpa=tokens[5];			               		               
			         	   int y=dh.insert(key,stud);
			               System.out.println(y);
			         	   
			           }  
				   
                     if(tokens[0].equals("update"))
                     {
                    	   Tuple<String,String> key= new Tuple<String,String>(tokens[1],tokens[2]);
			               Student stud= new Student();
			               stud.fname=tokens[1];stud.lname=tokens[2];stud.hostel=tokens[3];stud.department=tokens[4];stud.cgpa=tokens[5];
			             int q= dh.update(key, stud);
			             if(q!=0) {System.out.println(q);} else System.out.println("E");
			               
                     
                     }
                     if(tokens[0].equals("delete"))
                     {
                    	   Tuple<String,String> key= new Tuple<String,String>(tokens[1],tokens[2]);
                    	   if(dh.delete( key)!=0) {
			               System.out.println(dh.delete( key));
			               dh.AssignTemp(key).a="error404";}
                    	   else System.out.println("E");
                     
                     
                     }
                     if(tokens[0].equals("contains"))
                     {
                    	   Tuple<String,String> key= new Tuple<String,String>(tokens[1],tokens[2]);
			               
                    	   if(dh.contains( key)) {System.out.println("T");}else System.out.println("F");
                    	   
                     
                     
                     }
                     if(tokens[0].equals("get"))
                     {
                    	   Tuple<String,String> key= new Tuple<String,String>(tokens[1],tokens[2]);
			               
                    	  // System.out.println(dh.get(key));
                    	   if(dh.get(key)!=null) {
			               System.out.println(dh.get(key).fname+" "+dh.get(key).lname+" "+
			               dh.get(key).hostel+" "+dh.get(key).department+" "+dh.get(key).cgpa);
                    	   }else System.out.println("E");     
                     
                     
                     }
                     if(tokens[0].equals("address"))
                     {  Tuple<String,String> key= new Tuple<String,String>(tokens[1],tokens[2]);
                           System.out.println(dh.address(key));
                    
                     }
             line=b1.readLine();}

		  }}
		  
   public static void separatechain(String y,String z) throws NotFoundException, IOException
		  {  
		  
	    	 @SuppressWarnings("unchecked")
			SCBST<Pair<String,String>, Student>[] table=new SCBST[Integer.parseInt(z)];
			for(int i=0;i<Integer.parseInt(z);i++) { table[i]=new SCBST<Pair<String,String>,Student>(); }
			int w= Integer.parseInt(z);
		      
			  try ( BufferedReader b1=new BufferedReader(new FileReader(y)))
			  {   String line ;
			      line=b1.readLine();
			      while (line!=null)
			          {
				        String[] tokens= line.split(" ");
				        if (tokens[0].equals("insert"))
				           {   Tuple<String,String> key= new Tuple<String,String>(tokens[1],tokens[2]);
				           Pair<String,String> key1= new Pair<String,String>(tokens[1],tokens[2]);
				               Student stud= new Student();
				               stud.fname=tokens[1];stud.lname=tokens[2];stud.hostel=tokens[3];stud.department=tokens[4];stud.cgpa=tokens[5];
		                       int j=(int) Hash.djb2(key.toString(),w);
		                       
		                      System.out.println(table[j].insert(key1,stud));
		                       
					           			           
				           }
				       if(tokens[0].equals("update"))
	                     {
	                    	   Tuple<String,String> key= new Tuple<String,String>(tokens[1],tokens[2]);
	                    	   Pair<String,String> key1= new Pair<String,String>(tokens[1],tokens[2]);
				               Student stud= new Student();
				               stud.fname=tokens[1];stud.lname=tokens[2];stud.hostel=tokens[3];stud.department=tokens[4];stud.cgpa=tokens[5];
				               int j=(int) Hash.djb2(key.toString(),w);int q=table[j].update(key1,stud);
				               
		                     
		                       if(q!=0) { System.out.println(q);}else System.out.println("E");
				               
	                     
	                     }
	                     if(tokens[0].equals("delete"))
	                     {
	                    	   Tuple<String,String> key= new Tuple<String,String>(tokens[1],tokens[2]);
	                    	   Pair<String,String> key1= new Pair<String,String>(tokens[1],tokens[2]);
	                    	   int j=(int) Hash.djb2(key.toString(),w);int q=table[j].delete(key1);
		                      if(q!=0) { System.out.println(q);}else System.out.println("E");
	                     
	                     
	                     }
	                     if(tokens[0].equals("contains"))
	                     {
	                    	   Tuple<String,String> key= new Tuple<String,String>(tokens[1],tokens[2]);
	                    	   Pair<String,String> key1= new Pair<String,String>(tokens[1],tokens[2]);
	                    	   int j=(int) Hash.djb2(key.toString(),w);
		                       if(table[j].contains(key1)) {System.out.println("T");}else System.out.println("F");
	                     
	                     
	                     }
	                     if(tokens[0].equals("get"))
	                     {
	                    	   Tuple<String,String> key= new Tuple<String,String>(tokens[1],tokens[2]);
	                    	   Pair<String,String> key1= new Pair<String,String>(tokens[1],tokens[2]);
	                    	   int j=(int) Hash.djb2(key.toString(),w);
		                       if(table[j].get(key1)!=null) {
				               System.out.println(table[j].get(key1).fname+" "+table[j].get(key1).lname+" "+
				            		   table[j].get(key1).hostel+" "+table[j].get(key1).department+" "+table[j].get(key1).cgpa);
		                       }else System.out.println("E");
	                     
	                     
	                     }
	                     if(tokens[0].equals("address"))
	                     {  Tuple<String,String> key= new Tuple<String,String>(tokens[1],tokens[2]);
	                     Pair<String,String> key1= new Pair<String,String>(tokens[1],tokens[2]);
	                        int j=(int) Hash.djb2(key.toString(),w);
	                        if(table[j].address(key1).equals("no")) { System.out.println("E");}
	                        else  System.out.println(j+"-"+table[j].address(key1));
	                    
	                     } 
	             line=b1.readLine();}
		  
		  
		  
			  }
		  
		}
		  
	  public static void main(String [] args) throws IOException, NotFoundException
		{
		      
		  String z= args[0];
		if(args[1].equals("SCBST")) {separatechain(args[2],z);}
		else if(args[1].equals("DH")) {doublehash(args[2],z);}
		
		  
		}
	  
	  
	  
	  
	  
} 