package poal2info.carferry.GUI;

import static org.junit.jupiter.api.Assertions.fail;

import poal2info.carferry.Boat;
import poal2info.carferry.BoatException;
import poal2info.carferry.Car;
import poal2info.carferry.Driver;
import poal2info.carferry.Truck;
import poal2info.carferry.Vehicle;

public class Main {

	public static final Driver d1 = new Driver("Martin", "Jeanne", 22);
	public static final Driver d2 = new Driver("Dupont", "Vincent", 55);
	public static final Driver d3 = new Driver("Durand", "Marie", 34);
	public static final Driver d4 = new Driver("Grant", "Philip", 20);
	public static final Driver d5 = new Driver("Scott", "Simon", 55);
	public static final Driver d6 = new Driver("Lambert", "Alain", 44);
	public static final Vehicle v1 = new Car("RM 1054 FF", 1.2, 4.2, d1, 0);
	public static final Vehicle v2 = new Car("PO 377 AA",  1.4, 4.5, d2, 4);
	public static final Vehicle v3 = new Car("WX 456 RT",  1.2, 5.3, d3, 2);
	public static final Vehicle c1 = new Truck("AZ 678 DF", 4.0, 12.0, d4, 15.0);
	public static final Vehicle c2 = new Truck("QS 543 HJ", 5.2, 13.5, d5, 22.5);
	public static final Vehicle c3 = new Truck("BN 321 XC", 4.5, 15.0, d6, 18.0);
	
	public static void main(String[] args) {
		Integer.valueOf("3");
		Boat b = new Boat();
		try {
			b.addVehicle(v3);
			b.addVehicle(c1);
			b.addVehicle(c2);
		} catch (BoatException e) {
			e.printStackTrace();
			fail("You must organise vehicle in wedge");
		}
		MainFrame mainGUI = new MainFrame(b);

	}

}
