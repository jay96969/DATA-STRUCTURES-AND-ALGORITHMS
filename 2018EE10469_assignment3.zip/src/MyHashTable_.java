class NotFoundException extends Exception {} 
 
public interface MyHashTable_<K,T> { 
   
   public int insert(K key, T obj); 
 
   // Update object for given key 
   public int update(K key, T obj); 
 
   // Delete object for given key 
   public int delete(K key); 
 
   // Does an object with this key exist? 
   public boolean contains(K key); 
 
   // Return the object with given key 
   public T get(K key) throws NotFoundException; 
 
   // ”Address” of object with given key (explained below) 
   public String address(K key) throws NotFoundException; 
}