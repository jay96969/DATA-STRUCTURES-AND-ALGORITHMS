

public class Node<T> implements Position_<T>  {
	   
		public T value;
		public T value() {	
			return value;
		}
		
		Node<T> after;
		public Node<T> after() {
			return after;
		}
		

}
