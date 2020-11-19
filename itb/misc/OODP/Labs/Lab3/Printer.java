package Lab3;

public class Printer {
	private String name;
	private String location;
	private boolean avail;
	
	public Printer() {
		name = "HP Deskjet 930c";
		location = "Office";
		avail = true;
	}
	
	public Printer(String n, String loc, boolean free) {
		name = n;
		location = loc;
		avail = free;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLoc() {
		return location;
	}
	
	public boolean getAvail() {
		return avail;
	}
}
