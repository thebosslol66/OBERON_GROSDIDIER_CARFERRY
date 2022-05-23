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


/**
 * @author GROSDIDIER Alphée
 *
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Container c;
	private JMenuBar jmb;
	private JLabel nouvelleCroisiere;
	private JButton embarquer, debarquer; 
	private JMenuItem caleMenuItem;
	
	
	
	/**
	 * Constructor to create the main frame with all it's widget.
	 * 
	 */
	public MainFrame(ActionListener a) {
		//Initialisation de base
		super("CAR FERRY");
		this.setSize(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		embarquer.addActionListener(a);
		debarquer.addActionListener(a);
		caleMenuItem.addActionListener(a);
		
		this.setVisible(true);
	}

	public Object getCaleMenuItem() {
		return caleMenuItem;
	}

	public Object getDebarquer() {
		return debarquer;
	}

	public Object getEmbarquer() {
		return embarquer;
	}


}
