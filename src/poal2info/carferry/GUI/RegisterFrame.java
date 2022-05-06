package poal2info.carferry.GUI;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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

import poal2info.carferry.Boat;
import poal2info.carferry.BoatException;
import poal2info.carferry.Car;
import poal2info.carferry.Driver;
import poal2info.carferry.Truck;
import poal2info.carferry.Vehicle;

public class RegisterFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private Container c;
	
	private JRadioButton carButton, TruckButton;
	private ButtonGroup vehicleGroup;
	private JTextField inputRegistration, inputDriverName, inputDriverFirstName, inputDriverPerm;
	private JFormattedTextField inputPassenger, inputWeight, inputLength, inputCargoWeight;
	private JButton buttonValider;
	
	private Boat boat;
	private MainFrame root;
	
	NumberFormat passengerFieldFormatter;
	DecimalFormat weigthAndLengthFieldFormatter;
	
	
	public RegisterFrame(MainFrame _root, Boat _boat) {
		super("CAR FERRY - Embarquement");
		this.setMinimumSize(new Dimension(300, 200));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		boat = _boat;
		root = _root;
		this.c = this.getContentPane();
		this.c.setLayout(new BoxLayout(this.c, BoxLayout.Y_AXIS));
		
		passengerFieldFormatter = NumberFormat.getIntegerInstance();
		passengerFieldFormatter.setMaximumIntegerDigits(2);
		
		weigthAndLengthFieldFormatter = new DecimalFormat();
		weigthAndLengthFieldFormatter.setMaximumFractionDigits(2);
		DecimalFormatSymbols custom=new DecimalFormatSymbols();
		custom.setDecimalSeparator('.');
		weigthAndLengthFieldFormatter.setDecimalFormatSymbols(custom);
		
		JPanel vehicleChoice = new JPanel();
		carButton = new JRadioButton("Voiture");
		carButton.setSelected(true);
		TruckButton = new JRadioButton("Camion");
		
		vehicleGroup = new ButtonGroup();
		vehicleGroup.add(carButton);
		vehicleGroup.add(TruckButton);
		
		
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
		inputDriverPerm = new JFormattedTextField(NumberFormat.getIntegerInstance());
		inputDriverPerm.setColumns(20);
		
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
		
		carButton.addActionListener(this);
		TruckButton.addActionListener(this);
		buttonValider.addActionListener(this);
		
		this.pack();
		this.setVisible(true);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o instanceof JRadioButton){
			if (o.equals(carButton)) {
				inputCargoWeight.setValue(null);
				inputCargoWeight.setEnabled(false);
				inputPassenger.setValue(0);
				inputPassenger.setEnabled(true);
			}
			if (o.equals(TruckButton)) {
				inputPassenger.setValue(null);
				inputPassenger.setEnabled(false);
				inputCargoWeight.setValue(0);
				inputCargoWeight.setEnabled(true);
			}
		}
		if (o instanceof JButton){
			 if (o.equals(buttonValider)){
				 validateForm();
			 }
		}
	}

	private void validateForm() {
		String registration = inputRegistration.getText();
		if (registration.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Veuillez rensegner votre imatriculation", "Embarquement", JOptionPane.WARNING_MESSAGE);
			return;
		}
		String[] resitrationParts = registration.split(" ");
		if (resitrationParts.length != 3 || Pattern.matches("[a-zA-Z]+", resitrationParts[0]) == false || Pattern.matches("[0-9]+", resitrationParts[1]) == false || Pattern.matches("[a-zA-Z]+", resitrationParts[2]) == false) {
			JOptionPane.showMessageDialog(this, "Votre immatriculation ne coresond pas: XX 000 XX", "Embarquement", JOptionPane.WARNING_MESSAGE);
			return;
		}
		int passenger = 0;
		if (carButton.isSelected()) {
			passenger = Integer.valueOf(inputPassenger.getText());
			if (passenger < 0) {
				JOptionPane.showMessageDialog(this, "Le nombre de passager doit être au moin de 0", "Embarquement", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		
		double weight = Double.valueOf(inputWeight.getText());
		if (weight < 0) {
			JOptionPane.showMessageDialog(this, "Le poid de votre véhicule ne peux pas être négatif", "Embarquement", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		double length = Double.valueOf(inputLength.getText());
		if (length < 0) {
			JOptionPane.showMessageDialog(this, "Le longueur de votre véhicule ne peux pas être négatif", "Embarquement", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		double cargoWeigth = 0;
		if (TruckButton.isSelected()) {
			cargoWeigth = Double.valueOf(inputCargoWeight.getText());
			if (cargoWeigth < 0) {
				JOptionPane.showMessageDialog(this, "Le poid dez la cargaison de votre camion ne peux pas être négatif", "Embarquement", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		
		String driverName = inputDriverName.getText();
		if (driverName.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Veuillez rensegner le nom du conducteur", "Embarquement", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String driverFirstName = inputDriverFirstName.getText();
		if (driverFirstName.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Veuillez rensegner le prénom du conducteur", "Embarquement", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String driverPerm = inputDriverPerm.getText().replace(" ", "");
		if (driverPerm.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Veuillez rensegner le numéro de pérmis du conducteur", "Embarquement", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		Driver d = new Driver(driverName, driverFirstName, Integer.valueOf(driverPerm));
		Vehicle v = null;
		if (carButton.isSelected()) {
			 v = new Car(registration, weight, length, d, passenger);
		}
		if (carButton.isSelected()) {
			v = new Truck(registration, weight, length, d, cargoWeigth);
		}
		
		try {
			boat.addVehicle(v);
			this.dispose();//close window
		}
		catch (BoatException e) {
			this.dispose();//close window
			switch(e.getReason()) {
			case NOT_ENOUGTH_SPACE:{
				JOptionPane.showMessageDialog(root, "Embarquement impossible : plus d'éspace disponible", "Embarquement", JOptionPane.WARNING_MESSAGE);
			}break;
			case TOO_HEAVY:{
				JOptionPane.showMessageDialog(root, "Embarquement impossible : limite de poids atteinte", "Embarquement", JOptionPane.WARNING_MESSAGE);
			}break;
			case DRIVER_HAVE_ALREADY_A_CAR:{
				JOptionPane.showMessageDialog(root, "Embarquement impossible : un véhicule a déjà été associè a ce conducteur", "Embarquement", JOptionPane.WARNING_MESSAGE);
			}break;
			case CAR_IS_ALREADY_LOADED:{
				JOptionPane.showMessageDialog(root, "Embarquement impossible : ce véhicule a déjà été chargé", "Embarquement", JOptionPane.WARNING_MESSAGE);
			}break;
			case CANT_LOAD_DURING_UNLOAD:{
				JOptionPane.showMessageDialog(root, "Embarquement impossible : nous débarquons déjà d'autres véhicules", "Embarquement", JOptionPane.WARNING_MESSAGE);
			}break;
			default:
				JOptionPane.showMessageDialog(root, "Une erreur est survenue lors du chargement du véhicule", "Embarquement", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		
	}
}
