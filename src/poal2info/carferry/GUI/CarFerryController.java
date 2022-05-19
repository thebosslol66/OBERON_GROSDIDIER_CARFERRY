package poal2info.carferry.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
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

public class CarFerryController implements ActionListener{
	
	
	public static final Driver d1 = new Driver("Martin", "Jeanne", 22);
	public static final Driver d2 = new Driver("Dupont", "Vincent", 55);
	public static final Driver d3 = new Driver("Durand", "Marie", 34);
	public static final Driver d4 = new Driver("Grant", "Philip", 20);
	public static final Driver d5 = new Driver("Scott", "Simon", 55);
	public static final Driver d6 = new Driver("Lambert", "Alain", 44);
	public static final Vehicle v1 = new Car("RM 1054 FF", 1.2, 4.2, d1, 0);
	public static final Vehicle v2 = new Car("PO 377 AA",  1.4, 4.5, d2, 4);
	public static final Vehicle v3 = new Car("WX 456 RT",  1.2, 5.3, d3, 2);
	public static final Vehicle c1 = new Truck("AZ 678 DF", 4.0, 12.0, d4, 15.0);
	public static final Vehicle c2 = new Truck("QS 543 HJ", 5.2, 13.5, d5, 22.5);
	public static final Vehicle c3 = new Truck("BN 321 XC", 4.5, 15.0, d6, 18.0);
	
	private Boat b;
	
	private MainFrame mainGUI;
	private WedgeFrame wedgeFrame = null;
	private RegisterFrame registerFrame = null;
	public CarFerryController() {
		b = new Boat();
		
		try {
			b.addVehicle(v3);
			b.addVehicle(c1);
			b.addVehicle(c2);
		} catch (BoatException e) {
			e.printStackTrace();
		}
		
		mainGUI = new MainFrame(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o instanceof JMenuItem){
			if (o.equals(mainGUI.getCaleMenuItem())) {
				if (wedgeFrame == null || !wedgeFrame.isShowing()) {
					wedgeFrame = new WedgeFrame(b);
					
					
					MouseListener listInfoListener = new MouseAdapter() {
						private WedgeFrame parent;
						private JOptionPane dialogBox;
						private MouseAdapter init(WedgeFrame _parent) {
							parent = _parent;
							return this;
						}
					      public void mouseClicked(MouseEvent mouseEvent) {
					    	 Object o = mouseEvent.getSource();
					    	 if (!(o instanceof JList)) {
					    		 return;
					    	 }
					        JList<Object> theList = (JList<Object>) o ;
					        if (mouseEvent.getClickCount() >= 2) {
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
					        	JOptionPane.showMessageDialog(parent, message, "TICKET", JOptionPane.INFORMATION_MESSAGE);
					        }
					      }
					}.init(wedgeFrame);
					
					wedgeFrame.appendEventListener(listInfoListener);
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
			 if (o.equals(registerFrame.getButtonValider())){
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

}
