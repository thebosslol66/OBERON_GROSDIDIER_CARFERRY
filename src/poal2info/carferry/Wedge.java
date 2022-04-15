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
	
	public Position addVehicule(Vehicle v) throws LoadingException {
		double vehicleLength = v.getLenght();
		double vehicleWeight = v.getWeight();
		
		if ((this.getTotalWeight()+vehicleWeight) > maxWeight) {
			throw new LoadingException(LoadingException.Reason.TOO_HEAVY);
		}
		
		Set<Row> rowsWithEnougthSpace = new TreeSet<Row>();
		for  (int i=0; i<rows.length; i++) {
			if (v.getLenght() < this.getLengthLeft(i)) {
				rowsWithEnougthSpace.add(rows[i]);
			}
		}
		
		if (rowsWithEnougthSpace.isEmpty()) {
			throw new LoadingException(LoadingException.Reason.NOT_ENOUGTH_SPACE);
		}
		
		Row bestRow = Collections.min(rowsWithEnougthSpace);
		
		bestRow.addVehicle(v);
		return new Position(bestRow.getRowNumber(), bestRow.getVehicleNumber());
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
	
	private double getLengthLeft(Row r) {
		return length - (r.getTotalVehicleLength() + 
				(r.getVehicleNumber()+2)*spaceBetweenVehicles);
	}
	
	private double getTotalWeight() {
		double weight = 0;
		for  (int i=0; i<rows.length; i++) {
			weight += rows[i].getTotalWeight();
		}
		return weight;
	}
	
	public Vehicle removeVehicle() {
		return null;
	}
	
	public Queue<Vehicle> getVehicleRow(int rowNumber){
		return null;
	}
}
