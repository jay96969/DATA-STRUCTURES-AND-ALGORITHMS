Firstly i have implemented the hashentry node for DH(doublehashing class) and this take a key and a value;
then i have implemented Dh class with given interface.
     1. insert = it takes the key as concat string and find index through both hash function  untill we get a null node in that index.
      2.delete=  it takes the key as concat string and find index through both hash function untill we get a null node.
                       and at last i have a assignTemp function which assign a duplicate value to deleted node.
      3.contain= it takes the key as concat string and find index through both hash function untill we get a null node.
                        and return true if we find it otherwise false.
      4.update= it takes the key as concat string and find index through both hash function untill we get a null node.
                      if we found that key we will update its value.
      5.get=it takes the key as concat string and find index through both hash function untill we get a null node.
                      if we found that key we will return its value.
       6.address=it takes the key as concat string and find index through both hash function untill we get a null node.
                      if we found that key we will return its index.
then i have implemented SCBST class and BSTnode class .Bstnode class has a key and a value and two pointer(left,right);
  SCBST have been implemented with given interface.
     1. insert = it takes the key as first name and compare its value to current node(start with root) with compareTo method
                      and if it is +ve then move to right pointer of current node otherwise move left.Do this while we didnt get null node.
                      then insert given value and key to that node.  
      2.delete= case 1.Node to be removed has no children.
                            This case is quite simple. Algorithm sets corresponding link of the parent to NULL and disposes the node.
                       case 2.Node to be removed has one child.
                                    It this case, node is cut from the tree and algorithm links single child (with it's subtree) directly to the parent of the removed node.
                         case3.if it has 2 child
                                     find a minimum value in the right subtree;
                                      replace value of the node to be removed with found minimum. 
                                       Now, right subtree contains a duplicate.Remove it through first two cases.

      3.update= it takes the key as first name and compare its value to current node(start with root) with compareTo method
                      and if it is +ve then move to right pointer of current node otherwise move left.Do this while we didnt get matching key.
                      then update given value and key to that node.  
      4.contains= it takes the key as first name and compare its value to current node(start with root) with compareTo method
                      and if it is +ve then move to right pointer of current node otherwise move left.Do this while we didnt get matching key.
                      return true or false.
      5.get= it takes the key as first name and compare its value to current node(start with root) with compareTo method
                      and if it is +ve then move to right pointer of current node otherwise move left.Do this while we didnt get matching node.
                      then return its value.
       6.address= it takes the key as first name and compare its value to current node(start with root) with compareTo method
                      and if it is +ve then move to right pointer of current node and add "R" to a null string otherwise move left and add "L" to that string.Do this while we didnt get maching node.
                    return the string.
Student class has been implemented with given interface.which return the corresponding values.
tuple class=it has two overriden method equals and to string.tostring return concat of pair.equals compair two pairs.
Pair class =it has two overriden method equals and to string.tostring return first value of pair.
Assignment3 class=it has three function 
        1.doublehashing=it create a DH table. 
                                   it reads the file through buffered reader and insert data through given keywords.
        2.separatechain=it create table of SCBST class.
                                    it reads the file through buffered reader and insert data through given keywords.
        3. main function=call either one of first two functions.
      
                     




	



**********Worst-Case Time Complexity (Double Hashing)
update: O(n) — we  may have to probe over all n elements
Insert: O(n) — we may have to probe over all n elements
Delete: O(n) — we may have to probe over all n elements
contain/get:O(n)-- we may have to probe over all n elements

*********Average-Case Time Complexity (Double Hashing)
—  generally depends on efficiency of hash functions.
update: O(1)
Insert: O(1) 
Delete: O(1) 
contains/get:O(1)

*********Best-Case Time Complexity (Double Hashing)
update: O(1) — No collisions
Insert: O(1) — No collisions
delete: O(1) — No collisions
contains/get: O(1) — No collisions


#######Worst-Case Time Complexity ( Binary Search Tree)

update/contains/get: O(n) — If we insert the elements in ascending/descending order, we get a Linked List
Insert: O(n) — If we insert the elements in ascending/descending order, we get a Linked List
Remove: O(n) — If we insert the elements in ascending/descending order, we get a Linked List

######Average-Case Time Complexity (Binary Search Tree)
Find/update/get: O(log n) — when we have balanced tree(each node have two subnode except last layer)
                                                  In each layer there will be 2^n nodes.
                                                   there will be log(n) layers for n nodes;
                                                  thus we will transvers logn times to find the node at average .
                                                 
Insert: O(log n) — Effectively the same algorithm as find, with the actual insertion being a O(1) pointer rearrangement
Remove: O(log n) --too complex for summery.

######Best-Case Time Complexity (Binary Search Tree)
Find/update/get: O(1) — The query is the root
Insert: O(1) — The root only has one child and the node we are inserting becomes the root's other child
Remove: O(1) — We are removing the root and the root only has one child