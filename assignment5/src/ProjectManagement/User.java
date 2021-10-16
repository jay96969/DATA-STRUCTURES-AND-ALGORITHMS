package ProjectManagement;

import java.util.ArrayList;

public class User implements Comparable<User> {
	 ArrayList<Job> comlist = new ArrayList<Job>();
	 ArrayList<Job> arrlist = new ArrayList<Job>();
	// UserReport ur=new UserReport();
    public String name;
    public User(String name)
    {
    	this.name=name;
    	//this.ur.username=name;
    	
    }
    @Override
    public int compareTo(User user) {
        return this.name.compareTo(user.name);
    }
}
