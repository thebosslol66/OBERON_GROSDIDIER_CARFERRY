package poal2info.carferry;

public class Vehicle {

	private String registration;
	private double weight, length;
	private Driver driver; 
	
	public Vehicle(String _registration, double _weight, 
			double _length, Driver _driver) {
		registration = _registration;
		weight = _weight;
		length = _length;
		driver = _driver;
	}
	
	public Driver getDriver() {
		return driver;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public double getLenght() {
		return length;
	}
	
	public String getRegistration() {
		return registration;
	}

}
