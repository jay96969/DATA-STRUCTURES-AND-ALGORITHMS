

import java.util.Iterator;

public class Student implements Student_
{   
    public LinkedList<CourseGrade> courseList=new LinkedList<CourseGrade>();
	
	public String name;
	
	public String name() {
		return name;
	}

	
	public String entryNo;
	public String entryNo() {
		return entryNo;
	}

	public String hostel;
	public String hostel() {
		return hostel;
	}

	public String department;
	public String department() {
		return department;
	}
	
	
	public double completedCredits() {
		double sum =0;
		Iterator<Node<CourseGrade>> ite6=courseList.positions();
	     while 	(ite6.hasNext())
	     {  Node<CourseGrade> kl=ite6.next();
	         if(kl.value().grade1()!="F" && kl.value().grade1()!="I")
	         {       sum+=3;                 }
	     }
		 return sum;
	}
	public String cgpa() { double su=0;
		Iterator<Node<CourseGrade>> ite7=courseList.positions();
	     while 	(ite7.hasNext())
	     {  Node<CourseGrade> km=ite7.next();
	       su += km.value().gradepoint();
	     }
		 double cg= (3*su) / completedCredits();
		 Double.toString(cg);
		
		return  String.format("%.2f", cg);
	}


	
	


	

	
	
	
	

	
	
	

}
