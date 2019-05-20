package diceroller.userinterface.rowrenderers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import diceroller.mainpackage.DiceSet;
import diceroller.mainpackage.RPGDice;
import diceroller.userinterface.CustomSpinnerRPG;
import diceroller.userinterface.DicePanel;
import diceroller.userinterface.DicePictureGetter;
import diceroller.userinterface.GridLayout2;
import diceroller.userinterface.Logger;
import diceroller.userinterface.PanelEditName;
import diceroller.userinterface.RightPanel;
import diceroller.userinterface.optiondialogs.EditRPGModPane;

public class DiceRowRPG extends JPanel implements ActionListener,DiceRowRenderer {
	/*
	 * This class builds a row containing Dice icon if available, amout of dice to roll, modifier, roll button.
	 * It provides methods for rolling its dice set, to be called from the DicePanel's master Roll button
	 * 
	 */
	private DiceSet set;
	private JButton addButton;
	private PanelEditName panelName;
	private CustomSpinnerRPG diceNumber;
	private String resultString;
	private JButton editButton;
	private JButton rollButton;
	private int total;
	private int ModTotal;
	private int ModMultiply;
	private ArrayList<Integer> allRolls;
	private JTextField rollTotal;
	private Logger logger;
	private RightPanel rightPanel;
	private JButton removeButton;
	private boolean fumbleFlag=false;
	private boolean critFlag=false;
	private String rollMode;
	
	public DiceRowRPG(DiceSet set) throws Exception
	{
		super();
		this.setFocusable(true);
		this.set=set;
		this.ModTotal=set.getModTotal();
		this.ModMultiply=set.getModMultiply();
		this.rollMode=set.getRollMode();
		if(!(set.getDice() instanceof RPGDice))
			throw new Exception("Wrong argument: RPGrenderer with no RPG dice");
		initialize();
	}
	private void initialize()
	{
		//loads icon, dice name, dice number. default modifier, roll button, remove button
		this.setLayout(new GridLayout2(1, 7, 0, 0));
		this.setFocusable(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		//Icon:
		JPanel panelIcon = new JPanel();
		panelIcon.setFocusable(true);
		panelIcon.setLayout(new GridBagLayout());
		JLabel icon= new JLabel();
		icon.setIcon(DicePictureGetter.getImageFor(set));
		panelIcon.add(icon);
		this.add(panelIcon);
		
	
		//Name
		panelName = new PanelEditName(set,this);
		this.add(panelName);
		
		//DiceNumber
		JPanel panelSpinner = new JPanel();
		panelSpinner.setLayout(new GridBagLayout());
		panelSpinner.setFocusable(true);
		diceNumber = new CustomSpinnerRPG(99,0,this.set.getNum(),1,this.set.getDice().getName());
		panelSpinner.add(diceNumber);
		this.add(panelSpinner);
		
		//Modifier
		JPanel panelModifier = new JPanel();
		panelModifier.setLayout(new GridBagLayout());
		panelModifier.setFocusable(true);
		editButton = new JButton();
		editButton.setIcon(DicePictureGetter.getSettingsIcon());
		editButton.setHorizontalAlignment(JButton.CENTER);
		editButton.setVerticalAlignment(JButton.CENTER);
		editButton.addActionListener(this);
		editButton.setPreferredSize(new Dimension(width/30, width/30));
		panelModifier.add(editButton);
		this.add(panelModifier);
			
		//Roll button for single row
		JPanel panelRoll = new JPanel();
		panelRoll.setLayout(new GridBagLayout());
		panelRoll.setFocusable(true);
		rollButton=new JButton(); 
		rollButton.setText("Roll");
		rollButton.setHorizontalAlignment(JButton.CENTER);
		rollButton.setVerticalAlignment(JButton.CENTER);
		rollButton.setPreferredSize(new Dimension(width/25, width/30));
		rollButton.addActionListener(this);
		panelRoll.add(rollButton);
		this.add(panelRoll);
		
		//resultTotal
		JPanel panelResultTotal= new JPanel();
		panelResultTotal.setLayout(new GridBagLayout());
		rollTotal= new JTextField("0");
		rollTotal.setEditable(false);
		rollTotal.setPreferredSize(new Dimension(width/30,height/30));
		rollTotal.setHorizontalAlignment(JTextField.CENTER);
		panelResultTotal.add(rollTotal);
		this.add(panelResultTotal);
		
		//Remove button for single row
		JPanel panelRemove = new JPanel();
		panelRemove.setLayout(new GridBagLayout());
		panelRemove.setFocusable(true);
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
		if(rollMode.equals("Total"))
			rollTotal();
		else if(rollMode.equals("Advantage"))
			rollAdvantage();
		else if(rollMode.equals("Disadvantage"))
			rollDisadvantage();
	}
	private void rollTotal()
	{
		int total=0;
		int currentNum=(int)diceNumber.getValue();
		int i,rolledTemp;
		resultString=set.getName()+": ";
		allRolls= new ArrayList<Integer>();
		for (i=0; i<currentNum;i++)
		{
			//save stack
			rolledTemp=set.getDice().roll();
			if(i<currentNum-1)
				resultString+=""+rolledTemp+" + ";
			if(i==currentNum-1)
				resultString+=""+rolledTemp;
			allRolls.add(rolledTemp);
			total=total+rolledTemp+ModMultiply;
			
		}
		total+=ModTotal;
		if(ModMultiply!=0)
			resultString+=" + mM: "+currentNum+"*"+ModMultiply;
		if(ModTotal!=0)
			resultString+=" + mT: "+ModTotal;
		if(ModTotal!=0 || ModMultiply!=0 || currentNum!=1)
			resultString+=" = "+total;
		this.total=total;
	}
	private void rollAdvantage()
	{
		int higherRoll=0;
		int currentNum=(int)diceNumber.getValue();
		int i,rolledTemp;
		resultString=set.getName()+": ";
		allRolls= new ArrayList<Integer>();
		for (i=0; i<currentNum;i++)
		{
			rolledTemp=set.getDice().roll();
			if(rolledTemp>higherRoll)
				higherRoll=rolledTemp;
		}
		resultString+=""+higherRoll;
		allRolls.add(higherRoll);
		total=higherRoll;
	}
	private void rollDisadvantage()
	{
		int lowerRoll=((RPGDice)set.getDice()).getMaxValue();
		int currentNum=(int)diceNumber.getValue();
		int i,rolledTemp;
		resultString=set.getName()+": ";
		allRolls= new ArrayList<Integer>();
		for (i=0; i<currentNum;i++)
		{
			rolledTemp=set.getDice().roll();
			if(rolledTemp<lowerRoll)
				lowerRoll=rolledTemp;			
		}
		resultString+=""+(lowerRoll+ModTotal);
		allRolls.add(lowerRoll);
		total=lowerRoll+ModTotal;
	}
	public void save()
	{
		this.set.setName(this.panelName.getName());
		this.set.setNum(diceNumber.getValue());
		this.set.setModTotal(ModTotal);
		this.set.setModMultiply(ModMultiply);
		this.set.setRollMode(rollMode);
	}
	public DiceSet getDiceSet()
	{
		return set;
	}
	public int getTotal()
	{
		//with modifiers added
		return total;
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
	public void resetAll()
	{
		this.ModMultiply=0;
		this.ModTotal=0;
		this.diceNumber.setValue(0);
		DicePanel.saveFlag=true;
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
			this.rollTotal.setForeground(Color.BLACK);
			
			this.rollTotal.repaint();
			if(this.set.getDice().getName().equals("d20"))
			{
				for (Integer i: allRolls)
				{
					if(i==20)
					{
						critFlag=true;
					}
					if(i==1)
					{
						fumbleFlag=true;
					}
				}
				if(critFlag)
					this.rollTotal.setForeground(Color.GREEN);
				else if(fumbleFlag)
					this.rollTotal.setForeground(Color.RED);
				critFlag=false;
				fumbleFlag=false;
			}
			this.rollTotal.setText(""+total);
			if (logger!=null && !allRolls.isEmpty())
					this.logger.appendScroll(resultString);
		}
		else if(arg0.getSource().equals(removeButton))
		{
			this.rightPanel.removeRow(this);
			DicePanel.saveFlag=true;
		}
		else if(arg0.getSource().equals(editButton))
		{
			EditRPGModPane pane = new EditRPGModPane(ModTotal, ModMultiply,rollMode);
			if(pane.displayGUI()==JOptionPane.YES_OPTION)
			{
				this.ModTotal=pane.getModTotal();
				this.ModMultiply=pane.getModMultiply();
				this.rollMode=pane.getRollMode();
				DicePanel.saveFlag=true;
			}
		}
		
	}
	public String getRollMode()
	{
		return rollMode;
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
