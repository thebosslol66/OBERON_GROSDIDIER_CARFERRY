package poal2info.carferry;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * Main class of project
 * Manage all operations of boat, add, remove vehicle.
 * And manage tickets
 * 
 * @author GROSDIDIER Alphée
 * @author OBERON Quentin
 */
public class Boat {
	
	/**
	 * The boat Wedge
	 */
	private Wedge wedge;
	/**
	 * The boat price system
	 */
	private Accounting accounting;
	/**
	 * The list of ticket for the travel
	 */
	private Set<Ticket> listing;
	/**
	 * The number of passenger in the boat (nobody in the wedge)
	 */
	private int passengers;
	/**
	 * Store the state of loading (can't load during unloading)
	 */
	private boolean canLoad;
	
	/**
	 * @param length length of the wedge
	 * @param weight max weight accepted by the boat
	 * @param rows the number of rows for accept vehicle
	 */
	public Boat(double length, double weight, int rows) {
		wedge = new Wedge(length, weight, rows);
		accounting = new Accounting();
		listing = new TreeSet<Ticket>();
		canLoad = true;
	}
	
	/**
	 * Basic constructor with exercise data
	 */
	public Boat() {
		this(25.0, 75.0, 2);
	}
	
	/**
	 * Add a vehicle to boat in the best place to balance boat
	 * 
	 * @param v vehicle to add
	 * @return the ticket of vehicle if correctly loaded
	 * @throws BoatException BoatException.Reason.CANT_LOAD_DURING_UNLOAD if the boat is loading during unloading
	 * @throws BoatException BoatException.Reason.TOO_HEAVY if the vehicle can't be hold because it's weight will make wedge upper than the maxWeight
	 * @throws BoatException BoatException.Reason.NOT_ENOUGTH_SPACE if there is no space for this vehicle ion any rows
	 */
	public Ticket addVehicle(Vehicle v) throws BoatException{
		if (!canLoad) {
			throw new BoatException(BoatException.Reason.CANT_LOAD_DURING_UNLOAD);
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
		} catch (BoatException e) {
			throw e;
		}
	}
	
	/**
	 * Remove vehicle and balance boat
	 * 
	 * @return vehicle remove from boat
	 * @throws BoatException BoatException BoatException.Reason.BOAT_IS_EMPTY if all vehicle are removed
	 */
	public Vehicle removeVehicle() throws BoatException {
		canLoad = false;
		Vehicle v = wedge.removeVehicle();
		if (wedge.isEmpty()){
			canLoad = true;
		}
		passengers -= 1;
		if (v instanceof Car) {
			passengers -= ((Car) v).getPassengerNb();
		}
		return v;
	}
	
	/**
	 * Get a list of vehicle from a row 
	 * 
	 * @param rowNumber number of the row to get the list of vehicle contained by
	 * @return list of vehicle
	 * @throws BoatException BoatException.Reason.ROW_DONT_EXIST if we try top access a row witch didn't exist
	 */
	public Queue<Vehicle> getVehiculeRow(int rowNumber) throws BoatException {
		return wedge.getVehicleRow(rowNumber);
	}
	
	/**
	 * Get all ticket from boat
	 * 
	 * @return the list of ticket 
	 */
	public Set<Ticket> getListingTicket(){
		return listing;
	}
	
	/**
	 * Get the ticket from a vehicle
	 * 
	 * @param v vehicle to get ticket
	 * @return the ticket of vehicle if it exist else null
	 */
	public Ticket getTicketFromVehicle(Vehicle v) {
		for (Iterator<Ticket> it = listing.iterator(); it.hasNext(); ) {
			Ticket t = it.next();
	        if (t.equals(v))
	            return t;
	    }
		return null;
	}

	/**
	 * @return passenger number currently in boat
	 */
	public Integer getPassengers() {
		return passengers;
	}

}
