package ProjectManagement;

public class JobReport implements JobReport_ {
    String jobname;
	  String us;
	  int budget;
	  int priority;
	  Project project;
	  int arrival;
	  int complete;
	 public boolean com =false;
	  public JobReport(String username,int budget,Project pro,int arr)
	  {
		  this.arrival=arr;
		  this.budget=budget;
		  this.us=username;
		  this.project=pro;
		  this.priority=pro.priority;
	  }
	public String user() { return us; }
    
	public String toString() {return jobname;}
	
    public String project_name()  { return project.name; }

	   public int budget()  { return budget; }

	   public int arrival_time()  { return arrival; }

	   public int completion_time() { return complete; }

	
}
