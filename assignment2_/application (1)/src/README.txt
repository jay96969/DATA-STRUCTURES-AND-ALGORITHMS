I have implemented node class using nodebase interface which contain priority and value objects.
then i have implemented queue class for inventory.it has two main method enqueue and dequeue objects.enqueue method add a node to queue class
at the end.and dequeue remove an element from starting point of queue.
then i have implemented a priority queue which also have two main method enqueue and dequeue.
   enqueue method insert a node in queue according to its priority. thus queue become a decending order array acoording to priority.
   dequeue method remove a element from end of queue array because it has maximum priority.
then i have implemented seller and buyer classes.
    seller class takes a element from inventory and insert in catalog(priority queue);
       first it accuire a lock and check whether catalog is full or not .if its full then it waits till it become notFull.
        after adding a element it releases the lock.
     buyer class takes a item of hightest priority from catalog and dequeue it. 
         first it accuire a lock and check whether catalog is empty or not .if its empty then it waits till it become notEmpty.
        after removing an element it releases the lock.
then i have implemented the driver class .first i add the all items to the inventory by iterating through a loop.
        then i created (numBuyers)number of buyer threads and numSellers of seller threads.
 then i started all the threads.
      
        