package poal2info.carferry;

/**
 * Class to store position of vehicle in wedge
 * 
 * @author GROSDIDIER Alphée
 * @author OBERON Quentin
 */
public class Position {
	/**
	 * The number of row
	 */
	private int row;
	/**
	 * The position of vehicle in the row
	 */
	private int pos;
	
	/**
	 * @param _row number of row
	 * @param _pos position of vehicle in the row
	 */
	public Position(int _row, int _pos) {
		row = _row;
		pos = _pos;
	}
	
	/**
	 * @return the row id
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * @return the position in row
	 */
	public int getPos() {
		return pos;
	}
	
	/**
	 * @return true if 2 position are equals
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Position)) {
			return false;
		}
		Position p = (Position) o;
		return row == p.row && pos == p.pos;
	}
}
