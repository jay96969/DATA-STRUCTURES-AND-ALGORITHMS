I have implemented two function geddata and answerqueries in the main class Assignment1.
GetData:  it reads first two input strings and insert the corresponding data in all the three linked lists.
                 first linked list  contains all hostels and second ;all Departments and third all courses.
Allhostels:this linked list has object of all hostels and each hostel object has a linked list called students list which contains all students of that hostel.
 studentlist contains student class which i have also implemented.
In linked list i have created a add method with the help of node class and i have also created a  iterator function in linked list.
Iterator object itself have two object hasnext() and next();hastnext is a boolen ;next object return a current node. 
In node class there is two objects value and after which return the same as their name indicates.
Student class have many objects which return the information about that student such as cgpa ,name,department,hostel,entry number,completed credits,
and a linked list named courselist which has information about all courses that the student have completed or studying.
In getdata function first it reads the first file and add hostels object in allhostles;
then it again read the file and add object alldepartment and courses in their linked list.
then again read the file and iterate the allhostels and add student object in their linkedlist(studentlist);
when buffered reader read the second file and iterate the all hostel. inside this iteration it iterate all student list.  and each student object has a linked
list called courselist; then it read the file and insert the all courses information in that student.
AnswerQueries:
I have implemented the function answer queries this takes the parameters array of students and the linked list all hostels all departments all courses 
and the file name of queries file then it reads the file line by line tokenize it checks what type of query type it is . If the query type is info then it iterates
 over the array of students and checks if the given data of  student matches with that of the the one in each student object and if it matches it collects the the
 information from the Student object and adds it to a string and then prints the string. If there is a need to sort the elements it first stores all the elements in an array 
and I have implemented a code to sort an array which iterates over all of its elements and checks if it is more than any of the other elements of the array and if it is more
 it exchanges their position. If the query is share it iterates over all the hostels and all the departments and all the courses to check which which is matching with the keyword 
and if it matches it iterates over all the students of that particular object and stores it in an array to sort and then at last it prints all of them in a line. If the query type is course title 
it iterates over array of students and then then iterates over course list of each student to match the keyword and if it matches it prints the title  of course using course grade object.