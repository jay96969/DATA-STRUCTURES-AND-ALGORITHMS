package PriorityQueue;

public class Student implements Comparable<Student> {
    private String name;
    private Integer marks;
   

    public Student(String trim, int parseInt) {
    	this.name=trim;
    	this.marks=parseInt;
    }


    @Override
    public int compareTo(Student student) {
       if(this.marks>student.marks)
    	return 1;
       else if(this.marks<student.marks) return -1;
       else return 0;
    }
    @Override
    public String toString()
    {
    	return "Student{name='"+this.name+"', marks="+this.marks+"}";
    	
    }

    public String getName() {
        return name;
    }
    
}
