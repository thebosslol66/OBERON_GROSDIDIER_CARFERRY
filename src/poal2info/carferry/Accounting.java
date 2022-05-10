package poal2info.carferry;

/**
 * Calculate price of all salable object in the boat
 *  
 * @author GROSDIDIER Alphée
 * @author OBERON Quentin
 *
 */
public class Accounting {
	
	/**
	 * Price of empty car (driver include)
	 */
	final double baseCarPrice=35.0;
	/**
	 * Price of empty truck (driver include)
	 */
	final double baseTruckPrice=45.0;
	/**
	 * Price of each passenger in a car (exclude driver)
	 */
	final double pricePerPassenger=3.0;
	/**
	 * Price of each Kg of cargo carry by the truck
	 */
	final double pricePerKgCargo=0.1;
	
	/**
	 * Get the price for a vehicle (Car or Truck)
	 * 
	 * @param v Vehicle to calculate price
	 * @return the price of vehicle or 0 if vehicle class is not in Car and Truck
	 */
	double getPrice(Vehicle v) {
		if (v instanceof Car) {
			Car c = (Car) v;
			return baseCarPrice + c.getPassengerNb() * pricePerPassenger;
		}
		else if (v instanceof Truck) {
			Truck t = (Truck) v;
			return baseTruckPrice + t.getCargoWeight() * 1000 * pricePerKgCargo;
		}
		else {
			return 0;
		}
	}

}
