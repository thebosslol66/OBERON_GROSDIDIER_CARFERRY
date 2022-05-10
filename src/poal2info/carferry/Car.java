package poal2info.carferry;

/**
 * Create a Car like vehicle with specification of passenger number
 * @author GROSDIDIER Alphée
 * @author OBERON Quentin
 *
 */
public class Car extends Vehicle {

	/*
	 * Store number of passenger in a Car.
	 * We exclude the driver
	 */
	private int passengersNumber;
	
	/**
	 * @param _registration The registration of vehicle
	 * @param _weight The weight of the vehicle
	 * @param _length The length of the vehicle
	 * @param _driver The driver of vehicle
	 * @param _passengersNumber number of passenger in the vehicle
	 */
	public Car(String _registration, double _weight, 
			double _length, Driver _driver, int _passengersNumber){
		super(_registration, _weight, _length, _driver);
		passengersNumber = _passengersNumber;
	}
	
	/**
	 * @return number of passenger
	 */
	public int getPassengerNb() {
		return passengersNumber;
	}
}
