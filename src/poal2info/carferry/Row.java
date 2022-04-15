package poal2info.carferry;

import java.util.Queue;

public class Row implements Comparable<Row> {
	
	private Queue<Vehicle> vehiculeQueue;
	private int rowNumber;
	
	public void addVehicle(Vehicle v) {
		
	}
	
	public Vehicle removeVehicle() {
		return null;
	}
	
	public double getTotalVehicleLength() {
		return 0.0;
	}
	
	public int getVehicleNumber() {
		return 0;
	}

	public double getTotalWeight() {
		double weight = 0;
		for(Vehicle v: vehiculeQueue) {
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
	
	
}
