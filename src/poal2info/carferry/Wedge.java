package poal2info.carferry;

import java.util.Collections;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * The wedge manages operations of adding and removing vehicles
 * It can't carry more vehicle if all rows are filled in length or if the wedge reach the maxWeigth
 * 
 * @author GROSDIDIER Alphée
 * @author OBERON Quentin
 *
 */
public class Wedge {

	/**
	 * The space to separate vehicle to avoid colliding
	 */
	final private double spaceBetweenVehicles=0.5;
	/**
	 * The length and max weight supported by the wedge
	 * These value are limit value, it can't carry more than length and maxWeight
	 */
	private double length, maxWeight;
	/**
	 * The list of row available to carry vehicle
	 * Number of row is set by constructor
	 */
	private Row rows [];
	
	/**
	 * @param _length Maximum length for each rows
	 * @param _maxWeight Maximum weight of wedge (for all rows)
	 * @param nbRow The number of rows which can accept vehicle
	 */
	public Wedge(double _length, double _maxWeight, int nbRow) {
		length = _length;
		maxWeight = _maxWeight;
		rows = new Row[nbRow];
		for (int i=0; i<nbRow; i++) {
			rows[i] = new Row(i);
		}
	}
	
	/**
	 * Add a vehicle in Wedge in the best place to balance the wedge
	 * @param v Vehicle to add in the wedge
	 * @return Position of the vehicle in the wedge after adding it
	 * @throws BoatException BoatException.Reason.TOO_HEAVY if the vehicle can't be hold because it's weight will make wedge upper than the maxWeight
	 * @throws BoatException BoatException.Reason.NOT_ENOUGTH_SPACE if there is no space for this vehicle ion any rows
	 */
	public Position addVehicule(Vehicle v) throws BoatException {
		double vehicleWeight = v.getWeight();
		
		if (v instanceof Truck) {
			Truck t = (Truck) v;
			if ((this.getTotalWeight()+vehicleWeight+t.getCargoWeight()) > maxWeight) {
				throw new BoatException(BoatException.Reason.TOO_HEAVY);
			}
		}
		else {
			if ((this.getTotalWeight()+vehicleWeight) > maxWeight) {
				throw new BoatException(BoatException.Reason.TOO_HEAVY);
			}
		}
		
		Set<Row> rowsWithEnougthSpace = new TreeSet<Row>();
		for  (int i=0; i<rows.length; i++) {
			if (v.getLenght() < this.getLengthLeft(i)) {
				rowsWithEnougthSpace.add(rows[i]);
			}
		}
		
		if (rowsWithEnougthSpace.isEmpty()) {
			throw new BoatException(BoatException.Reason.NOT_ENOUGTH_SPACE);
		}
		
		Row bestRow = Collections.min(rowsWithEnougthSpace);
		
		return bestRow.addVehicle(v);
	}
	
	/*
	 * Get the space left in the row with space between vehicle
	 * 
	 * @param rowNumber the number of the row to get his space left
	 * @return the space left for the row
	 */
	private double getLengthLeft(int rowNumber) {
		return length - (rows[rowNumber].getTotalVehicleLength() + 
				(rows[rowNumber].getVehicleNumber()+2)*spaceBetweenVehicles);
	}
	
	
	/**
	 * Get the total weight of the Wedge
	 * 
	 * @return the weigth of Wedge
	 */
	private double getTotalWeight() {
		double weight = 0;
		for  (int i=0; i<rows.length; i++) {
			weight += rows[i].getTotalWeight();
		}
		return weight;
	}
	
	/**
	 * Remove a vehicle from Wedge to stay balanced
	 * 
	 * @return The vehicle remodved
	 * @throws BoatException BoatException.Reason.BOAT_IS_EMPTY if all vehicle are removed
	 */
	public Vehicle removeVehicle() throws BoatException {
		Set<Row> AllRows = new TreeSet<Row>();
		for (int i=0; i<rows.length; i++) {
			AllRows.add(rows[i]);
		}
		Row bestRow = Collections.max(AllRows);
		Vehicle v = bestRow.removeVehicle();
		if (v == null) {
			throw new BoatException(BoatException.Reason.BOAT_IS_EMPTY);
		}
		return v;
	}
	
	/**
	 * Get all vehicle from a row
	 * 
	 * @param rowNumber the id of the row to get vehicle
	 * @return the list of vehicle
	 * @throws BoatException BoatException.Reason.ROW_DONT_EXIST if we try top access a row witch didn't exist
	 */
	public Queue<Vehicle> getVehicleRow(int rowNumber) throws BoatException{
		if (rowNumber >= rows.length) {
			throw new BoatException(BoatException.Reason.ROW_DONT_EXIST);
		}
		return rows[rowNumber].getRow();
	}

	/**
	 * Get if the wedge is empty
	 * 
	 * @return true if empty else false
	 */
	public boolean isEmpty() {
		return getTotalWeight() == 0;
	}
}
