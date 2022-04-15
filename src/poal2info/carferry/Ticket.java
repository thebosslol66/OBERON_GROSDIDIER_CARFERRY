package poal2info.carferry;

public class Ticket implements Comparable<Ticket>{
	private Vehicle vehicle;
	private Position  placeInWedge;
	private double price;
	
	public Ticket(double _price, Position _placeInWedge, Vehicle _vehicle) {
		price = _price;
		placeInWedge = _placeInWedge;
		vehicle = _vehicle;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Ticket)) {
			return false;
		}
		Ticket t = (Ticket) o;
		return vehicle.equals(t.vehicle);
	}
	
	public int compareTo(Ticket t) {
		return 0;
	}
	
	public Position getPlaceInWedge() {
		return placeInWedge;
	}
	
	public double getPrice () {
		return price;
	}
	
	public String getName() {
		return vehicle.getDriver().getName();
	}
	
	public String getFirstName() {
		return vehicle.getDriver().getFirstName();
	}
	
	public String getRegistration() {
		return vehicle.getRegistration();
	}
}
