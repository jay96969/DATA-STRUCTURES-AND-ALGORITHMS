package ProjectManagement;

public class UserReport implements UserReport_ {
	 int consume;
	 String username;
	 public UserReport(String un,int cons)
	 {
		 this.username=un;
		 this.consume=cons;
	 }
	 public String user()    { return username; }

	 public int consumed() { return consume; }

}
