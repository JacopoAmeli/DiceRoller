package diceroller.userinterface.rowrenderers;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import diceroller.mainpackage.DiceSet;
import diceroller.mainpackage.StarWarsDice;
import diceroller.userinterface.CustomSpinner;
import diceroller.userinterface.DicePanel;
import diceroller.userinterface.DicePictureGetter;
import diceroller.userinterface.GridLayout2;
import diceroller.userinterface.Logger;
import diceroller.userinterface.PanelEditName;
import diceroller.userinterface.RightPanel;
import diceroller.utils.SWDiceValues;

public class DiceRowSW extends JPanel implements ActionListener,DiceRowRenderer {
	/*
	 * This class builds a row containing Dice icon if available, amout of dice to roll, modifier, roll button.
	 * It provides methods for rolling its dice set, to be called from the DicePanel's master Roll button
	 * 
	 */
	private DiceSet set;
	
	private PanelEditName panelName;
	private CustomSpinner diceNumber;
	private ArrayList<Integer> allRolls;
	private JButton resetButton;
	private Logger logger;
	private RightPanel rightPanel;
	private JButton removeButton;
	
	public DiceRowSW(DiceSet set) throws Exception
	{
		super();
		this.set=set;
		if(!(set.getDice() instanceof StarWarsDice))
			throw new Exception("Wrong argument: SWrenderer with no SW dice");
		initialize();
		
	}
	private void initialize()
	{
		//loads icon, dice, default modifier
		this.setLayout(new GridLayout2(1, 6, 0, 0));
		
		//Icon:
		JPanel panelIcon = new JPanel();
		panelIcon.setLayout(new GridBagLayout());
		JLabel iconLabel = new JLabel();
		iconLabel.setIcon(DicePictureGetter.getImageFor(set));
		iconLabel.setHorizontalAlignment(JButton.CENTER);
		iconLabel.setVerticalAlignment(JButton.CENTER);
		panelIcon.add(iconLabel);
		this.add(panelIcon);
	
		//Name
		panelName = new PanelEditName(set,this);
		this.add(panelName);
		
		//DiceNumber
		JPanel panelSpinner = new JPanel();
		panelSpinner.setLayout(new GridBagLayout());
		diceNumber = new CustomSpinner(99,0,this.set.getNum(),1);
		panelSpinner.add(new JLabel());
		panelSpinner.add(diceNumber);
		this.add(panelSpinner);
		
			//reset Counter
		JPanel resetPanel = new JPanel();
		resetButton = new JButton();
		resetButton.setIcon(DicePictureGetter.getResetIcon());
		resetButton.addActionListener(this);
		resetPanel.add(resetButton);
		resetPanel.setLayout(new GridBagLayout());
		this.add(resetPanel);
		
		//Remove button for single row
		JPanel panelRemove = new JPanel();
		panelRemove.setLayout(new GridBagLayout());
		removeButton = new JButton("X"); 
		removeButton.setHorizontalAlignment(JButton.CENTER);
		removeButton.setVerticalAlignment(JButton.CENTER);
		removeButton.addActionListener(this);
		removeButton.setForeground(Color.RED);
		panelRemove.add(removeButton);
		this.add(panelRemove);
		
	}
 
	public void roll()
	{
		int currentNum=(int)diceNumber.getValue();
		int i;
		allRolls= new ArrayList<Integer>();
		for (i=0; i<currentNum;i++)
		{
			allRolls.add(set.getDice().roll());
		}
	}
	public void save()
	{
		this.set.setName(this.panelName.getName());
		this.set.setNum(diceNumber.getValue());
	}
	public DiceSet getDiceSet()
	{
		return set;
	}
	public List<Integer> getAllRolls()
	{
		return allRolls;
	}
	public int getTotal()
	{
		//this makes no freaking sense 
		return 0;
	}
	public String getResultString()
	{
		int i;
		ArrayList<StarWarsDice> list = new ArrayList<StarWarsDice>();
		for (i=0;i<getAllRolls().size();i++)
			list.add((StarWarsDice)set.getDice());			
		return SWDiceValues.total(getAllRolls(), list).toString();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton source= (JButton) arg0.getSource();
	 if (source.equals(resetButton))
			resetAll();
		else if(arg0.getSource().equals(removeButton))
		{
			this.rightPanel.removeRow(this);
			DicePanel.saveFlag=true;
		}
		
	}
	@Override
	public void resetAll() 
	{
		diceNumber.reset();
		DicePanel.saveFlag=true;
	}
	@Override
	public void setLogger(Logger logger)
	{
		this.logger=logger;		
	}
	@Override
	public int getNum() {
		return this.diceNumber.getValue();
	}
	@Override
	public void setMainPanel(RightPanel mainPanel) {
		this.rightPanel=mainPanel;
		
	}
	@Override
	public void setEditSize(int width)
	{
		this.panelName.setNameSizes(width);
	}
	public int getEditSize()
	{
		return this.panelName.getNameSizes();
	}
	public void editSizeChanged()
	{
		rightPanel.resetColumns();
	}
	
}
