package poal2info.carferry;

public class Driver {

	private String name, firstName;
	private int permNumber;
	
	/* A modifier */
	public boolean equals(Object o) {
		if (!(o instanceof Driver)) {
			return false;
		}
		Driver d = (Driver) o;
		return name == d.name &&
				firstName == d.firstName &&
				permNumber == d.permNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public int getPermNumber() {
		return permNumber;
	}
}
