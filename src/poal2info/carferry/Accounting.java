package poal2info.carferry;

public class Accounting {
	
	final double baseCarPrice=35.0;
	final double baseTruckPrice=45.0;
	final double pricePerPassenger=3.0;
	final double pricePerKgCargo=0.1;
	
	//Ask if driver is a passenger and is in storage 
	double getPrice(Vehicle v) {
		if (v instanceof Car) {
			Car c = (Car) v;
			return baseCarPrice + c.getPassengerNb() * pricePerPassenger;
		}
		else if (v instanceof Truck) {
			Truck t = (Truck) v;
			return baseTruckPrice + t.getCargoWeight() * pricePerKgCargo;
		}
		else {
			return 0;
		}
	}

}
