package diceroller.userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import diceroller.mainpackage.DiceSet;
import diceroller.mainpackage.GenericDice;

public class DiceRowGeneric extends JPanel implements  ActionListener,DiceRowRenderer {

	private DiceSet set;
	private JButton addButton;
	private PanelEditName panelName;
	private CustomSpinner diceNumber;
	private String resultString;
	private JButton rollButton;
	private ArrayList<String> allRolls;
	private Logger logger;
	private RightPanel rightPanel;
	private JButton removeButton;
	
	public DiceRowGeneric(DiceSet set) throws Exception
	{
		super();
		this.set=set;
		if(!(set.getDice() instanceof GenericDice))
			throw new Exception("Wrong argument for GenericRenderer");
		initialize();
	}
	public void save()
	{
		this.set.setName(this.panelName.getName());
		this.set.setNum(diceNumber.getValue());
	}
	private void initialize()
	{
		//loads icon, dice, default modifier
		this.setLayout(new GridLayout2(1, 6, 0, 0));
				
		//Icon:
		JPanel panelIcon = new JPanel();
		panelIcon.setLayout(new GridBagLayout());
		addButton = new JButton("+");
		addButton.setIcon(DicePictureGetter.getImageFor(set));
		addButton.addActionListener(this);
		addButton.setHorizontalAlignment(JButton.CENTER);
		addButton.setVerticalAlignment(JButton.CENTER);
		panelIcon.add(addButton);
		this.add(panelIcon);
				
		//Name
		panelName = new PanelEditName(set,this);
		this.add(panelName);
				
		//DiceNumber
		JPanel panelSpinner = new JPanel();
		panelSpinner.setLayout(new GridBagLayout());
		diceNumber = new CustomSpinner(99,0,this.set.getNum(),1);
		panelSpinner.add(new JLabel("No."));
		panelSpinner.add(diceNumber);
		this.add(panelSpinner);
				
					
		//Roll button for single row
		JPanel panelRoll = new JPanel();
		panelRoll.setLayout(new GridBagLayout());
		rollButton=new JButton(); 
		rollButton.setText("Roll");
		rollButton.setHorizontalAlignment(JButton.CENTER);
		rollButton.setVerticalAlignment(JButton.CENTER);
		rollButton.addActionListener(this);
		panelRoll.add(rollButton);
		this.add(panelRoll);
		
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
	@Override
	public void resetAll()
	{
		this.diceNumber.setValue(0);
		this.resultString="";
	}
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource().equals(addButton))
		{
			this.diceNumber.setValue((int)this.diceNumber.getValue()+1);
			DicePanel.saveFlag=true;
		}
		else if(arg0.getSource().equals(rollButton))
		{
			roll();
			if (logger!=null)
					this.logger.appendScroll(resultString);
		}
		else if(arg0.getSource().equals(removeButton))
		{
			this.rightPanel.removeRow(this);
			DicePanel.saveFlag=true;
		}
		
	}
	public void roll()
	{
		int currentNum=(int)diceNumber.getValue();
		int i;
		String rolledTemp;
		resultString=set.getName()+": ";
		allRolls= new ArrayList<String>();
		for (i=0; i<currentNum;i++)
		{
			GenericDice diceToRoll= (GenericDice)this.set.getDice();
			rolledTemp=diceToRoll.rollString();
			allRolls.add(rolledTemp);
		}
		if(allRolls.size()>0)
		{
			resultString+=allRolls.get(0);
			for (i=1;i<allRolls.size();i++)
			{
				resultString+=","+allRolls.get(i);
			}
		}
	}

	public DiceSet getDiceSet()
	{
		return set;
	}
	public String getResultString()
	{
		return resultString;
	}
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
	public void setEditSize(int width) {
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
