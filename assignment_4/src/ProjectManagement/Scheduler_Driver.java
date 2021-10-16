package ProjectManagement;

import PriorityQueue.MaxHeap;
import PriorityQueue.PriorityQueueDriverCode;
import PriorityQueue.Student;
import Trie.Trie;
import Trie.TrieNode;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class Scheduler_Driver extends Thread implements SchedulerInterface {
     int globaltime=0;
	 Trie trie = new Trie();
	 Trie<User> trie1 = new Trie();
	 MaxHeap<Job> maxHeap1 = new MaxHeap<Job>();
	 ArrayList<Job> list1 = new ArrayList<Job>(); //requested jobs 
	 ArrayList<Job> list2 = new ArrayList<Job>(); //completed jobs
	 ArrayList<Job> list3 = new ArrayList<Job>(); //current not finished job for query purpose
	 
	 
	 
    public static void main(String[] args) throws IOException {
        Scheduler_Driver scheduler_driver = new Scheduler_Driver();

        File file;
        if (args.length == 0) {
            URL url = PriorityQueueDriverCode.class.getResource("INP");
            file = new File(url.getPath());
        } else {
            file = new File(args[0]);
        }

        scheduler_driver.execute(file);
    }

    public void execute(File file) throws IOException {

    //  URL url = Scheduler_Driver.class.getResource("INP");
    //    file = new File(url.getPath());

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found. "+file.getAbsolutePath());
        }
        String st;
        while ((st = br.readLine()) != null) {
            String[] cmd = st.split(" ");
            if (cmd.length == 0) {
                System.err.println("Error parsing: " + st);
                return;
            }

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
                case "":
                    handle_empty_line();
                    break;
                case "ADD":
                    handle_add(cmd);
                    break;
                default:
                    System.err.println("Unknown command: " + cmd[0]);
            }
        }


        run_to_completion();

        print_stats();
        print3();

    }




    private void print3() {
    	Iterator<Job> i = list3.iterator();
    	while(i.hasNext())
    	{  Job job11=i.next();
   
    	     System.out.println("Job{user='"+job11.user.name+"', project='"+job11.project.name+"', jobstatus=COMPLETED, execution_time="
    	    		 +job11.runtime+", end_time="+job11.completedtime+", name='"+job11.name+"'}");
    	}
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
    	Project p=(Project) trie.search(cmd[2]).getValue();
    	TrieNode<User> u=trie1.search(cmd[3]);
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
    	
      Job job=new Job(cmd[1],p,u.getValue(),cmd[4]);
      maxHeap1.insert(job);
      list3.add(job);
      
      
    }

    @Override
    public void handle_user(String name) {
    	User x=new User(name);
    	trie1.insert(name, x);
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
    /*	Iterator<Job> i = list1.iterator();
    	while(i.hasNext())
    	{  if(i.next().runtime>=trie.search(i.next().project.name).getValue();)
    		
    	}
    	*/
    	if(maxHeap1.numItems==0) return;
    	System.out.println("Running code");
    	System.out.println("Remaining jobs: "+maxHeap1.numItems);
    	
    	Job j=maxHeap1.extractMax();
    //	System.out.println(j.runtime);   
    	 
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
    	list2.add(j);
    	Iterator<Job> i7 = list3.iterator();
    	while(i7.hasNext())
    	{  Job job11=i7.next();
    	if(job11.project.name.equals(p.name))
			
		{    i7.remove();
		break;
    	}
    	}
    	
    	//Project: IITD.CS.OS.ASPLOS budget remaining: 60
    	System.out.println("Project: "+p.name+" budget remaining: "+p.budget);
    	System.out.println("Execution cycle completed");
    }
    
    
    @Override
    public void handle_add(String[] cmd) {
    	
    	Project p=(Project) trie.search(cmd[1]).getValue();
    	p.budget+=Integer.parseInt(cmd[2]);
    	Iterator<Job> i = list1.iterator();
    	 ArrayList<Job> lp = new ArrayList<Job>();
    	 ArrayList<Job> lp1 = new ArrayList<Job>();
    	MaxHeap<Job> maxHeap2 = new MaxHeap<Job>();
    	while(i.hasNext())
    	{  Job job1=i.next();
    	   
    		if(job1.project.name.equals(p.name))
    			
    		{    i.remove();
    		
    		    lp.add(job1);
    		    
    		    // maxHeap2.insert(job1);
    			}
    	}
   
    	
    		while(maxHeap1.numItems!=0)
    	{   Job jb=maxHeap1.extractMax();
    	    lp1.add(jb);	
    	maxHeap2.insert(jb);
    	}
    		Iterator<Job> i1 = lp.iterator();
        	while(i1.hasNext())
        	{  Job job11=i1.next();
        	     maxHeap1.insert(job11);}
        	Iterator<Job> i2 = lp1.iterator();
        	while(i2.hasNext())
        	{  Job job12=i2.next();
        	     maxHeap1.insert(job12);}
        
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
    	list2.add(j);
    	//Project: IITD.CS.OS.ASPLOS budget remaining: 60
    	System.out.println("Project: "+p.name+" budget remaining: "+p.budget);
    	System.out.println("System execution completed");
    }

    

}
