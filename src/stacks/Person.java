package stacks;

public class Person {
	private String name;
	private String SSN;
	
	public Person() {
		
	}
	public Person(String name, String SSN) {
		this.name = name;
		this.SSN = SSN;
	}
	
	public String getName()
	  {
	    return this.name;
	  }
	
	public String getSSN()
	  {
	    return this.SSN;
	  }
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setSSN(String SSN) {
		this.SSN=SSN;
	}
}
