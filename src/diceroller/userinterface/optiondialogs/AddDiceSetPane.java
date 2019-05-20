package diceroller.userinterface.optiondialogs;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import diceroller.mainpackage.DiceSet;
import diceroller.mainpackage.DiceSetList;
import diceroller.mainpackage.GenericDice;
import diceroller.mainpackage.RPGDice;
import diceroller.userinterface.DiceSetCellRenderer;
import diceroller.userinterface.DiceSetListCellRenderer;
import diceroller.userinterface.GridLayout2;

public class AddDiceSetPane implements ListSelectionListener {

	private JPanel panel;
	private DiceSet selected;
	private JList list;
	private JList diceList;
	private ArrayList<DiceSetList> mainList;
	
	public AddDiceSetPane(ArrayList<DiceSetList> allList)
	{
		panel = new JPanel();
        panel.setLayout(new GridLayout2(1,2));
        list = new JList();
        this.mainList=allList;
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new AbstractListModel() {
			ArrayList<DiceSetList> values = mainList;
			public int getSize() {
				return values.size();
			}
			public Object getElementAt(int index) {
				return values.get(index);
			}
		});
		list.setCellRenderer(new DiceSetListCellRenderer());
		list.addListSelectionListener(this);
		
		diceList = new JList();
		diceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ArrayList<DiceSet> tempValues = new ArrayList<DiceSet>();
		tempValues.add(new DiceSet(new RPGDice(4),"       "));
		diceList.setModel(new AbstractListModel() {
			ArrayList<DiceSet> values = tempValues;
			public int getSize() {
				return values.size();
			}
			public Object getElementAt(int index) {
				return values.get(index);
			}
		});
		diceList.setCellRenderer(new DiceSetCellRenderer());
		diceList.addListSelectionListener(this);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height=screenSize.height;
		int width=screenSize.width;
		if (screenSize.height>screenSize.width)
		{
			height=width;
			width=screenSize.height;
		}
		JScrollPane paneLeft= new JScrollPane(list);
		//paneLeft.setPreferredSize(new Dimension(screenSize.width/15,screenSize.height/9));
		JScrollPane paneRight= new JScrollPane(diceList);
		
		
		
		panel.add(paneLeft);
		panel.add(new JSeparator(SwingConstants.VERTICAL));
		panel.add(paneRight);
		paneRight.setPreferredSize(list.getPreferredSize());
	}
    public int displayGUI() 
    {
        return JOptionPane.showConfirmDialog(null,
                        getPanel(),
                        "Add Dice",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
    }

    private JPanel getPanel() 
    {
        return panel;
    }
    public String getName()
    {
    	return null;
    }
    public DiceSet getSelected()
    {
    	return selected;
    }
	@Override
	public void valueChanged(ListSelectionEvent arg0) 
	{
		
		if(arg0.getSource().equals(list))
		{
			
			int i;
			ArrayList<DiceSet> tempValues = new ArrayList<DiceSet>();
			DiceSetList selectedDiceSetList= (DiceSetList)list.getSelectedValue();
			if(selectedDiceSetList.getSize()==0)
			{
				//I have to "fake" a new generic dice
				DiceSet tempSet=new DiceSet(new GenericDice(new ArrayList<String>(),"New Dice"));
				tempSet.setName("New Dice");
				tempSet.setNum(0);
				tempValues.add(tempSet);
			}
			for (i=0; i<selectedDiceSetList.getSize();i++)
			{
				tempValues.add(selectedDiceSetList.getDiceSet(i));
			}
			diceList.setModel(new AbstractListModel() {
				ArrayList<DiceSet> values = tempValues;
				public int getSize() {
					return values.size();
				}
				public Object getElementAt(int index) {
					return values.get(index);
				}
			});
			diceList.repaint();
		}
		else if (arg0.getSource().equals(diceList))
		{
			selected = (DiceSet) diceList.getSelectedValue();
		}
		
	}
	
}
