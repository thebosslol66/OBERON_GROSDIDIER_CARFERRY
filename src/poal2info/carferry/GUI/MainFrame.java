package poal2info.carferry.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import poal2info.carferry.Boat;
import poal2info.carferry.BoatException;
import poal2info.carferry.Vehicle;


public class MainFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Container c;
	private JMenuBar jmb;
	private JLabel nouvelleCroisiere;
	private JButton embarquer, debarquer; 
	private JMenuItem caleMenuItem;
	
	private Boat boat;
	private WedgeFrame wedgeFrame = null;
	private RegisterFrame registerFrame = null;
	
	public MainFrame(Boat _boat) {
		//Initialisation de base
		super("CAR FERRY");
		this.setSize(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		boat = _boat;
		
		// Création de la barre de menu
		jmb = new JMenuBar();
		
		JMenu caleMenu = new JMenu("Cale du ferry");
		caleMenuItem = new JMenuItem("Afficher la cale");
		caleMenu.add(caleMenuItem);
		jmb.add(caleMenu);
		
		
		this.setJMenuBar(jmb);
		
		//set Grid Layer and creation of Panel
		this.c = this.getContentPane();
		this.c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		
		nouvelleCroisiere = new JLabel("Nouvelle croisière");
		nouvelleCroisiere.setAlignmentX(CENTER_ALIGNMENT);
		c.add(nouvelleCroisiere);
		
		JPanel buttonP = new JPanel();
		buttonP.setLayout(new BoxLayout(buttonP, BoxLayout.X_AXIS));
		embarquer = new JButton("Embarquer");
		debarquer = new JButton("Débarquer");
		
		buttonP.add(Box.createHorizontalGlue());
		buttonP.add(Box.createHorizontalGlue());
		buttonP.add(embarquer);
		buttonP.add(Box.createHorizontalGlue());
		buttonP.add(debarquer);
		buttonP.add(Box.createHorizontalGlue());
		buttonP.add(Box.createHorizontalGlue());
		c.add(buttonP);
		buttonP.setOpaque(false);
		c.setBackground(Color.GREEN); 
		
		
		embarquer.addActionListener(this);
		debarquer.addActionListener(this);
		caleMenuItem.addActionListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o instanceof JMenuItem){
			if (o.equals(caleMenuItem)) {
				if (wedgeFrame == null || !wedgeFrame.isShowing()) {
					wedgeFrame = new WedgeFrame(boat);
				}
				else {
					wedgeFrame.setVisible(true);
					wedgeFrame.toFront();
					wedgeFrame.requestFocus();
				}
			}
		}
		if (o instanceof JButton){
			if (o.equals(embarquer)) {
				if (registerFrame == null || !registerFrame.isShowing()) {
					registerFrame = new RegisterFrame(this, boat);
					//get event when registration window close yo update wedge
					registerFrame.addWindowListener(new WindowAdapter() {
			            @Override
			            public void windowClosed(WindowEvent e) {
			            	if (wedgeFrame != null && wedgeFrame.isShowing()) {
								wedgeFrame.update();
								System.out.print("update");
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
			if (o.equals(debarquer)) {
				try {
					Vehicle v = boat.removeVehicle();
					if (wedgeFrame != null && wedgeFrame.isShowing()) {
						wedgeFrame.update();
					}
					JOptionPane.showMessageDialog(this, "Débarquement " + v.getRegistration(), "Débarquement", JOptionPane.WARNING_MESSAGE);
				}
				catch (BoatException ex) {
					if (ex.getReason() == BoatException.Reason.BOAT_IS_EMPTY) {
						JOptionPane.showMessageDialog(this, "La cale est vide", "Débarquement", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		}
		
	}

}
