package poal2info.carferry.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import poal2info.carferry.Car;
import poal2info.carferry.Driver;
import poal2info.carferry.Truck;
import poal2info.carferry.Vehicle;

/**
 * @author GROSDIDIER Alphée
 * @author OBERON Quentin
 *
 * The class to manage the registration of vehicle
 */
public class RegisterFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Container c;
	
	private JRadioButton carButton, TruckButton;
	private ButtonGroup vehicleGroup;
	private JTextField inputRegistration, inputDriverName, inputDriverFirstName, inputDriverPerm;
	private JFormattedTextField inputPassenger, inputWeight, inputLength, inputCargoWeight;
	private JButton buttonValider;
	
	NumberFormat passengerFieldFormatter;
	DecimalFormat weigthAndLengthFieldFormatter;
	
	
	/**
	 * Constructor to create the register frame with all it's widget.
	 * 
	 * @param a the action listener for the window
	 */
	public RegisterFrame(ActionListener a) {
		// creation de la fenetre
		super("CAR FERRY - Embarquement");
		this.setMinimumSize(new Dimension(300, 200));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.c = this.getContentPane();
		this.c.setLayout(new BoxLayout(this.c, BoxLayout.Y_AXIS));
		
		passengerFieldFormatter = NumberFormat.getIntegerInstance();
		passengerFieldFormatter.setMaximumIntegerDigits(2);
		
		weigthAndLengthFieldFormatter = new DecimalFormat();
		weigthAndLengthFieldFormatter.setMaximumFractionDigits(2);
		DecimalFormatSymbols custom=new DecimalFormatSymbols();
		custom.setDecimalSeparator('.');
		weigthAndLengthFieldFormatter.setDecimalFormatSymbols(custom);
		
		// car/truck choice button
		JPanel vehicleChoice = new JPanel();
		carButton = new JRadioButton("Voiture");
		carButton.setSelected(true);
		TruckButton = new JRadioButton("Camion");
		
		// link button
		vehicleGroup = new ButtonGroup();
		vehicleGroup.add(carButton);
		vehicleGroup.add(TruckButton);
		
		
		// creation of all registration fields
		JPanel panelRegistration = new JPanel();
		inputRegistration = new JTextField(20);
		
		JPanel panelPassenger = new JPanel();
		inputPassenger = new JFormattedTextField(passengerFieldFormatter);
		inputPassenger.setValue(0);
		inputPassenger.setColumns(2);
		
		JPanel panelWeight = new JPanel();
		inputWeight = new JFormattedTextField(weigthAndLengthFieldFormatter);
		inputWeight.setValue(0);
		inputWeight.setColumns(4);
		
		JPanel panelLength = new JPanel();
		inputLength = new JFormattedTextField(weigthAndLengthFieldFormatter);
		inputLength.setValue(0);
		inputLength.setColumns(4);
		
		JPanel panelCargoWeight = new JPanel();
		inputCargoWeight = new JFormattedTextField(weigthAndLengthFieldFormatter);
		inputCargoWeight.setEnabled(false);
		inputCargoWeight.setColumns(4);
		
		JPanel panelDriverName = new JPanel();
		inputDriverName = new JTextField(20);
		
		JPanel panelDriverFirstName = new JPanel();
		inputDriverFirstName = new JTextField(20);
		
		JPanel panelDriverPerm = new JPanel();
		inputDriverPerm = new JTextField(20);
		
		JPanel panelButton = new JPanel();
		buttonValider = new JButton("Valider");
		
		vehicleChoice.add(new JLabel("Quel est le type du véhucule ?"));
		vehicleChoice.add(carButton);
		vehicleChoice.add(TruckButton);
		this.c.add(vehicleChoice);
		panelRegistration.add(new JLabel("Entrez immatriculation du véhicule (20 car maxi)"));
		panelRegistration.add(inputRegistration);
		this.c.add(panelRegistration);
		panelPassenger.add(new JLabel("Entrez le nombre de passagers"));
		panelPassenger.add(inputPassenger);
		this.c.add(panelPassenger);
		panelWeight.add(new JLabel("Entrez le poid du véhicule (en tonnes)"));
		panelWeight.add(inputWeight);
		this.c.add(panelWeight);
		panelLength.add(new JLabel("Entrez la longueur du véhicule (en mètre)"));
		panelLength.add(inputLength);
		this.c.add(panelLength);
		panelCargoWeight.add(new JLabel("Entrez le poids de la cargaison du camion (en tonnes)"));
		panelCargoWeight.add(inputCargoWeight);
		this.c.add(panelCargoWeight);
		panelDriverName.add(new JLabel("Entrez le nom du conducteur"));
		panelDriverName.add(inputDriverName);
		this.c.add(panelDriverName);
		panelDriverFirstName.add(new JLabel("Entrez le prénom du conducteur"));
		panelDriverFirstName.add(inputDriverFirstName);
		this.c.add(panelDriverFirstName);
		panelDriverPerm.add(new JLabel("Entrez le numero de permis de conduire"));
		panelDriverPerm.add(inputDriverPerm);
		this.c.add(panelDriverPerm);
		panelButton.add(buttonValider);
		this.c.add(panelButton);
		
		
		// show buttons 
		vehicleChoice.setBackground(Color.RED);
		this.c.setBackground(Color.GREEN);
		panelRegistration.setOpaque(false);
		panelPassenger.setOpaque(false);
		panelWeight.setOpaque(false);
		panelLength.setOpaque(false);
		panelCargoWeight.setOpaque(false);
		panelDriverName.setOpaque(false);
		panelDriverFirstName.setOpaque(false);
		panelDriverPerm.setOpaque(false);
		
		
		vehicleChoice.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelRegistration.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelPassenger.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelWeight.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelLength.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelCargoWeight.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelDriverName.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelDriverFirstName.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelDriverPerm.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelButton.setLayout(new GridLayout());
		
		
		carButton.addActionListener(a);
		TruckButton.addActionListener(a);
		buttonValider.addActionListener(a);
		
		this.pack();
		this.setVisible(true);
	}

	
	/**
	 * Check if the inputs are correct
	 * 
	 * If not we show to the user the form problem
	 * @return Vehicle if all inputs are correct, else null
	 */
	public Vehicle vehicleFromForm() {
		String registration = inputRegistration.getText();
		if (registration.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Veuillez rensegner votre imatriculation", "Embarquement", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		String[] resitrationParts = registration.split(" ");
		if (resitrationParts.length != 3 || Pattern.matches("[a-zA-Z]+", resitrationParts[0]) == false || Pattern.matches("[0-9]+", resitrationParts[1]) == false || Pattern.matches("[a-zA-Z]+", resitrationParts[2]) == false) {
			JOptionPane.showMessageDialog(this, "Votre immatriculation ne coresond pas: XX 000 XX", "Embarquement", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		int passenger = 0;
		if (carButton.isSelected()) {
			passenger = Integer.valueOf(inputPassenger.getText());
			if (passenger < 0) {
				JOptionPane.showMessageDialog(this, "Le nombre de passager doit être au moin de 0", "Embarquement", JOptionPane.WARNING_MESSAGE);
				return null;
			}
		}
		
		double weight = Double.valueOf(inputWeight.getText());
		if (weight < 0) {
			JOptionPane.showMessageDialog(this, "Le poid de votre véhicule ne peux pas être négatif", "Embarquement", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		
		double length = Double.valueOf(inputLength.getText());
		if (length < 0) {
			JOptionPane.showMessageDialog(this, "Le longueur de votre véhicule ne peux pas être négatif", "Embarquement", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		
		double cargoWeigth = 0;
		if (TruckButton.isSelected()) {
			cargoWeigth = Double.valueOf(inputCargoWeight.getText());
			if (cargoWeigth < 0) {
				JOptionPane.showMessageDialog(this, "Le poid dez la cargaison de votre camion ne peux pas être négatif", "Embarquement", JOptionPane.WARNING_MESSAGE);
				return null;
			}
		}
		
		String driverName = inputDriverName.getText();
		if (driverName.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Veuillez rensegner le nom du conducteur", "Embarquement", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		
		String driverFirstName = inputDriverFirstName.getText();
		if (driverFirstName.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Veuillez rensegner le prénom du conducteur", "Embarquement", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		
		String driverPerm = inputDriverPerm.getText().replace(" ", "");
		if (driverPerm.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Veuillez rensegner le numéro de pérmis du conducteur", "Embarquement", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		
		Driver d = new Driver(driverName, driverFirstName, driverPerm);
		Vehicle v = null;
		if (carButton.isSelected()) {
			 v = new Car(registration, weight, length, d, passenger);
		}
		if (carButton.isSelected()) {
			v = new Truck(registration, weight, length, d, cargoWeigth);
		}
		
		return v;
	}


	/**
	 * @return the instance of radiobutton Car
	 */
	public Object getCarButton() {
		return carButton;
	}

	/**
	 * @return the instance of radiobutton Truck
	 */
	public Object getTruckButton() {
		return TruckButton;
	}
	 
	/**
	 * Activate input form  for car
	 * Here we enable passenger end disable cargoWeight
	 */
	public void activateCarsButton() {
		inputCargoWeight.setValue(null);
		inputCargoWeight.setEnabled(false);
		inputPassenger.setValue(0);
		inputPassenger.setEnabled(true);
		
	}
	
	/**
	 * Activate input form for Truck
	 * Here we enable cargoWeight input and disable passenger
	 */
	public void activateTrucksButton() {
		inputPassenger.setValue(null);
		inputPassenger.setEnabled(false);
		inputCargoWeight.setValue(0);
		inputCargoWeight.setEnabled(true);
	}
	
	
	/**
	 * @return the instance for the validate form
	 */
	public Object getButtonValider() {
		return buttonValider;
	}
}
