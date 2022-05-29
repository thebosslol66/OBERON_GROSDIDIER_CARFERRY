package poal2info.carferry;

import java.util.Iterator;

/**
 * @author GROSDIDIER Alphée
 *
 * The script described by exercise
 */
public class Main {

	public static void main(String[] args) throws BoatException {
		//1
		final Driver d1 = new Driver("Martin", "Jeanne", "22FF");
		final Driver d2 = new Driver("Dupont", "Vincent", "A55");
		final Driver d3 = new Driver("Durand", "Marie", "B34");
		final Driver d4 = new Driver("Grant", "Philip", "20FF");
		final Driver d5 = new Driver("Scott", "Simon", "B55JG");
		final Driver d6 = new Driver("Lambert", "Alain", "C44Djk");
		final Vehicle v1 = new Car("RM 1054 FF", 1.2, 4.2, d1, 2);
		final Vehicle v2 = new Car("PO 377 AA",  1.4, 4.5, d2, 1);
		final Vehicle v3 = new Car("WX 456 RT",  1.2, 5.3, d3, 0);
		final Vehicle c1 = new Truck("AZ 678 DF", 4.0, 12.0, d4, 15.0);
		final Vehicle c2 = new Truck("QS 543 HJ", 5.2, 13.5, d5, 22.5);
		final Vehicle c3 = new Truck("BN 321 XC", 4.5, 15.0, d6, 18.0);
		//2
		Boat b = new Boat(25, 75, 2);
		try {
			b.addVehicle(TestBoatUtils.c1);
			b.addVehicle(TestBoatUtils.v1);
			b.addVehicle(TestBoatUtils.v2);
			b.addVehicle(TestBoatUtils.c2);
			b.addVehicle(TestBoatUtils.v3);
		}
		catch (BoatException e) {
			System.out.println("Error during loading vehicle");
		}
		try {
			b.addVehicle(TestBoatUtils.c3);
			System.out.println("Error during loading vehicle");
		} catch (BoatException e) {
			System.out.println("Normal Error: " + e.getMessage());
		}
		
		Iterator<Vehicle> itG = b.getVehiculeRow(0).iterator();
		Iterator<Vehicle> itD = b.getVehiculeRow(1).iterator();
		
		System.out.println("\nG:\t\t\t\tD:");
		while(itG.hasNext() || itD.hasNext()) {
			Vehicle vG = null;
			if (itG.hasNext()) {
				vG = itG.next();
			}
			Vehicle vD = null;
			if (itD.hasNext()) {
				vD = itD.next();
			}
			String toPrint = "  ";
			if (vG != null) {
				toPrint += vG;
			}
			else {
				toPrint += "\t\t\t";
			}
			toPrint += "\t  ";
			if (vD != null) {
				toPrint += vD;
			}
			System.out.println(toPrint);
		}
		System.out.println("\nTicket List:");
		Iterator<Ticket> itT = b.getListingTicket().iterator();
		while(itT.hasNext()) {
			Ticket t = itT.next();
			String message = " [ ";
        	switch(t.getPlaceInWedge().getRow()) {
        	case 0:{
        		message += "G";
        	}break;
        	case 1:{
        		message += "D";
        	}break;
        	default:
        		message += "?";
        	}
        	message += t.getPlaceInWedge().getPos() + " " + t.getName() + " " + t.getFirstName() + " " +t.getRegistration() + " " + t.getPrice() + "euros ]";
        	
			System.out.println(message);
		}
		
		System.out.println("\nDébarquement:");
		
		try {
			while (true) {
				Vehicle v = b.removeVehicle();
				System.out.println(" " + v);
			}
		}
		catch (BoatException e) {
			if (e.getReason() != BoatException.Reason.BOAT_IS_EMPTY) {
				System.out.println("Error during unloading boat");
			}
		}
	}

}
