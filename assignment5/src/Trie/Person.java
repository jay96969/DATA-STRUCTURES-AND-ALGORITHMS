package Trie;

public class Person {
	public String name;
	public String phone_number;
    public Person(String name, String phone_number) {
    	this.name=name;
    	this.phone_number=phone_number;
    	
    }
    @Override
    public String toString()
    { return "[Name: "+ this.name+", Phone="+this.phone_number+"]" ;                       }
    public String getName() {
        return name;
    }
}
