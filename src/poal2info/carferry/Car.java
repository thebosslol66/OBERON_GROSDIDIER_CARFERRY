package poal2info.carferry;

public class Car extends Vehicle {

	/*
	 * Store number of passenger in a Car.
	 * We exclude the driver
	 */
	private int passengersNumber;
	
	public Car(String _registration, double _weight, 
			double _length, Driver _driver, int _passengersNumber){
		super(_registration, _weight, _length, _driver);
		passengersNumber = _passengersNumber;
	}
	
	public int getPassengerNb() {
		return passengersNumber;
	}
}
