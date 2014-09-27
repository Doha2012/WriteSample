package main;

import java.io.Serializable;

public class Foo implements Serializable{

	private static final long serialVersionUID = -8053826299713728036L;
	 private long id;
	    private String name;
	
	
	
	public Foo() {
	}

	public Foo(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	// put  data to string array
	public String[] toStringArray(){
		String[] arr = new String[2];
		arr[0] = id+"";
		arr[1] = name;
		return arr;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
