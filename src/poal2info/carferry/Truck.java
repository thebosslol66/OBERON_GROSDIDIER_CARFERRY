package poal2info.carferry;

/**
 * Create a truck like a vehicle but add a cargo for the truck specification
 * 
 * @author GROSDIDIER Alphée
 * @author OBERON Quentin
 *
 */
public class Truck extends Vehicle {

	/**
	 * The weight of the cargo carry by the Truck
	 */
	private double cargoWeight;
	
	/**
	 * @param _registration The registration of vehicle
	 * @param _weight The weight of the vehicle
	 * @param _length The length of the vehicle
	 * @param _driver The driver of vehicle
	 * @param _cargoWeight the weigth carry by the Truck
	 */
	public Truck(String _registration, double _weight, 
			double _length, Driver _driver, double _cargoWeight){
		super(_registration, _weight, _length, _driver);
		cargoWeight = _cargoWeight;
	}
	
	/**
	 * @return cargo weight
	 */
	public double getCargoWeight() {
		return cargoWeight;
	}
}
