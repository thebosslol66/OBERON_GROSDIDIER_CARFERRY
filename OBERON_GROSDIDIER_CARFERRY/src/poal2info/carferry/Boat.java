package poal2info.carferry;

import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class Boat {
	
	private Wedge wedge;
	private Accounting accounting;
	private Set<Ticket> listing;
	private boolean canLoad;
	
	public Boat() {
		wedge = new Wedge();
		accounting = new Accounting();
		listing = new TreeSet<Ticket>();
		canLoad = true;
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
		return null;
	}
	
	public Ticket getTicketFromVehicle(Vehicle v) {
		return null;
	}

}
