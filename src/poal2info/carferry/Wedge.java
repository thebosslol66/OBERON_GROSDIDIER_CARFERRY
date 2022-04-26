package poal2info.carferry;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class Wedge {

	final private double spaceBetweenVehicles=0.5;
	private double length, maxWeight;
	private Row rows [];
	
	public Wedge(double _length, double _maxWeight, int nbRow) {
		length = _length;
		maxWeight = _maxWeight;
		rows = new Row[nbRow];
		for (int i=0; i<nbRow; i++) {
			rows[i] = new Row(i);
		}
	}
	
	public Position addVehicule(Vehicle v) throws BoatException {
		double vehicleLength = v.getLenght();
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
	 * Get the space left in the row
	 * 
	 * @param rowNumber the number of the row to get his space left
	 * @return the space left for the row
	 */
	private double getLengthLeft(int rowNumber) {
		return length - (rows[rowNumber].getTotalVehicleLength() + 
				(rows[rowNumber].getVehicleNumber()+2)*spaceBetweenVehicles);
	}
	
	
	private double getTotalWeight() {
		double weight = 0;
		for  (int i=0; i<rows.length; i++) {
			weight += rows[i].getTotalWeight();
		}
		return weight;
	}
	
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
	
	public Queue<Vehicle> getVehicleRow(int rowNumber) throws BoatException{
		if (rowNumber >= rows.length) {
			throw new BoatException(BoatException.Reason.ROW_DONT_EXIST);
		}
		return rows[rowNumber].getRow();
	}

	public boolean isEmpty() {
		return getTotalWeight() == 0;
	}
}
