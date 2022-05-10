package poal2info.carferry;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Manage group of vehicle in a row
 * Can get number and weight of vehicle
 * 
 * @author GROSDIDIER Alphée
 * @author OBERON Quentin
 *
 */
public class Row implements Comparable<Row> {
	
	/**
	 * The queue of all vehicle contained by the row 
	 */
	private Queue<Vehicle> vehicleQueue;
	/**
	 * The id of the row
	 */
	private int rowNumber;
	
	/**
	 * @param nb id of the row
	 */
	public Row(int nb) {
		vehicleQueue = new LinkedList<Vehicle>();
		rowNumber = nb;
	}
	
	/**
	 * Addf a vehicle in the row
	 * @param v vehicle to add
	 * @return the position in the row and the id of the row
	 */
	public Position addVehicle(Vehicle v) {
		vehicleQueue.add(v);
		return new Position(rowNumber, this.getVehicleNumber());
	}
	
	/**
	 * @return vehicle removed
	 */
	public Vehicle removeVehicle() {
		return vehicleQueue.poll();
	}
	
	public double getTotalVehicleLength() {
		double length = 0;
		for(Vehicle v: vehicleQueue) {
			length += v.getLenght();
		}
		return length;
	}
	
	/**
	 * @return the number of vehicle contained in the row
	 */
	public int getVehicleNumber() {
		return vehicleQueue.size();
	}

	/**
	 * @return the weight of the row
	 */
	public double getTotalWeight() {
		double weight = 0;
		for(Vehicle v: vehicleQueue) {
			if (v instanceof Truck) {
				weight += ((Truck) v).getCargoWeight();
			}
			weight += v.getWeight();
		}
		return weight;
	}
	
	/**
	 * @return the difference of weight between this vehicle and another
	 */
	@Override
	public int compareTo(Row r) {
		return (int) (this.getTotalWeight() - r.getTotalWeight());
	}
	
	/**
	 * @return the entire list of vehicle in the row
	 */
	public Queue<Vehicle> getRow(){
		return vehicleQueue;
	}
	
}
