package diceroller.userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import diceroller.mainpackage.DiceSet;
import diceroller.mainpackage.RPGDice;

public class DiceRowRPG extends JPanel implements ActionListener,DiceRowRenderer {
	/*
	 * This class builds a row containing Dice icon if available, amout of dice to roll, modifier, roll button.
	 * It provides methods for rolling its dice set, to be called from the DicePanel's master Roll button
	 * 
	 */
	private DiceSet set;
	private JButton addButton;
	private PanelEditName panelName;
	private CustomSpinner diceNumber;
	private int num;
	private String resultString;
	private JButton editButton;
	private JButton rollButton;
	private int total;
	private int ModTotal;
	private int ModMultiply;
	private ArrayList<Integer> allRolls;
	private Logger logger;
	private RightPanel rightPanel;
	private JButton removeButton;
	
	public DiceRowRPG(DiceSet set) throws Exception
	{
		super();
		this.setFocusable(true);
		this.set=set;
		this.ModTotal=set.getModTotal();
		this.ModMultiply=set.getModMultiply();
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
		//Icon:
		JPanel panelIcon = new JPanel();
		panelIcon.setFocusable(true);
		panelIcon.setLayout(new GridBagLayout());
		addButton = new JButton("+");
		addButton.setIcon(DicePictureGetter.getImageFor(set));
		addButton.addActionListener(this);
		addButton.setHorizontalAlignment(JButton.CENTER);
		addButton.setVerticalAlignment(JButton.CENTER);
		panelIcon.add(addButton);
		this.add(panelIcon);
		
		//Name
		//Name
		panelName = new PanelEditName(set,this);
		this.add(panelName);
		
		//DiceNumber
		JPanel panelSpinner = new JPanel();
		panelSpinner.setLayout(new GridBagLayout());
		panelSpinner.setFocusable(true);
		diceNumber = new CustomSpinner(99,0,this.set.getNum(),1);
		panelSpinner.add(new JLabel("No."));
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
		rollButton.addActionListener(this);
		panelRoll.add(rollButton);
		this.add(panelRoll);
	
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
	public void save()
	{
		this.set.setName(this.panelName.getName());
		this.set.setNum(diceNumber.getValue());
		this.set.setModTotal(ModTotal);
		this.set.setModMultiply(ModMultiply);
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
			if (logger!=null)
					this.logger.appendScroll(resultString);
		}
		else if(arg0.getSource().equals(removeButton))
		{
			this.rightPanel.removeRow(this);
			DicePanel.saveFlag=true;
		}
		else if(arg0.getSource().equals(editButton))
		{
			EditRPGModPane pane = new EditRPGModPane(ModTotal, ModMultiply);
			if(pane.displayGUI()==JOptionPane.YES_OPTION)
			{
				this.ModTotal=pane.getModTotal();
				this.ModMultiply=pane.getModMultiply();
				DicePanel.saveFlag=true;
			}
		}
		
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
