package ProjectManagement;

public class Job implements Comparable<Job> {
	int id;
	JobReport ur;
	public String name;
	public Project project;
	public User user;
	public int runtime;
	 boolean completed=false;
	 public int completedtime;
	 
	public Job(String name,Project project,User user,String run,int arrival,int id)
	{
		this.name=name;
		this.project=project;
		this.id=id;
		JobReport j=new JobReport(user.name,Integer.parseInt(run),project,arrival);
		this.ur=j;
     	this.user=user;
     	this.ur.jobname=name;
		this.runtime=Integer.parseInt(run);
	}
	
    @Override
    public int compareTo(Job job) {
         if(this.project.priority>job.project.priority)
        	return 1;
         else if(this.project.priority==job.project.priority)
      
        	return  job.id-this.id;
      
        else return -1;
    }
    @Override
    public boolean equals(Object x)
    {   
    	return this.name.equals(x);
    }
} 