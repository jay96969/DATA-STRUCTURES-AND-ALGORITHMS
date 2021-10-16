



BUYER AND SELLER DATA STRUCTURE

Description- Most of the online shopping websites use multithreading and priority queue data structure for thier websites.This assignment is merely a mimic of working of these websites.


CODE EXPLAINED
I have implemented node class using nodebase interface which contain priority and value objects.
then i have implemented queue class for inventory.it has two main method Enqueue and Dequeue objects.Enqueue method add a node to queue class
at the end.and Dequeue remove an element from starting point of queue.
then i have implemented a priority queue which also have two main method enqueue and dequeue.
Enqueue method insert a node in queue according to its priority. thus queue become a decending order array acoording to priority.
Dequeue method remove a element from thr end of queue since it has the max priority.
Then i have implemented seller and buyer classes.Seller class takes a element from inventory and insert in catalog(priority queue);
First it accuire a lock and check whether catalog is full or not .if its full then it waits till it become notFull.After adding a element it releases the lock.
Buyer class takes a item of hightest priority from catalog and dequeue it. 
First it accuire a lock and check whether catalog is empty or not .if its empty then it waits till it become notEmpty.
After removing an element it releases the lock.
Then i have implemented the driver class .first i add the all items to the inventory by iterating through a loop then i created (numBuyers)number of buyer threads and numSellers of seller threads.Then i started all the threads.
      
        
