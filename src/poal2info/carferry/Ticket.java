package poal2info.carferry;

/**
 * Class to store and get all informations for a ticket
 * 
 * @author GROSDIDIER Alphée
 * @author OBERON Quentin
 *
 */
public class Ticket implements Comparable<Ticket>{
	/**
	 * The vehicle attached to this ticket
	 */
	private Vehicle vehicle;
	/**
	 * The position of vehicle in the wedge during the travel
	 */
	private Position  placeInWedge;
	/**
	 * The price of vehicle for the travel
	 */
	private double price;
	
	/**
	 * @param _price price of vehicle for the travel
	 * @param _placeInWedge position of vehicle in the wedge
	 * @param _vehicle vehicle attached to this ticket
	 */
	public Ticket(double _price, Position _placeInWedge, Vehicle _vehicle) {
		price = _price;
		placeInWedge = _placeInWedge;
		vehicle = _vehicle;
	}

	/**
	 * @return true if the ticket is for the same vehicle
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Ticket)) {
			return false;
		}
		Ticket t = (Ticket) o;
		return vehicle.equals(t.vehicle);
	}
	
	/**
	 * Verify if the ticket vehicle is the same than vehicle
	 * 
	 * @param v vehicle to compare
	 * @return true if the ticket is for vehicle else false
	 */
	public boolean equals(Vehicle v) {
		return vehicle.equals(v);
	}
	
	/**
	 * Is the function implemented by comparable to sort Ticket with name and firstname
	 * @return ticket difference
	 */
	public int compareTo(Ticket t) {
		int diff = vehicle.getDriver().getName().compareTo(t.vehicle.getDriver().getName());
		if (diff == 0) {
			return vehicle.getDriver().getFirstName().compareTo(t.vehicle.getDriver().getFirstName());
		}
		return diff;
	}
	
	/**
	 * @return position in wedge
	 */
	public Position getPlaceInWedge() {
		return placeInWedge;
	}
	
	/**
	 * @return ticket price
	 */
	public double getPrice () {
		return price;
	}
	
	/**
	 * @return driver name 
	 */
	public String getName() {
		return vehicle.getDriver().getName();
	}
	
	/**
	 * @return driver firstName
	 */
	public String getFirstName() {
		return vehicle.getDriver().getFirstName();
	}
	
	/**
	 * @return registration of vehicle
	 */
	public String getRegistration() {
		return vehicle.getRegistration();
	}
}
