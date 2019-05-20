package diceroller.userinterface;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import diceroller.mainpackage.DiceSetList;
import diceroller.utils.DefaultSetsFactory;

public class AddDiceSetListPane implements ActionListener
{
	private JLabel nameLabel;
	private JComboBox options;
	private JPanel panel;
	private String selected;
	
	public AddDiceSetListPane()
	{
		panel = new JPanel();
        panel.setLayout(new GridLayout2(2,1));
        Object[] strings= DefaultSetsFactory.defaultSets().toArray();
        options=new JComboBox(strings);
        options.addActionListener(this);
        panel.add(options);
        selected=options.getSelectedItem().toString();
	}
    public int displayGUI() 
    {
        return JOptionPane.showConfirmDialog(null,
                        getPanel(),
                        "Add Dice Set ",
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
    public DiceSetList getSelected()
    {
    	DiceSetList result=DefaultSetsFactory.createFromString(selected);
    	System.out.println(result.getName());
    	return result;
    }
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource().equals(options))
		{
			selected=options.getSelectedItem().toString();
		}
		
	}

}
