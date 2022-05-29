package poal2info.carferry.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import poal2info.carferry.Boat;
import poal2info.carferry.BoatException;
import poal2info.carferry.Vehicle;

/**
 * @author GROSDIDIER Alphée
 *
 * The veiw of the wedge frame
 */
public class WedgeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Container c;
	private JPanel leftRow, rightRow;
	private JList<Object> leftRowList, rightRowList;
	
	
	/**
	 * Construct the wedge window
	 */
	public WedgeFrame(){
		super("Cale du ferry");
		this.setMinimumSize(new Dimension(300, 200));
		
		this.c = this.getContentPane();
		this.c.setLayout(new GridLayout(1, 2));
		
		// create left Row
		leftRow = new JPanel();
		leftRow.getInsets();
		leftRow.setLayout(new BorderLayout());
		leftRow.setBorder(new TitledBorder(new EmptyBorder(20, 10, 10, 10), "Rangée gauche"));
		
		leftRowList = new JList<Object>();
		leftRowList.setBorder(new BevelBorder(BevelBorder.LOWERED));
		leftRowList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//create right Row
		rightRow = new JPanel();
		rightRow.setLayout(new BorderLayout());
		rightRow.setBorder(new TitledBorder(new EmptyBorder(20, 10, 10, 10), "Rangée droite"));
		
		rightRowList = new JList<Object>();
		rightRowList.setBorder(new BevelBorder(BevelBorder.LOWERED));
		rightRowList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//display list information
		leftRow.add(leftRowList);
		c.add(leftRow);
		rightRow.add(rightRowList);
		c.add(rightRow);
		
		c.setBackground(Color.GREEN);
		leftRow.setBackground(Color.GREEN);
		rightRow.setBackground(Color.GREEN);
		
		
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Get the list of vehicle from the row
	 * 
	 * @param b the boat model
	 * @param rowNumber the number of the row to get
	 * @return the array list of vehicle which are in the row
	 */
	private Object[] listDataVehicle(Boat b, int rowNumber) {
		Queue<Vehicle> RowVehicle = null;
		try {
			RowVehicle = b.getVehiculeRow(rowNumber);
		} catch (BoatException e) {
			RowVehicle = new LinkedList<Vehicle>();
		}
		return RowVehicle.toArray();
	}
	
	/**
	 * Update JList with current vehicle in Wedge
	 * 
	 * @param b the model of boat
	 */
	public void update(Boat b) {
		leftRowList.setListData(listDataVehicle(b, 0));
		rightRowList.setListData(listDataVehicle(b, 1));
	}

	/**
	 * Set JList listener
	 * 
	 * @param listInfoListener The listener for JList
	 */
	public void appendEventListener(MouseListener listInfoListener) {
		leftRowList.addMouseListener(listInfoListener);
		rightRowList.addMouseListener(listInfoListener);	
	}
}
