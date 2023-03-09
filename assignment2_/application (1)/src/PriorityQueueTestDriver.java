// Use this driver for the testing the correctness of your priority queue implementation
// You can change the add, remove sequence to test for various scenarios.
public class PriorityQueueTestDriver {
    public static void main(String[] args) {
	
    Queue<String> pq = new Queue<String>(5);
	pq.add(4, "A");
	pq.enqueue( new Node(5,"r"));
	
	pq.add(10, "B");
	pq.add(5, "C");
	pq.add(2, "E");
	pq.add(5, "F");
	pq.add(5, "g");
	
	
	//pq.display();
	int size = pq.size();
	for (int i=0; i<size; i++) {
            NodeBase<String> n = (Node<String>) pq.removeMin();
            
            System.out.print(n.value);
            n.show();
	}
    }
}
