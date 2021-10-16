package ProjectManagement;


public class Project {
	public String name;
	//NAME PRIORITY BUDGET
    public int priority;
    public int budget;
   
    public Project(String name,String x,String y)
    {this.name=name;
    this.priority=Integer.parseInt(x);
    this.budget=Integer.parseInt(y);
    	
    }
	
	

}
