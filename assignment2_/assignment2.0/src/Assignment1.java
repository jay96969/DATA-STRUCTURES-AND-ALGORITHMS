


import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class Assignment1 {
	
	
	
	
	
	 static  LinkedList<Hostel> allhostels =new LinkedList<Hostel>();
	  static LinkedList<Dept> allDepartments=new LinkedList<Dept>();
	  static  LinkedList<Course> allcourses=new LinkedList<Course>();
	
	  
	private static void getData(String x,String y) throws IOException
	  {
	   
	  try ( BufferedReader b1=new BufferedReader(new FileReader(x)))
	  { String line ;int i=0;
	   line=b1.readLine();
	   while (line!=null)
	     {
       String[] tokens= line.split(" ");
		Iterator<Node<Hostel>> iterh =allhostels.positions();
		while (iterh.hasNext())
		{ Node<Hostel> p = iterh.next();
		if (p.value().name.equals(tokens[3]))
		{  i=1; break ;}
		
		}
		if (i==0)
		{Hostel h2=new Hostel();
		  h2.name=tokens[3];
		  allhostels.add(h2);
		}
		i=0;line=b1.readLine(); } }
	  
	 ////// ////////////////
	  
	  try ( BufferedReader b1=new BufferedReader(new FileReader(x)))
	  { String line ;int i=0;
	  line=b1.readLine();
	  while(line!=null)
		  {String[] tokens= line.split(" ");
		Iterator<Node<Dept>> iterd =allDepartments.positions();
		while (iterd.hasNext())
		{ Node<Dept> p = iterd.next();
		if (p.value().name.equals(tokens[2]))
		{  i=1; break ;}
		}
		if (i==0)
		{Dept d2=new Dept();
		  d2.name=tokens[2];
		  allDepartments.add(d2);
		}
	   i=0;line=b1.readLine();}}
	   
	////////////////////////
	  
	  Iterator<Node<Hostel>> itero =allhostels.positions();
		while (itero.hasNext()) 
		
		{   Node<Hostel> p = itero.next();
	try ( BufferedReader b1=new BufferedReader(new FileReader(x)))
	{ String line1=b1.readLine();  
	       while (line1!=null)
	         {
	    	   String[] tokens= line1.split(" ");				       
		       if ( p.value().name.equals(tokens[3]))
		     {   Student st=new Student();
		           st.name=tokens[1];
		           st.hostel=tokens[3];
		           st.entryNo=tokens[0];
		           st.department=tokens[2];
		           p.value().studentList.add(st);
		  }    
		    line1=b1.readLine();
	         }  }  }
			   
		
		
		
		//////////////////////////////////file2 reading
		try( BufferedReader b2=new BufferedReader(new FileReader(y)))
		{ String l1=b2.readLine();int i=0;
	 while (l1!=null)
     {
   String[] tokens= l1.split(" ");
   String text9=tokens[3];
 	for(int j=4;j<tokens.length;j++)
 	{ text9+=" "+tokens[j];}
  
	Iterator<Node<Course>> iterw =allcourses.positions();
	while (iterw.hasNext())
	{ Node<Course> p = iterw.next();	
	if (p.value().name.equals(tokens[1]))
	{  i=1; break ;}
	}
	if (i==0)
	{Course h2=new Course();
	  h2.name=tokens[1];
	  h2.coursetitle=text9;
	  allcourses.add(h2);
	}
	i=0;
   l1=b2.readLine();}}
	 
	 ///////////////////////
	 
	 
	 
	try	 (BufferedReader b2=new BufferedReader(new FileReader(y));)
	 
	{ String l2=b2.readLine();
	 while (l2!=null)
	 {   String[] tokens5= l2.split(" ");
	 String text1=tokens5[3];
	  	for(int j=4;j<tokens5.length;j++)
	  	{ text1+=" "+tokens5[j];}
     Iterator<Node<Hostel>> iter1 =allhostels.positions();
	   while(iter1.hasNext())
	   { Node<Hostel> s=iter1.next();
	   
	      Iterator<Node<Student>> iterst=s.value().studentList.positions();
	        while(iterst.hasNext())
	          { Node<Student> stx=iterst.next();
	              if (stx.value().entryNo().equals(tokens5[0]))
	                {
	                  CourseGrade cg=new CourseGrade();
	                  cg.coursenum=tokens5[1];
	                  cg.coursetitle=text1;
	                  cg.grade1=tokens5[2];
	                  stx.value().courseList.add(cg);	                  
	     }}}
	        l2=b2.readLine();
	 
	 
	   }  }
	}
	   
	 

	
	private static void answerQueries(String z) throws IOException
	{
		       String fresult="  ";
		       try  (BufferedReader b3=new BufferedReader(new FileReader(z)))
		   		{String line5 ;
		 	    line5=b3.readLine();
		 	    while(line5!=null )
		 	    {  String[] tokens2= line5.split(" ");
		 	      
		 	      if (tokens2[0].equals("COURSETITLE"))
		 	          {    
		 	            	Iterator<Node<Course>> ite4 =allcourses.positions();
	                          while(ite4.hasNext())
	                         { Node<Course> v=ite4.next();
	                           if (v.value.name.equals(tokens2[1]))
	                          { fresult+="  "+(v.value.coursetitle) ;}
	                  } }
	                if(tokens2[0].equals("SHARE"))
	                {    String result="#";
	                	Iterator<Node<Hostel>> iter8 =allhostels.positions();
	     	 	         while(iter8.hasNext())
	    	 	           { Node<Hostel> w=iter8.next();   
	    	 	              Iterator<Node<Student>> iterR=w.value().studentList.positions();
	    	 	                while(iterR.hasNext())
	    	 	                 { Node<Student> sty=iterR.next();
	    	 	                 if (sty.value.hostel.equals(tokens2[2])||sty.value.department.equals(tokens2[2]))	    	 	                 	     
	    	 	                 {  result+=" "+sty.value.entryNo;
	    	 	                 }
	    	 	                 
	    	 	                Iterator<Node<CourseGrade>> ite=sty.value().courseList.positions();
	    		                   while (ite.hasNext())
	    		                   {  Node<CourseGrade> j=ite.next();
	    		                     if (j.value.coursenum.equals(tokens2[2]))
	    		                    		 {result+=" "+sty.value.entryNo;
	    		                                   }
	    		                   }
	    	 	                 }}
	     	 	          
	     	 	           result=result.replace(" "+tokens2[1],"");
	     	 	           result=result.replace("# ","");
	                     String[] toke=result.split(" ");
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
	    	 	            String resu=" ";
	    	 	            for ( int k=0;k<toke.length;k++)
	    	 	               { resu+=" "+arrr[k];} 
	    	 	            resu=resu.replace("  ","");
	    	 	            fresult+="  "+(resu);
	    	 	           
	    	 	           
	                }
	     	 	         
		 	          
	     	 	         
	     	 	         
		 	          
	                
	                if ( tokens2[0].equals("INFO"))
	                {    
	                	String result="#";
	                	Iterator<Node<Hostel>> iter8 =allhostels.positions();
	     	 	         while(iter8.hasNext())
	    	 	           { Node<Hostel> w=iter8.next();   
	    	 	              Iterator<Node<Student>> iterR=w.value().studentList.positions();
	    	 	                while(iterR.hasNext())
	    	 	                 { Node<Student> sty=iterR.next();
	    	 	                  if (sty.value.entryNo.equals(tokens2[1])||sty.value.name.equals(tokens2[1]))
                                  { result+=" "+sty.value.entryNo;
                                  result+=" "+sty.value.name;
                                  result+=" "+sty.value.department();
                                  result+=" "+sty.value.hostel;
                                  result+=" "+sty.value.cgpa();
                                  String ex=" ";
                                  
                                  Iterator<Node<CourseGrade>> it =sty.value().courseList.positions();
                                  while(it.hasNext())
                                  {  Node<CourseGrade> ok=it.next();String temp1=" ";
                                      temp1+=" "+ok.value().coursenum;
                                      temp1+="$"+ok.value().grade1;
                                      temp1=temp1.replace("  ","" );
                                      ex+=" "+temp1;
                                  } 
                                  ex=ex.replace("  ", "");
                                  String[] toke1=ex.split(" ");
          	    	 	           String[] arrr1=new String[toke1.length];
          	    	 	           for ( int k=0;k<toke1.length;k++)
          	    	 	               { arrr1[k]=toke1[k];}
          	    	 	           
          	    	 	             
          	    	 	           for(int i=0;i<arrr1.length;i++)
          	    	 	        		   { for(int j=i+1;j<arrr1.length;j++)
          	    	 	        		   { if (arrr1[i].compareTo(arrr1[j])>0)
          	    	 	        		     {   String temp=arrr1[i];
          	    	 	        		          arrr1[i]=arrr1[j];
          	    	 	        		          arrr1[j]=temp;
          	    	 	        		      
          	    	 	        		   }}}
          	    	 	            String resu1=" ";
          	    	 	            for ( int k=0;k<toke1.length;k++)
          	    	 	               { resu1+=" "+arrr1[k];} 
          	    	 	            resu1=resu1.replace("  ","");
                                   resu1=resu1.replace("$"," ") ;
                                  result+=" "+resu1;
                                      
                                      
                                      
                                  
                                  }
	    	 	                 }
	    	 	                 
	              
	                }  
	     	 	         
	     	       result=result.replace("# ","");
	     	        fresult+="  "+(result);}
		 	    
		 	           line5=b3.readLine();}}
		       fresult=fresult.replace("    ", "");
		       String[] tokenf=fresult.split("  ");
		       for(int i=tokenf.length-1;i>=0;i--)
		       { System.out.println(tokenf[i]);
		       
	}   }
	
	public static void main(String [] args) throws IOException
	{
	
	   String x="C:\Users\Jay Kishan\Desktop\assignment1_\test_cases_hidden.tar.7z\test_cases_hidden.tar.gz\test_cases_hidden.tar\students.txt";
	   String y="C:\Users\Jay Kishan\Desktop\assignment1_\test_cases_hidden.tar.7z\test_cases_hidden.tar.gz\test_cases_hidden.tar\/courses.txt";
	   String z="C:\Users\Jay Kishan\Desktop\assignment1_\test_cases_hidden.tar.7z\test_cases_hidden.tar.gz\test_cases_hidden.tar\/query4.txt";
	   
	
	    getData(x,y);
	    answerQueries(z);
	
	
	
	
	  
	}
	

} 	    
