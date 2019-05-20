package diceroller.userinterface.rowrenderers;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import diceroller.mainpackage.DeckOfCards;
import diceroller.mainpackage.DiceSet;
import diceroller.mainpackage.SWSkillDice;
import diceroller.mainpackage.StarWarsDice;
import diceroller.userinterface.CustomSpinner;
import diceroller.userinterface.DicePanel;
import diceroller.userinterface.DicePictureGetter;
import diceroller.userinterface.GridLayout2;
import diceroller.userinterface.Logger;
import diceroller.userinterface.PanelEditName;
import diceroller.userinterface.RightPanel;
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
	private JComboBox panelName;
	private CustomSpinner diceNumber;
	private CustomSpinner profNumber;
	private ArrayList<Integer> allRolls;
	private ArrayList<StarWarsDice> RolledList; 
	private JButton resetButton;
	private Logger logger;
	private JCheckBox checkBoxEnabled;
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
		JLabel iconLabel = new JLabel();
		iconLabel.setIcon(DicePictureGetter.getSkillIcon());
		iconLabel.setHorizontalAlignment(JButton.CENTER);
		iconLabel.setVerticalAlignment(JButton.CENTER);
		panelIcon.add(iconLabel);
		this.add(panelIcon);
	
		//Name
		Object[] strings = new Object[36];
		strings[0]="Skill";
		strings[1]="Astrogation";
		strings[2]="Athletics";
		strings[3]="Brawl";
		strings[4]="Charm";
		strings[5]="Coercion";
		strings[6]="Computers";
		strings[7]="Cool";
		strings[8]="Coordination";
		strings[9]="Core Worlds";
		strings[10]="Deception";
		strings[11]="Discipline";
		strings[12]="Education";
		strings[13]="Gunnery";
		strings[14]="Leadership";
		strings[15]="Lightsaber";
		strings[16]="Lore";
		strings[17]="Mechanics";
		strings[18]="Medicine";
		strings[19]="Melee";
		strings[20]="Negotiation";
		strings[21]="Outer Rim";
		strings[22]="Perception";
		strings[23]="Piloting-Planetary";
		strings[24]="Piloting-Space";
		strings[25]="Ranged-Heavy";
		strings[26]="Ranged-Light";
		strings[27]="Resilience";
		strings[28]="Skulduggery";
		strings[29]="Stealth";
		strings[30]="Streetwise";
		strings[31]="Survival";
		strings[32]="Underworld";
		strings[33]="Vigilance";
		strings[34]="Xenology";
		strings[35]="Warfare";
		panelName = new JComboBox(strings);
		panelName.setSelectedItem(this.set.getName());
		JPanel panelcombobox= new JPanel();
		panelcombobox.setLayout(new GridBagLayout());
		panelcombobox.add(panelName);
		this.add(panelcombobox);
		
		JPanel panelEnabled = new JPanel();
		panelEnabled.setLayout(new GridBagLayout());
		checkBoxEnabled = new JCheckBox();
		checkBoxEnabled.setSelected(false);
		checkBoxEnabled.setSelectedIcon(DicePictureGetter.getCheckedIcon());
		checkBoxEnabled.setIcon(DicePictureGetter.getUncheckedIcon());
		panelEnabled.add(checkBoxEnabled);
		this.add(panelEnabled);
		
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
		int i;
		allRolls= new ArrayList<Integer>();
		RolledList = new ArrayList<StarWarsDice>();
		if(checkBoxEnabled.isSelected())
		{
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
	}
	public ArrayList<StarWarsDice> getRolledList()
	{
		return RolledList;
	}
	public void save()
	{
		this.set.setName(this.panelName.getSelectedItem().toString());
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
		return SWDiceValues.total(getAllRolls(), RolledList).toString();
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
		
	}
	public int getEditSize()
	{
		return this.panelName.getSize().width;
	}
	public void editSizeChanged()
	{
		rightPanel.resetColumns();
	}
	
}
