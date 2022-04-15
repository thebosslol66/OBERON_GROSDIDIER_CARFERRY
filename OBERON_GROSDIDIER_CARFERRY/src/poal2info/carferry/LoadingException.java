package poal2info.carferry;

public class LoadingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 enum Reason {
		NOT_ENOUGTH_SPACE,
		TOO_HEAVY,
		DRIVER_HAVE_ALREADY_A_CAR,
		CAR_IS_ALREADY_LOADED,
		CANT_LOAD_DURING_UNLOAD
		}
	
	private Reason reason;
	
	public LoadingException(Reason r) {
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
		default:
			return "You break the program";
		}
	}
	public Reason getReason() {
		return this.reason;
	}

}
