package poal2info.carferry;

public class BoatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public enum Reason {
		NOT_ENOUGTH_SPACE,
		TOO_HEAVY,
		DRIVER_HAVE_ALREADY_A_CAR,
		CAR_IS_ALREADY_LOADED,
		CANT_LOAD_DURING_UNLOAD,
		BOAT_IS_EMPTY,
		ROW_DONT_EXIST
		}
	
	private Reason reason;
	
	public BoatException(Reason r) {
		super();
		reason = r;
	}
	
	@Override
	public String getMessage() {
		switch(reason) {
		case NOT_ENOUGTH_SPACE:{
			return "There is no free place for this vehicle";
		}
		case TOO_HEAVY:{
			return "The boat can't handle more weight";
		}
		case DRIVER_HAVE_ALREADY_A_CAR:{
			return "The vehicle have a driver which have already a vehicle in the boat";
		}
		case CAR_IS_ALREADY_LOADED:{
			return "The vehicle is already in the boat";
		}
		case CANT_LOAD_DURING_UNLOAD:{
			return "The boat is unloading it's phisically impossible to do that";
		}
		case BOAT_IS_EMPTY:{
			return "Can't unload, the boat is empty";
		}
		case ROW_DONT_EXIST:{
			return "You can't access to this row because it doesn't exist";
		}
		default:
			return "You break the program";
		}
	}
	public Reason getReason() {
		return this.reason;
	}

}
