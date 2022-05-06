package poal2info.carferry.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import poal2info.carferry.Boat;
import poal2info.carferry.BoatException;
import poal2info.carferry.Ticket;
import poal2info.carferry.Vehicle;

public class WedgeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Container c;
	private JPanel leftRow, rightRow;
	private JList<Object> leftRowList, rightRowList;
	
	private Boat boat;
	
	
	public WedgeFrame(Boat _boat){
		super("Cale du ferry");
		this.setMinimumSize(new Dimension(300, 200));
		
		boat = _boat;
		this.c = this.getContentPane();
		this.c.setLayout(new GridLayout(1, 2));
		
		leftRow = new JPanel();
		leftRow.getInsets();
		leftRow.setLayout(new BorderLayout());
		leftRow.setBorder(new TitledBorder(new EmptyBorder(20, 10, 10, 10), "Rangée gauche"));
		
		leftRowList = new JList<Object>(listDataVehicle(0));
		leftRowList.setBorder(new BevelBorder(BevelBorder.LOWERED));
		leftRowList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		rightRow = new JPanel();
		rightRow.setLayout(new BorderLayout());
		rightRow.setBorder(new TitledBorder(new EmptyBorder(20, 10, 10, 10), "Rangée droite"));
		
		rightRowList = new JList<Object>(listDataVehicle(1));
		rightRowList.setBorder(new BevelBorder(BevelBorder.LOWERED));
		rightRowList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		leftRow.add(leftRowList);
		c.add(leftRow);
		rightRow.add(rightRowList);
		c.add(rightRow);
		
		c.setBackground(Color.GREEN);
		leftRow.setBackground(Color.GREEN);
		rightRow.setBackground(Color.GREEN);
		
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
		        	Ticket t = boat.getTicketFromVehicle(vehicleSelected);
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
		}.init(this);
		
		leftRowList.addMouseListener(listInfoListener);
		rightRowList.addMouseListener(listInfoListener);
		
		this.pack();
		this.setVisible(true);
	}
	
	private Object[] listDataVehicle(int rowNumber) {
		Queue<Vehicle> RowVehicle = null;
		try {
			RowVehicle = boat.getVehiculeRow(rowNumber);
		} catch (BoatException e) {
			RowVehicle = new LinkedList<Vehicle>();
		}
		return RowVehicle.toArray();
	}
	
	public void update() {
		leftRowList.setListData(listDataVehicle(0));
		rightRowList.setListData(listDataVehicle(1));
	}
}
