package ProjectManagement;

 import PriorityQueue.MaxHeap;
import PriorityQueue.PriorityQueueDriverCode;
import PriorityQueue.Student;
import RedBlack.RBTree;
import Trie.Person;
import Trie.Trie;
import Trie.TrieNode;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class Scheduler_Driver extends Thread implements SchedulerInterface {
     int globaltime=0;
     int id=0;
	 Trie<Project> trie = new Trie<Project>();
	 Trie<User> trie1 = new Trie<User>();
	 RBTree<String,Job> rbt = new RBTree(); //it store jobs as value and key as project name
	 MaxHeap<Job> maxHeap1 = new MaxHeap<Job>();//ready queue
	 ArrayList<Job> list1 = new ArrayList<Job>(); //requested jobs 
	 ArrayList<Job> list2 = new ArrayList<Job>(); //completed jobs
	 ArrayList<Job> list3 = new ArrayList<Job>(); //current unfinished job for query purpose=list1+maxheap1
	 JobReport[] list4=new JobReport[100000000];// it is sorted because in every insertion it is getting sorted.```for priority query
	 int lenght1=0;
	 UserReport[] list5=new UserReport[100000000];//it is sorted because in every insertion it is getting sorted.```for top query
	 int lenght=0;
	 
	 
	  public static void main(String[] args) throws IOException {
		  //

		          Scheduler_Driver scheduler_driver = new Scheduler_Driver();
		          File file;
		          if (args.length == 0) {
		              URL url = Scheduler_Driver.class.getResource("INP");
		              file = new File(url.getPath());
		          } else {
		              file = new File(args[0]);
		          }

		          scheduler_driver.execute(file);
		      }

		      public void execute(File commandFile) throws IOException {


		          BufferedReader br = null;
		          try {
		              br = new BufferedReader(new FileReader(commandFile));

		              String st;
		              while ((st = br.readLine()) != null) {
		                  String[] cmd = st.split(" ");
		                  if (cmd.length == 0) {
		                      System.err.println("Error parsing: " + st);
		                      return;
		                  }
		                  String project_name, user_name;
		                  Integer start_time, end_time;

		                  long qstart_time, qend_time;

		                  switch (cmd[0]) {
		                      case "PROJECT":
		                          handle_project(cmd);
		                          break;
		                      case "JOB":
		                          handle_job(cmd);
		                          break;
		                      case "USER":
		                          handle_user(cmd[1]);
		                          break;
		                      case "QUERY":
		                          handle_query(cmd[1]);
		                          break;
		                      case "": // HANDLE EMPTY LINE
		                          handle_empty_line();
		                          break;
		                      case "ADD":
		                          handle_add(cmd);
		                          break;
		                      //--------- New Queries
		                      case "NEW_PROJECT":
		                      case "NEW_USER":
		                      case "NEW_PROJECTUSER":
		                      case "NEW_PRIORITY":
		                          timed_report(cmd);
		                          break;
		                      case "NEW_TOP":
		                          qstart_time = System.nanoTime();
		                          timed_top_consumer(Integer.parseInt(cmd[1]));
		                          qend_time = System.nanoTime();
		                          System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
		                          break;
		                      case "NEW_FLUSH":
		                          qstart_time = System.nanoTime();
		                          timed_flush( Integer.parseInt(cmd[1]));
		                          qend_time = System.nanoTime();
		                          System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
		                          break;
		                      default:
		                          System.err.println("Unknown command: " + cmd[0]);
		                  }

		              }


		              run_to_completion();
		              print_stats();

		          } catch (FileNotFoundException e) {
		              System.err.println("Input file Not found. " + commandFile.getAbsolutePath());
		          } catch (NullPointerException ne) {
		              ne.printStackTrace();

		          }
		      }

		      @Override
		      public ArrayList<JobReport_> timed_report(String[] cmd) {
		          long qstart_time, qend_time;
		          ArrayList<JobReport_> res = null;
		          switch (cmd[0]) {
		              case "NEW_PROJECT":
		                  qstart_time = System.nanoTime();
		                  res = handle_new_project(cmd);
		                  qend_time = System.nanoTime();
		                  System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
		                  break;
		              case "NEW_USER":
		                  qstart_time = System.nanoTime();
		                  res = handle_new_user(cmd);
		                  qend_time = System.nanoTime();
		                  System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));

		                  break;
		              case "NEW_PROJECTUSER":
		                  qstart_time = System.nanoTime();
		                  res = handle_new_projectuser(cmd);
		                  qend_time = System.nanoTime();
		                  System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
		                  break;
		              case "NEW_PRIORITY":
		                  qstart_time = System.nanoTime();
		                  res = handle_new_priority(cmd[1]);
		                  qend_time = System.nanoTime();
		                  System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
		                  break;
		          }

		          return res;
		      }


		     @Override
		     public ArrayList<UserReport_> timed_top_consumer(int top) {
		    	 ArrayList<UserReport_> lis = new ArrayList<UserReport_>();
		    	 if(top>lenght)
			    	{
			    		top=lenght;;
			    	}
		    	 for(int r=0;r<top;r++)
		    	{   
		    	
		    		lis.add(list5[r]);
		    		
		    	}
		    	 return lis;
		     }



		     @Override
		     public void timed_flush(int waittime) {
		    	 ArrayList<Job> liss = new ArrayList<Job>();
		    	 int currg=globaltime;
		    	 while(maxHeap1.numItems>0)
		    	 {  Job j=maxHeap1.extractMax();
		    	   Project p=(Project) trie.search(j.project.name).getValue();
		    	    if(currg-j.ur.arrival>=waittime&&j.runtime<=p.budget)
		    	    { 
		    	    	j.completed=true;
		    	    	globaltime+=j.runtime;
		    	    	j.completedtime=globaltime;
		    	    	p.budget-=j.runtime;
		    	   	
		    	    	Iterator<Job> i =p.list.iterator();
		    	    	while(i.hasNext())
		    	    	{   Job jb=i.next();
		    	    		if(jb.name.equals(j.name)) 
		    	    	{
		    	    		jb.ur.complete=globaltime;
		    	    		break;
		    	    		
		    	    	}
		    	    	}
		    	    	for(int w=0;w<lenght1;w++)
		    	    	{
		    	    		if(list4[w].jobname.equals(j.name))
		    	    		{
		    	    			list4[w]=j.ur;
		    	    			break;
		    	    		}
		    	    	}
		    	    	Iterator<Job> i0 =  trie1.search(j.user.name).getValue().arrlist.iterator();
		    	    	while(i0.hasNext())
		    	    	{   Job jb=i0.next();
		    	    		if(jb.name.equals(j.name)) 
		    	    	{    i0.remove();
		    	        	jb.ur.complete=globaltime;
		    	    	    trie1.search(j.user.name).getValue().comlist.add(jb);
		    	    		
		    	    		
		    	    		break;
		    	    		
		    	    	}
		    	    	}
		    	    	
		    	    	//j.ur.complete=globaltime;
		    	    	list2.add(j);
		    	    	Iterator<Job> i7 = list3.iterator();
		    	    	while(i7.hasNext())
		    	    	{  Job job11=i7.next();
		    	    	if(job11.project.name.equals(p.name))
		    				
		    			{    i7.remove();
		    			break;
		    	    	}
		    	    	}
		    	   // updating consumed budget and sorting the array
		    	    	for(int h=0;h<lenght;h++)
		    	    	{
		    	    		if(list5[h].username.equals(j.user.name))
		    	    		{
		    	    			list5[h].consume+=j.runtime;
		    	    			
		    	    			if(h>0)
		    	    			{
		    	    			while(list5[h].consume>list5[h-1].consume)
		    	    			{   UserReport temp=list5[h];
		    	    			     list5[h]=list5[h-1];
		    	    			     list5[h-1]=temp;
		    	    			     h--;
		    	    			     if(h==0)
		    	    			     {
		    	    			    	 break;
		    	    			     }
		    	    				
		    	    			}
		    	    			}
		    	    			break;
		    	    		}
		    	    	} 
		    	    }
		    	    else if(currg-j.ur.arrival>=waittime&&j.runtime>p.budget)
		    	    {
		    	    	list1.add(j);
		    	    }
		    	    else liss.add(j);
		    	 }
		    	
		    	 Iterator<Job> iu=liss.iterator();
		    	 while(iu.hasNext())
		    	 {
		    		 maxHeap1.insert(iu.next());
		    	 }
		  
		     }
		     
		     

		     private ArrayList<JobReport_> handle_new_priority(String s) {
		    	 ArrayList<JobReport_> lis = new ArrayList<JobReport_>();
		    	 int x=Integer.parseInt(s);
		    	 int y=0;
		    	 while(y<lenght1&&list4[y].priority>=x)
		    	 {   
		    		 if (list4[y].complete==0)
		    		 {
		    			 lis.add(list4[y]);
		    		 }
		    		 y++; 
		    	 }
		         return lis;
		     }

		     private ArrayList<JobReport_> handle_new_projectuser(String[] cmd) {
		    	 ArrayList<JobReport_> lis = new ArrayList<JobReport_>();
		    	 if(trie1.search(cmd[2])==null)
				    {
				    	return lis;
				    }	
		    	 Iterator<Job> iter= trie1.search(cmd[2]).getValue().comlist.iterator();
			    	 
			    	 
			    	while(iter.hasNext()) {
			    		Job jtr=iter.next();
			    		if(jtr.project.name.equals(cmd[1])) {
			    		if(jtr.ur.arrival>=Integer.parseInt(cmd[3])&&jtr.ur.arrival<=Integer.parseInt(cmd[4]))
			    		{
			    			lis.add(jtr.ur);
			    		}
			    		}
			    		}
			    	Iterator<Job> iter1= trie1.search(cmd[2]).getValue().arrlist.iterator();
			    	while(iter1.hasNext()) {
			    		Job jtr1=iter1.next();
			    		if(jtr1.project.name.equals(cmd[1])) {
			    		if(jtr1.ur.arrival>=Integer.parseInt(cmd[3])&&jtr1.ur.arrival<=Integer.parseInt(cmd[4]))
			    		{
			    			lis.add(jtr1.ur);
			    		}
			    		}
			    		}
			    	 
			    	 return lis;
		     }

		     private ArrayList<JobReport_> handle_new_user(String[] cmd) {
		    	 ArrayList<JobReport_> lis = new ArrayList<JobReport_>();
			    if(trie1.search(cmd[1])==null)
			    {
			    	return lis;
			    }   
		    	 Iterator<Job> iter= trie1.search(cmd[1]).getValue().arrlist.iterator();
			    	while(iter.hasNext()) {
			    		Job jtr=iter.next();
			    		if(jtr.ur.arrival>=Integer.parseInt(cmd[2])&&jtr.ur.arrival<=Integer.parseInt(cmd[3]))
			    		{
			    			lis.add(jtr.ur);
			    		}
			    		}
			    	Iterator<Job> iter1= trie1.search(cmd[1]).getValue().comlist.iterator();
			    	while(iter1.hasNext()) 
			    	{
			    		Job jtr=iter1.next();
			    		if(jtr.ur.arrival>=Integer.parseInt(cmd[2])&&jtr.ur.arrival<=Integer.parseInt(cmd[3]))
			    		{
			    			lis.add(jtr.ur);
			    		}
			     	}
			    	 
			    	 return lis;
		     }

		     private ArrayList<JobReport_> handle_new_project(String[] cmd) {
		    	 ArrayList<JobReport_> lis = new ArrayList<JobReport_>();
		    	if(trie.search(cmd[1])==null)
		    	{
		    		return lis;
		    	}
		    	 Iterator<Job> iter= trie.search(cmd[1]).getValue().list.iterator();
		    	
		    	while(iter.hasNext()) {
		    		Job jtr=iter.next();
		    		if(jtr.ur.arrival>=Integer.parseInt(cmd[2])&&jtr.ur.arrival<=Integer.parseInt(cmd[3]))
		    		{
		    			lis.add(jtr.ur);
		    		}
		    		}
		    	 
		    	 return lis;
		     }





  

	@Override
    public void run() {
        // till there are JOBS
        schedule();
    }


    @Override
    public void run_to_completion() {
    	while(maxHeap1.numItems!=0)
    	{
    		run();
    	}

    }

    @Override
    public void handle_project(String[] cmd) {
        Project pro=new Project(cmd[1],cmd[2],cmd[3]);
        trie.insert(pro.name,pro);
        System.out.println("Creating project");
    }

    @Override
    public void handle_job(String[] cmd) {
    	System.out.println("Creating job");
    	if(trie.search(cmd[2])==null)
    	{   System.out.println("No such project exists. "+cmd[2]);
    		return ;
    	}
    	if(trie1.search(cmd[3])==null)
    	{   System.out.println("No such user exists: "+cmd[3]);
    		return ;
    	}  
    	Project p=trie.search(cmd[2]).getValue();
    	User u=trie1.search(cmd[3]).getValue();
    	if(p==null) 
    	{
    		System.out.println("No such project exists. "+cmd[2]);
    		return;
    	}
    	if(u==null) 
    	{
    		System.out.println("No such user exists: "+cmd[3]);
    		return;
    	}
    	
      Job job=new Job(cmd[1],p,u,cmd[4],globaltime,id);
    
      
      maxHeap1.insert(job);
      list3.add(job);
      trie.search(job.project.name).getValue().list.add(job);
      trie1.search(job.user.name).getValue().arrlist.add(job);
      rbt.insert(job.project.name, job);
      list4[lenght1]=job.ur;
      int h=lenght1;
      lenght1++;
      if(h>0)
		{
		while(list4[h].project.priority>list4[h-1].project.priority)
		{   JobReport temp=list4[h];
		     list4[h]=list4[h-1];
		     list4[h-1]=temp;
		     h--;
		     if(h==0)
		     {
		    	 break;
		     }
			
		}
		}
      
     id++; 
    }

    @Override
    public void handle_user(String name) {
    	User x=new User(name);
    	trie1.insert(name, x);
    	UserReport up=new UserReport(name,0);
    	
    	list5[lenght]=up;
    	lenght++;
    	
    	System.out.println("Creating user");

    }

    @Override
    public void handle_query(String key) {
    	System.out.println("Querying");
    	Iterator<Job> i = list2.iterator();
    	while(i.hasNext())
    	{  if(i.next().name.equals(key)) 
    	{
    		System.out.println(key+": COMPLETED");
    		return;
    	}
    	}
       Iterator<Job> ik = list3.iterator();
    	while(ik.hasNext())
    	{ 
    		if (ik.next().name.equals(key))
    	{
    		System.out.println(key+": NOT FINISHED");
    		return;
    	}
    	}	
    	
    	System.out.println(key+": NO SUCH JOB");
    	
    }

    @Override
    public void handle_empty_line() {
   
    	if(maxHeap1.numItems==0)
    {    System.out.println("Running code");
	System.out.println("Remaining jobs: "+maxHeap1.numItems);
	System.out.println("Execution cycle completed");
    		return;}
    	
    	System.out.println("Running code");
    	System.out.println("Remaining jobs: "+maxHeap1.numItems);
    	
    	Job j=maxHeap1.extractMax();
    	 
    	Project p=(Project) trie.search(j.project.name).getValue();
    	 
    	while(j.runtime>p.budget&&maxHeap1.numItems>0)
    		
    	{   
    		 System.out.println("Executing: "+j.name+" from: "+j.project.name);
    	     System.out.println("Un-sufficient budget.");
    	   
    		 list1.add(j);
    		 j=maxHeap1.extractMax();
    	     p=(Project) trie.search(j.project.name).getValue();
            
    	}
    	
    	if(j.runtime>p.budget) 
    		{list1.add(j);
    		return;}
    	System.out.println("Executing: "+j.name+" from: "+j.project.name);
    	j.completed=true;
    	globaltime+=j.runtime;
    	j.completedtime=globaltime;
    	p.budget-=j.runtime;
   	
    	Iterator<Job> i =p.list.iterator();
    	while(i.hasNext())
    	{   Job jb=i.next();
    		if(jb.name.equals(j.name)) 
    	{
    		jb.ur.complete=globaltime;
    		break;
    		
    	}
    	}
    	for(int w=0;w<lenght1;w++)
    	{
    		if(list4[w].jobname.equals(j.name))
    		{
    			list4[w]=j.ur;
    			break;
    		}
    	}
    	Iterator<Job> i0 =  trie1.search(j.user.name).getValue().arrlist.iterator();
    	while(i0.hasNext())
    	{   Job jb=i0.next();
    		if(jb.name.equals(j.name)) 
    	{    i0.remove();
        	jb.ur.complete=globaltime;
    	    trie1.search(j.user.name).getValue().comlist.add(jb);
    		
    		
    		break;
    		
    	}
    	}
    	
    	//j.ur.complete=globaltime;
    	list2.add(j);
    	Iterator<Job> i7 = list3.iterator();
    	while(i7.hasNext())
    	{  Job job11=i7.next();
    	if(job11.project.name.equals(p.name))
			
		{    i7.remove();
		break;
    	}
    	}
   // updating consumed budget and sorting the array
    	for(int h=0;h<lenght;h++)
    	{
    		if(list5[h].username.equals(j.user.name))
    		{
    			list5[h].consume+=j.runtime;
    			
    			if(h>0)
    			{
    			while(list5[h].consume>list5[h-1].consume)
    			{   UserReport temp=list5[h];
    			     list5[h]=list5[h-1];
    			     list5[h-1]=temp;
    			     h--;
    			     if(h==0)
    			     {
    			    	 break;
    			     }
    				
    			}
    			}
    			break;
    		}
    	}
    	
    	
    	System.out.println("Project: "+p.name+" budget remaining: "+p.budget);
    	System.out.println("Execution cycle completed");
    }
    
    
    @Override
    public void handle_add(String[] cmd) {
    	
    	if(trie.search(cmd[1])==null)
    	{
    		return;
    	}
    	
    	Project p=(Project) trie.search(cmd[1]).getValue();
    	p.budget+=Integer.parseInt(cmd[2]);
    	Iterator<Job> i = list1.iterator();
    	 
    	 
    
    	while(i.hasNext())
    	{  Job job1=i.next();
    	   
    		if(job1.project.name.equals(p.name))
    			
    		{    i.remove();
    		
    		    
    		   maxHeap1.insert(job1);
    		   
    			}
    	 }
   
   
    	System.out.println("ADDING Budget");
    }

    @Override
    public void print_stats() {
    	 System.out.println("--------------STATS---------------");
    	System.out.println("Total jobs done: "+list2.size());
    	Iterator<Job> i = list2.iterator();
    	while(i.hasNext())
    	{  Job job11=i.next();
   
    	     System.out.println("Job{user='"+job11.user.name+"', project='"+job11.project.name+"', jobstatus=COMPLETED, execution_time="
    	    		 +job11.runtime+", end_time="+job11.completedtime+", name='"+job11.name+"'}");
    	}
 
    	System.out.println("------------------------");
    	System.out.println("Unfinished jobs: ");
    	Iterator<Job> j = list1.iterator();
    	while(j.hasNext())
    	{  Job jo=j.next();
    	 System.out.println("Job{user='"+jo.user.name+"', project='"+jo.project.name+"', jobstatus=REQUESTED, execution_time="
	    		 +jo.runtime+", end_time=null, name='"+jo.name+"'}");
    	}
    	
    	System.out.println("Total unfinished jobs: "+list1.size());
    	System.out.println("--------------STATS DONE---------------");
    }

    @Override
    public void schedule() {
    	if(maxHeap1.numItems==0) return;
    	System.out.println("Running code");
    	System.out.println("Remaining jobs: "+maxHeap1.numItems);
    	
    	Job j=maxHeap1.extractMax();
    	 
    	Project p=(Project) trie.search(j.project.name).getValue();
    	 
    	while(j.runtime>p.budget&&maxHeap1.numItems>0)
    		
    	{   
    		System.out.println("Executing: "+j.name+" from: "+j.project.name);
    	    System.out.println("Un-sufficient budget.");
    	   
    		list1.add(j);
    		 j=maxHeap1.extractMax();
    	     p=(Project) trie.search(j.project.name).getValue();
            
    	}
    	
    	if(j.runtime>p.budget) 
    	{list1.add(j);
		return;}
    	System.out.println("Executing: "+j.name+" from: "+j.project.name);
    	j.completed=true;
    	globaltime+=j.runtime;
    	j.completedtime=globaltime;
    	p.budget-=j.runtime;
     	Iterator<Job> i = p.list.iterator();
    	while(i.hasNext())
    	{   Job jb=i.next();
    		if(jb.name.equals(j.name)) 
    	{
    		jb.ur.complete=globaltime;
    		break;
    		
    	}
    	}
    	
    	list2.add(j);
    	
    	System.out.println("Project: "+p.name+" budget remaining: "+p.budget);
    	System.out.println("System execution completed");
    }

    

}
