package poal2info.carferry.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import poal2info.carferry.Boat;
import poal2info.carferry.BoatException;
import poal2info.carferry.Car;
import poal2info.carferry.Driver;
import poal2info.carferry.Ticket;
import poal2info.carferry.Truck;
import poal2info.carferry.Vehicle;

public class CarFerryController implements ActionListener, MouseListener{
	
	private Boat b;
	
	private MainFrame mainGUI;
	private WedgeFrame wedgeFrame = null;
	private RegisterFrame registerFrame = null;
	
	public CarFerryController(Boat _b) {
		//create the main window
		b = _b;
		mainGUI = new MainFrame(this);
	}

	public CarFerryController() {
		this(new Boat());
		
	}

	/* 
	 * checks if an action has been performed and opens the corresponding window
	*/
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o instanceof JMenuItem){
			if (o.equals(mainGUI.getCaleMenuItem())) {
				if (wedgeFrame == null || !wedgeFrame.isShowing()) {
					wedgeFrame = new WedgeFrame();
					wedgeFrame.update(b);
					wedgeFrame.appendEventListener(this);
				}
				else {
					wedgeFrame.setVisible(true);
					wedgeFrame.toFront();
					wedgeFrame.requestFocus();
				}
			}
		}
		if (o instanceof JButton){
			if (o.equals(mainGUI.getEmbarquer())) {
				if (registerFrame == null || !registerFrame.isShowing()) {
					registerFrame = new RegisterFrame(this);
					//get event when registration window close yo update wedge
					registerFrame.addWindowListener(new WindowAdapter() {
			            @Override
			            public void windowClosed(WindowEvent e) {
			            	if (wedgeFrame != null && wedgeFrame.isShowing()) {
								wedgeFrame.update(b);
							}
			            }
			        });
				}
				else {
					registerFrame.setVisible(true);
					registerFrame.toFront();
					registerFrame.requestFocus();
				}
			}
			if (o.equals(mainGUI.getDebarquer())) {
				try {
					Vehicle v = b.removeVehicle();
					if (wedgeFrame != null && wedgeFrame.isShowing()) {
						wedgeFrame.update(b);
					}
					JOptionPane.showMessageDialog(mainGUI, "Débarquement " + v.getRegistration(), "Débarquement", JOptionPane.WARNING_MESSAGE);
				}
				catch (BoatException ex) {
					if (ex.getReason() == BoatException.Reason.BOAT_IS_EMPTY) {
						JOptionPane.showMessageDialog(mainGUI, "La cale est vide", "Débarquement", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			 if (registerFrame != null && o.equals(registerFrame.getButtonValider())){
				 Vehicle v = registerFrame.vehicleFromForm();
				 if (v == null) {
					 return;
				 }
				 
				 try {
						b.addVehicle(v);
						registerFrame.dispose();//close window
					}
				 catch (BoatException ex) {
					 	registerFrame.dispose();//close window
						switch(ex.getReason()) {
						case NOT_ENOUGTH_SPACE:{
							JOptionPane.showMessageDialog(mainGUI, "Embarquement impossible : plus d'éspace disponible", "Embarquement", JOptionPane.WARNING_MESSAGE);
						}break;
						case TOO_HEAVY:{
							JOptionPane.showMessageDialog(mainGUI, "Embarquement impossible : limite de poids atteinte", "Embarquement", JOptionPane.WARNING_MESSAGE);
						}break;
						case DRIVER_HAVE_ALREADY_A_CAR:{
							JOptionPane.showMessageDialog(mainGUI, "Embarquement impossible : un véhicule a déjà été associè a ce conducteur", "Embarquement", JOptionPane.WARNING_MESSAGE);
						}break;
						case CAR_IS_ALREADY_LOADED:{
							JOptionPane.showMessageDialog(mainGUI, "Embarquement impossible : ce véhicule a déjà été chargé", "Embarquement", JOptionPane.WARNING_MESSAGE);
						}break;
						case CANT_LOAD_DURING_UNLOAD:{
							JOptionPane.showMessageDialog(mainGUI, "Embarquement impossible : nous débarquons déjà d'autres véhicules", "Embarquement", JOptionPane.WARNING_MESSAGE);
						}break;
						default:
							JOptionPane.showMessageDialog(mainGUI, "Une erreur est survenue lors du chargement du véhicule", "Embarquement", JOptionPane.WARNING_MESSAGE);
						}
					}
				 }
		}
		
		if (o instanceof JRadioButton){
			if (o.equals(registerFrame.getCarButton())) {
				registerFrame.activateCarsButton();
			}
			if (o.equals(registerFrame.getTruckButton())) {
				registerFrame.activateTrucksButton();
			}
		}
		
		
	}

	/**
	 * Check event for mouse clicking
	 * Usefull for JList to see informations about a vehicle
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
	   	 if (!(o instanceof JList)) {
	   		 return;
	   	 }
	   	 if (wedgeFrame != null) {
	       JList<Object> theList = (JList<Object>) o ;
	       if (e.getClickCount() >= 2) {
	       	Vehicle vehicleSelected = (Vehicle) theList.getSelectedValue();
	       	Ticket t = b.getTicketFromVehicle(vehicleSelected);
	       	String message = "[ ";
	       	switch(t.getPlaceInWedge().getRow()) {
	       	case 0:{
	       		message += "G";
	       	}break;
	       	case 1:{
	       		message += "D";
	       	}break;
	       	default:
	       		message += "?";
	       	}
	       	message += t.getPlaceInWedge().getPos() + " " + t.getName() + " " + t.getFirstName() + " " +t.getRegistration() + " " + t.getPrice() + "euros ]";
	       	JOptionPane.showMessageDialog(wedgeFrame, message, "TICKET", JOptionPane.INFORMATION_MESSAGE);
	       }
	   	 }
	}

	/**
	 * Theses are methods not use in project but implemented by interfaces
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	/**
	 * Theses are methods not use in project but implemented by interfaces
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	/**
	 * Theses are methods not use in project but implemented by interfaces
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	/**
	 * Theses are methods not use in project but implemented by interfaces
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
