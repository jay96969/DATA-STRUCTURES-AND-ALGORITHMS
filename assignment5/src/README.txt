trie;


1  trie node;
     it has 127 character array,and it has a boolean isEnd which do check for end of node
     and a getvalue fucntion that return value of end node;
  ////////Time complexity=(h)
    where h is height
  
In trie class
   1. isEmpty=it check whether a node has empty array;
   2.  insert = it iterate through root node and when it find a null index in any node
                      it create a new node.at the end of word it turn the  isEnd true
                     and assign a value to that node.
    3. delete = it iterate through root node and every time it store the parent pointer
                       and a son index.
                        when we reach at the end we iterate word backward through the 
                         son and parent pointer and check whether count of subnodes 
                     are 0 or not OR its isEnd boolean is true or false.
                       turn the son index null accordingly.
  4.  prefix.=it itrate through prefix word  and return that last node;
   5 print level=it recursively reach at the required level node and add those char to a string
                        and i use bubble sort to sort the char in array.and return the array.
    6. print =it print all levels.
                         count of total level are store in depth integer.
    
     7.printtrie. it recursively  reach all end nodes after that node and return the value.  
     8. seach . it itrate through word and check wether there isEnd true or not.


2. Max heap
     //////////time complexity=O(h)
       where h is height of heap
       pair class
                it made up with T and an int.
                  it works as node of heap.T store the value and and int store the insert time
          insert ;
                  insert element at last and call the sift up function
          shiftup; it shift up the last node till it find higher priority node or equal priority node
         delete=it remove the first element of array and put last at first.
                        call the siftdown function
            shiftdown=  it shift the first node down till it find the less priority node.

3.  redblacktree;
    time complexity=O(h)
    where h is height
      rbnode  ;it has a arraylist to store  the values of same keys.
                     it has two pointer left n right
                     a boolean color; a int size;
                     
        rbtree
               1.insertnode; first it check if the node is null or not.
                                    it recursively reach at null node(simple bst method)
                                    Color of a NULL node is considered as BLACK.
                         

          Let x be the newly inserted node.assign its color red.
1) If x is root, change color of x as bla;ck   

3) if color of x’s parent is not black and x is not root.
        a) If x’s uncle is Red 
            recloring

b) If x’s uncle is BLACK, then there can be four configurations for x, x’s parent (p) and x’s grandparent (g) 
    a) Left Left Case=right rotation and swap color
.   b) Left Right Case =left rotation and apply case a
    c) Right Right Case=left rotate and swap color
    d) Right Left Case=left rotate and apply case c



/////////////////////////////////////PROJECT Management

Job class =it has a constractor , compare to method(using priority).
 jobreport= it has a constractor ,user and all other method given in interface
Project=it contains a constractor ,and a arraylist to store job


Implementation of scheduler driver methods


///////////////// data storing elements
1. trie=it store all project with key as projectname;
2. trie1=it store all users with key as username;
3.rbt=in this rb tree key is projectname and value is Job;
 4maxHeap1=it is ready queue store unfinished jobs
5.list1=it is for requested jobs
6.list2=it is for completed jobs and we use it in printing completed jobs;
7.list3=it contain all unfinished jobs(maxheap+all jobs having unsufficient budget)
            it is used for query purpose;wether a job is executed or not;
8.list4=it is array of jobreport and every time we insert an item in this we use insertion sort.
            so it is sorted by priority.it is used to handle_new_priority query
9.list5=it is array of userreport and     every time we insert an item in this we use insertion sort so it is
            sorted by consumed budget.

////////////////////////Methods
handle project =it constract a project and insert in trie;
                         time complexity=it is stored in combination of trie and array.
                            O(h*n)   
handle job=it first check whether the project or user exist or not.
                    it fetches the priority from project(trie).
                      then constract job and insert in maxheap,rbt.
                      it insert the job in arraylist(job) of corresponding user(trie1)
                     then it insert the job in list4 by insertion sort by priority.
                    time complexity=it is stored in combination of trie and array.
                          O(h*n)   
handle user=create a user and insert in trie1.
                       it makes user report and insert in list5;
                     time complexity=it is stored in combination of trie and array.
                           O(h*n)   
handle query=it iterate list2 and check whether it is fcompleted then itrate list3 and check whether it is finished.
                        else it print no such job.
                       time complexity=it is stored in combination of trie and array.
                            O(h*n)   
handle emplty line=it  extract a job and check if it has sufficient budget.
                               if not then put it in arraylist list2.if yes then it changes its status to complete.
                               increase global time,and reduce the budget of project by its runntime;
                                then it insert complete time in corresponding job;
                               it remove this job from list3. it update the consumed budget of corresponding userreport in list5
                               and sort it.(insertion sort)
                              
handle  add=if fetches corresponding project from trie and add the budget.
                      then it transver through list1 and if the corresponding job now have require budget then it put it back into 
                    heap.
schedule =same as handle add except last printing line.
//./////assignment5 queries
NEW project =it search corresponding project in trie and iterate its jobreport arraylist.
                       check whether arrival time is in require interval or not.
                     if it is then  adds this jobreport to return arraylist .
                        
 New user =it fetches corresponding user from trie1 and  iterate its jobreport arraylist.(both complete list and arrival list)
                       check whether arrival time is in require interval or not.
                     if it is then  adds this jobreport to return arraylist .   
                      time complexity=it is stored in combination of trie and array.
                            O(h*n)        

new projectuser=search user in trie1 and first itrate completed jobreport arraylist then arrival jobreport arraylist.
                           check whether the project name matches or not if yes then add it to return jobreport list.
                           time complexity=it is stored in combination of trie and array.
                            O(h*n)  
new priority=it iterate list4 which is sorted.check whether the job complete time is 0 or not.
                    if it is zero then add this jobreport to return list.
                   time complexity=it is stored in sorted  array.
                            O(1)
New top= it return the top require elements of list5.
                 list5 is sorted by consumed budget.
            time complexity=it is stored in sorted  array.
                            O(1)


New Flush=i extract a element from heap and check its waittime if the wait is greater or equal to require waittime
                     then we check its budget. if it sufficient the execute it other wise store in a list(liss);
                      at last we insert back the liss to maxheap;
              time complexity=O(n);
//////////////////////////////////////
///////////////////////////////////////////////////////////


