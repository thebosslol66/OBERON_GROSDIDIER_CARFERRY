package poal2info.carferry;

public class Truck extends Vehicle {

	private double cargoWeight;
	
	public Truck(String _registration, double _weight, 
			double _length, Driver _driver, double _cargoWeight){
		super(_registration, _weight, _length, _driver);
		cargoWeight = _cargoWeight;
	}
	
	public double getCargoWeight() {
		return cargoWeight;
	}
}
