package ProjectManagement;

import java.util.ArrayList;

public class Project {
	public String name;
	//NAME PRIORITY BUDGET
    public int priority;
    public int budget;
    ArrayList<Job> list = new ArrayList<Job>();
   
    public Project(String name,String x,String y)
    {this.name=name;
    this.priority=Integer.parseInt(x);
    this.budget=Integer.parseInt(y);
    	
    }
	
	

}
