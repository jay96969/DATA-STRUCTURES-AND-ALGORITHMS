package ProjectManagement;

public class Job implements Comparable<Job> {
	
	
	public String name;
	public Project project;
	public User user;
	public int runtime;
	 boolean completed=false;
	 public int completedtime;
	public Job(String name,Project project,User user,String run)
	{
		this.name=name;
		this.project=project;
		this.user=user;
		this.runtime=Integer.parseInt(run);
	}
    @Override
    public int compareTo(Job job) {
        if(this.project.priority>job.project.priority)
        	return 1;
        else if(this.project.priority==job.project.priority)
         return 0;
        else return -1;
    }
    @Override
    public boolean equals(Object x)
    {   
    	return this.name.equals(x);
    }
} 