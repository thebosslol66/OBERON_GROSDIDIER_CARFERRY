package poal2info.carferry;

public class Position {
	private int row;
	private int pos;
	
	public Position(int _row, int _pos) {
		row = _row;
		pos = _pos;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getPos() {
		return pos;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Position)) {
			return false;
		}
		Position p = (Position) o;
		return row == p.row && pos == p.pos;
	}
}
