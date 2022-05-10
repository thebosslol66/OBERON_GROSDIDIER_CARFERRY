package poal2info.carferry;

/**
 * Store all information of driver
 * 
 * @author GROSDIDIER Alphée
 * @author OBERON Quentin
 *
 */
public class Driver {

	/**
	 * Name and first name of the driver
	 */
	private String name, firstName;
	/**
	 * The permit number of driver
	 */
	private int permNumber;
	
	/**
	 * @param _name driver name
	 * @param _firstName driver first name
	 * @param _permNumber driver permit number
	 */
	public Driver(String _name, String _firstName, int _permNumber) {
		name = _name;
		firstName = _firstName;
		permNumber = _permNumber;
	}
	
	
	/**
	 * @return true if 2 driver are equals
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Driver)) {
			return false;
		}
		Driver d = (Driver) o;
		return name == d.name &&
				firstName == d.firstName &&
				permNumber == d.permNumber;
	}
	
	/**
	 * @return driver name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return driver first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @return driver permit number
	 */
	public int getPermNumber() {
		return permNumber;
	}
	
	/**
	 * @return visualization of driver
	 */
	public String toString() {
		return name + " " + firstName;
	}
}
