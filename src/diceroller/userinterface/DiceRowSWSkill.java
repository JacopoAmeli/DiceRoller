package diceroller.userinterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import diceroller.mainpackage.DiceSet;
import diceroller.mainpackage.SWSkillDice;
import diceroller.mainpackage.StarWarsDice;
import diceroller.utils.SWDiceValues;

public class DiceRowSWSkill extends JPanel implements ActionListener,DiceRowRenderer {
	/*
	 * This class builds a row containing Dice icon if available, amout of dice to roll, modifier, roll button.
	 * It provides methods for rolling its dice set, to be called from the DicePanel's master Roll button
	 * 
	 */
	private DiceSet set;
	private StarWarsDice ability;
	private StarWarsDice proficiency;
	private JButton addButton;
	private PanelEditName panelName;
	private CustomSpinner diceNumber;
	private CustomSpinner profNumber;
	private ArrayList<Integer> allRolls;
	private ArrayList<StarWarsDice> RolledList; 
	private JButton resetButton;
	private Logger logger;
	private RightPanel rightPanel;
	private JButton removeButton;
	
	public DiceRowSWSkill(DiceSet set) throws Exception
	{
		super();
		this.set=set;
		if(!(set.getDice() instanceof SWSkillDice))
			throw new Exception("Wrong argument: SWrenderer with no SW dice");
		ability=new StarWarsDice("Ability");
		proficiency=new StarWarsDice("Proficiency");
		initialize();
		
	}
	private void initialize()
	{
		//loads icon, dice, default modifier
		this.setLayout(new GridLayout2(1, 6, 0, 0));
		
		//Icon:
		JPanel panelIcon = new JPanel();
		panelIcon.setLayout(new GridBagLayout());
		addButton = new JButton("+");
		addButton.setIcon(DicePictureGetter.getSkillIcon());
		addButton.addActionListener(this);
		addButton.setHorizontalAlignment(JButton.CENTER);
		addButton.setVerticalAlignment(JButton.CENTER);
		panelIcon.add(addButton);
		this.add(panelIcon);
		
		//reset Counter
		JPanel resetPanel = new JPanel();
		resetButton = new JButton("Reset");
		resetButton.addActionListener(this);
		resetPanel.add(resetButton);
		resetPanel.setLayout(new GridBagLayout());
		this.add(resetPanel);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//Name
		panelName = new PanelEditName(set,this);
		this.add(panelName);
		
		//DiceNumberAbility
		JPanel panelSpinner = new JPanel();
		panelSpinner.setLayout(new GridBagLayout());
		diceNumber = new CustomSpinner(99,0,this.set.getNum(),1);
		panelSpinner.add(new JLabel(DicePictureGetter.getAbilityIcon()));
		panelSpinner.add(diceNumber);
		this.add(panelSpinner);
		
		//DiceNumberProficiency
		JPanel panelSpinner2 = new JPanel();
		panelSpinner2.setLayout(new GridBagLayout());
		profNumber = new CustomSpinner(99,0,this.set.getModTotal(),1);
		panelSpinner2.add(new JLabel(DicePictureGetter.getProficiencyIcon()));
		panelSpinner2.add(profNumber);
		this.add(panelSpinner2);
		
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
		int i;
		allRolls= new ArrayList<Integer>();
		RolledList = new ArrayList<StarWarsDice>();
		for (i=0; i<diceNumber.getValue();i++)
		{
			allRolls.add(ability.roll());
			RolledList.add(ability);
		}
		for (i=0; i<profNumber.getValue();i++)
		{
			allRolls.add(proficiency.roll());
			RolledList.add(proficiency);
		}
	}
	public ArrayList<StarWarsDice> getRolledList()
	{
		return RolledList;
	}
	public void save()
	{
		this.set.setName(this.panelName.getName());
		this.set.setModTotal(profNumber.getValue());
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
		return SWDiceValues.total(getAllRolls(), RolledList).toString();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton source= (JButton) arg0.getSource();
		if (source.equals(addButton))
		{
			if((int)diceNumber.getValue()<9)
				diceNumber.setValue((int)diceNumber.getValue()+1);
			if((int)profNumber.getValue()<9)
				profNumber.setValue(profNumber.getValue()+1);
			DicePanel.saveFlag=true;
		}
		else if (source.equals(resetButton))
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
		profNumber.reset();
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
	public int getProf()
	{
		return this.profNumber.getValue();
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
