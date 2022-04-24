package poal2info.carferry;

import java.util.LinkedList;
import java.util.Queue;

public class Row implements Comparable<Row> {
	
	private Queue<Vehicle> vehicleQueue;
	private int rowNumber;
	
	public Row(int nb) {
		vehicleQueue = new LinkedList<Vehicle>();
		rowNumber = nb;
	}
	
	public void addVehicle(Vehicle v) {
		vehicleQueue.add(v);
	}
	
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
	
	public int getVehicleNumber() {
		return vehicleQueue.size();
	}

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
	
	@Override
	public int compareTo(Row r) {
		return (int) (this.getTotalWeight() - r.getTotalWeight());
	}

	public int getRowNumber() {
		// TODO Auto-generated method stub
		return rowNumber;
	}
	
	public Queue<Vehicle> getRow(){
		return vehicleQueue;
	}
	
}
