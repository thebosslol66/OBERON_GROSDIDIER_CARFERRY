package poal2info.carferry;

/**
 * The base class to implement a vehicle
 * It's abstract because we never see a vehicle it can only be implemented by a truck or a car
 * 
 * @author GROSDIDIER Alphée
 * @author OBERON Quentin
 *
 */
public abstract class Vehicle {

	/**
	 * The registration of vehicle
	 */
	private String registration;
	/**
	 * The weight and length of the vehicle
	 */
	private double weight, length;
	/**
	 * The driver of the vehicle
	 */
	private Driver driver; 
	
	/**
	 * @param _registration The registration of vehicle
	 * @param _weight The weight of the vehicle
	 * @param _length The length of the vehicle
	 * @param _driver The driver of vehicle
	 */
	public Vehicle(String _registration, double _weight, 
			double _length, Driver _driver) {
		registration = _registration;
		weight = _weight;
		length = _length;
		driver = _driver;
	}
	
	/**
	 * @return Driver of vehicle
	 */
	public Driver getDriver() {
		return driver;
	}
	
	/**
	 * @return weigth of vehicle
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * @return length of vehicle
	 */
	public double getLenght() {
		return length;
	}
	
	/**
	 * @return registration of vehicle
	 */
	public String getRegistration() {
		return registration;
	}
	
	/**
	 * @return visualization of vehicle in a string
	 */
	public String toString() {
		return driver + " " + registration;
	}

}
