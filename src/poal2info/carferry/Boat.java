package poal2info.carferry;

import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class Boat {
	
	private Wedge wedge;
	private Accounting accounting;
	private Set<Ticket> listing;
	private int passengers;
	private boolean canLoad;
	
	public Boat(double length, double weight, int rows) {
		wedge = new Wedge(length, weight, rows);
		accounting = new Accounting();
		listing = new TreeSet<Ticket>();
		canLoad = true;
	}
	
	public Boat() {
		this(25.0, 75.0, 2);
	}
	
	public Ticket addVehicle(Vehicle v) throws LoadingException{
		if (!canLoad) {
			throw new LoadingException(LoadingException.Reason.CANT_LOAD_DURING_UNLOAD);
		}
		try {
			Position p = wedge.addVehicule(v);
			double price = accounting.getPrice(v);
			Ticket t = new Ticket(price, p, v);
			listing.add(t);
			passengers +=1;
			if (v instanceof Car) {
				passengers += ((Car) v).getPassengerNb();
			}
			return t;
		} catch (LoadingException e) {
			throw e;
		}
	}
	
	public Vehicle removeVehicle() {
		return wedge.removeVehicle();
	}
	
	public Queue<Vehicle> getVehiculeRow(int rowNumber) {
		return wedge.getVehicleRow(rowNumber);
	}
	
	public Set<Ticket> getListingTicket(){
		return listing;
	}
	
	public Ticket getTicketFromVehicle(Vehicle v) {
		return null;
	}

}
